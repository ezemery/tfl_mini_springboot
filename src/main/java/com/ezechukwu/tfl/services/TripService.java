package com.ezechukwu.tfl.services;

import com.ezechukwu.tfl.exception.NotFoundException;
import com.ezechukwu.tfl.exception.UnauthorizedException;
import com.ezechukwu.tfl.models.*;
import com.ezechukwu.tfl.records.*;
import com.ezechukwu.tfl.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TripService {
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


    public TripCheckInRecord checkin(TripCheckInRequest tripCheckInRequest) {

        Optional<Card> card = cardRepository.findById(tripCheckInRequest.card().id());
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
                StationPoint station = stationPointRepository.findById(tripCheckInRequest.station_point().station_point_id()).orElseThrow(() -> new NotFoundException("Station not found with id" + tripCheckInRequest.station_point().station_point_id()));
                if(tripCheckInRequest.transport().transport_mode() == TransportMode.BUS){
                    max_charge = 1.80;
                }else{
                    max_charge = 3.20;
                }
                Trip trip = new Trip(time, null, charge, card.get(), station, null, station);
                charge.setAmount(max_charge);
                charge.setCard(card.get());
                charge.setTrip(trip);
                chargeRepository.save(charge);
                tripRepository.save(trip);
                return new TripCheckInRecord(new CardRecord(card.get().getCardId(), card.get().getCardName(), card.get().getCardType(), card.get().getCardToken()), time, null, new StationPointRecord(station.getStationPointId(), new StationRecord(station.getStation().getStationId(), station.getStation().getStationName())), null, max_charge);
            }

        } else {
            throw new NotFoundException("Card not found with id" + tripCheckInRequest.card().id());
        }
    }

    public TripCheckOutRecord checkout(TripCheckOutRequest tripCheckOutRequest) {
        Optional<Card> card = cardRepository.findById(tripCheckOutRequest.card().id());
        if (!card.isEmpty()) {

            Trip trip = tripRepository.findByCardIdAndStationName(card.get().getCardId());
            if (trip == null) {
//                System.out.println("Trip is null" + trip);
                TripCheckInRequest request = new TripCheckInRequest(tripCheckOutRequest.card(), tripCheckOutRequest.station_point(), tripCheckOutRequest.transport());
                TripCheckInRecord newTrip = this.checkin(request);
                return new TripCheckOutRecord(newTrip.card(),newTrip.check_in_time(), null, newTrip.check_in_station(), null, max_charge);
            } else {
                LocalDateTime time = LocalDateTime.now();
                StationPoint station = stationPointRepository.findById(tripCheckOutRequest.station_point().station_point_id()).orElseThrow(() -> new NotFoundException("Station not found with id" + tripCheckOutRequest.station_point().station_point_id()));
                LocalDateTime tripTime = trip.getCheckOutTime();

                trip.setCheckOutStationPoint(station);
                Charge updatedCharge = trip.getCharge();
                Zone checkInZone = zoneRepository.findZoneByStationPointId(trip.getCheckInStationPoint().getStationPointId());
                Zone checkOutZone = zoneRepository.findZoneByStationPointId(tripCheckOutRequest.station_point().station_point_id());
                Fare fare = fareRepository.findByCheckInAndCheckOutZone(checkInZone.getZoneNumber(), checkOutZone.getZoneNumber());
                if(tripCheckOutRequest.transport().transport_mode() == TransportMode.TRAIN){
                    max_charge = fare.getFare();
//                    if(this.isHoliday(tripTime)){
//                        max_charge = max_charge - (max_charge * 0.25);
//                    }
                }else{
                    max_charge = 1.80;
                }
                updatedCharge.setAmount(max_charge);
                chargeRepository.save(updatedCharge);
                Trip updatedTrip = tripRepository.save(trip);
                return new TripCheckOutRecord(new CardRecord(card.get().getCardId(), card.get().getCardName(), card.get().getCardType(), card.get().getCardToken()), updatedTrip.getCheckInTime(), updatedTrip.getCheckOutTime(), new StationPointRecord(updatedTrip.getCheckInStationPoint().getStationPointId(), new StationRecord(updatedTrip.getCheckInStationPoint().getStation().getStationId(), updatedTrip.getCheckInStationPoint().getStation().getStationName())), new StationPointRecord(updatedTrip.getCheckOutStationPoint().getStationPointId(), new StationRecord(updatedTrip.getCheckOutStationPoint().getStation().getStationId(), updatedTrip.getCheckOutStationPoint().getStation().getStationName())), max_charge);

            }
        } else {
            throw new NotFoundException("Card not found with id" + tripCheckOutRequest.card().id());
        }
    }

//    private boolean isHoliday(LocalDateTime date){
//        // fetch holidays from url
//        try{
//            //HolidayResponse holidayResponse = getHolidayAPI;
//            //Map<String, Boolean> holidayResponseMap = holidayResponse.stream().map(()->).collect(Collectors.toMap());
//
//            if(holidayResponseMap.containsKey(date)){
//                return true;
//            }else{
//                return false;
//            }
//        }catch(Exception e){
//            return false;
//        }
//
//    }
}
