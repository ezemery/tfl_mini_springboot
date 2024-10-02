package com.ezechukwu.tfl.services.impl;

import com.ezechukwu.tfl.dto.request.TripCheckInRequest;
import com.ezechukwu.tfl.dto.request.TripCheckOutRequest;
import com.ezechukwu.tfl.dto.response.*;
import com.ezechukwu.tfl.exception.NotFoundException;
import com.ezechukwu.tfl.exception.UnauthorizedException;
import com.ezechukwu.tfl.models.*;
import com.ezechukwu.tfl.repositories.*;
import com.ezechukwu.tfl.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TripServiceImpl implements TripService {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private ChargeRepository chargeRepository;
    @Autowired
    private StationPointRepository stationPointRepository;
    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private FareRepository fareRepository;
    @Value("${application.max_charge}")
    private Double max_charge;


    public TripCheckInResponse checkin(TripCheckInRequest tripCheckInRequest) {

        Optional<Card> card = cardRepository.findById(tripCheckInRequest.getCard().getId());
        if (!card.isEmpty()) {
            //check if card has enough balance
            Double walletBookBalance = card.get().getWallet().getBookBalance();
            if (walletBookBalance < max_charge) {
                throw new UnauthorizedException("Card balance is too low for this trip");
            } else {
                Wallet wallet = card.get().getWallet();
                wallet.setBookBalance(wallet.getBookBalance() - max_charge);
                Charge charge = new Charge();
                LocalDateTime time = LocalDateTime.now();
                StationPoint station = stationPointRepository.findById(tripCheckInRequest.getStationPoint().getStationPointId())
                        .orElseThrow(() -> new NotFoundException("Station not found with id" + tripCheckInRequest.getStationPoint().getStationPointId()));

                max_charge = calculateTripFare(tripCheckInRequest, null, null);

                Trip trip = Trip.builder()
                        .checkInTime(time)
                        .charge(charge)
                        .card(card.get())
                        .checkInStationPoint(station)
                        .checkOutStationPoint(null)
                        .station(station)
                        .build();

                charge.setAmount(max_charge);
                charge.setCard(card.get());
                charge.setTrip(trip);
                chargeRepository.save(charge);
                tripRepository.save(trip);

                CardResponse cardResponse = CardResponse.builder()
                        .id(card.get().getCardId())
                        .cardName(card.get().getCardName())
                        .cardToken(card.get().getCardToken())
                        .cardType(card.get().getCardType())
                        .build();

                return  TripCheckInResponse.builder()
                        .card(cardResponse)
                        .checkInTime(time)
                        .checkOutTime(null)
                        .checkInStation(new StationPointResponse(station.getStationPointId(), new StationResponse(station.getStation().getStationId(), station.getStation().getStationName())))
                        .checkOutStation(null)
                        .fare(max_charge)
                        .build();
            }

        } else {
            throw new NotFoundException("Card not found with id" + tripCheckInRequest.getCard().getId());
        }
    }

    public TripCheckOutResponse checkout(TripCheckOutRequest tripCheckOutRequest) {
        Optional<Card> card = cardRepository.findById(tripCheckOutRequest.getCard().getId());
        if (!card.isEmpty()) {

            Trip trip = tripRepository.findByCardIdAndStationName(card.get().getCardId());
            if (trip == null) {
                TripCheckInRequest request = TripCheckInRequest.builder()
                        .card(tripCheckOutRequest.getCard())
                        .stationPoint(tripCheckOutRequest.getStationPoint())
                        .transport(tripCheckOutRequest.getTransport())
                        .build();

                TripCheckInResponse newTrip = this.checkin(request);

                return  TripCheckOutResponse.builder()
                        .card(newTrip.getCard())
                        .checkInTime(newTrip.getCheckInTime())
                        .checkOutTime(null)
                        .checkInStation(newTrip.getCheckInStation())
                        .checkOutStation(null)
                        .fare(max_charge)
                        .build();
            } else {
                StationPoint station = stationPointRepository.findById(tripCheckOutRequest.getStationPoint().getStationPointId())
                        .orElseThrow(() -> new NotFoundException("Station not found with id" + tripCheckOutRequest.getStationPoint().getStationPointId()));

                trip.setCheckOutStationPoint(station);
                Charge updatedCharge = trip.getCharge();
                Zone checkInZone = zoneRepository.findZoneByStationPointId(trip.getCheckInStationPoint().getStationPointId());
                Zone checkOutZone = zoneRepository.findZoneByStationPointId(tripCheckOutRequest.getStationPoint().getStationPointId());
                Fare fare = fareRepository.findByCheckInAndCheckOutZone(checkInZone.getZoneNumber(), checkOutZone.getZoneNumber());

                max_charge = calculateTripFare(null, tripCheckOutRequest, fare);

                updatedCharge.setAmount(max_charge);

                chargeRepository.save(updatedCharge);
                Trip updatedTrip = tripRepository.save(trip);

                CardResponse cardResponse = CardResponse.builder()
                        .id(card.get().getCardId())
                        .cardName(card.get().getCardName())
                        .cardToken(card.get().getCardToken())
                        .cardType(card.get().getCardType())
                        .build();

                return  TripCheckOutResponse.builder()
                        .card(cardResponse)
                        .checkInTime(updatedTrip.getCheckInTime())
                        .checkOutTime(updatedTrip.getCheckOutTime())
                        .checkInStation(new StationPointResponse(updatedTrip.getCheckInStationPoint().getStationPointId(), new StationResponse(updatedTrip.getCheckInStationPoint().getStation().getStationId(), updatedTrip.getCheckInStationPoint().getStation().getStationName())))
                        .checkOutStation(new StationPointResponse(updatedTrip.getCheckOutStationPoint().getStationPointId(), new StationResponse(updatedTrip.getCheckOutStationPoint().getStation().getStationId(), updatedTrip.getCheckOutStationPoint().getStation().getStationName())))
                        .fare(max_charge)
                        .build();
            }
        } else {
            throw new NotFoundException("Card not found with id" + tripCheckOutRequest.getCard().getId());
        }
    }

    private Double calculateTripFare(TripCheckInRequest tripCheckInRequest, TripCheckOutRequest tripCheckOutRequest, Fare fare){
        Double max_charge = 0.0;
        if(tripCheckInRequest != null){
            if (tripCheckInRequest.getTransport().getTransportMode() == TransportMode.BUS) {
                max_charge = 1.80;
            } else {
                max_charge = 3.20;
            }
        }
        if(tripCheckOutRequest != null){
            if (tripCheckOutRequest.getTransport().getTransportMode() == TransportMode.TRAIN) {
                max_charge = fare.getFare();
            } else {
                max_charge = 1.80;
            }
        }

        return max_charge;
    }

}
