
INSERT INTO station (station_id, station_name) VALUES (1, 'Holborn');
INSERT INTO station (station_id, station_name) VALUES (2, 'Hammersmith');
INSERT INTO station (station_id, station_name) VALUES (3, 'Chelsea');
INSERT INTO station (station_id, station_name) VALUES (4, 'Earls Court');
INSERT INTO station (station_id, station_name) VALUES (5, 'Wimbledon');

INSERT INTO station_point (station_point_id, station_id) VALUES (1, 1);
INSERT INTO station_point (station_point_id, station_id) VALUES (2, 4);
INSERT INTO station_point (station_point_id, station_id) VALUES (3, 4);
INSERT INTO station_point (station_point_id, station_id) VALUES (4, 5);
INSERT INTO station_point (station_point_id, station_id) VALUES (5, 2);


INSERT INTO zone (zone_id, zone_number, station_point_id) VALUES (1, 1, 1);
INSERT INTO zone (zone_id, zone_number, station_point_id) VALUES (2, 1, 4);
INSERT INTO zone (zone_id, zone_number, station_point_id) VALUES (3, 2, 4);
INSERT INTO zone (zone_id, zone_number, station_point_id) VALUES (4, 3, 5);
INSERT INTO zone (zone_id, zone_number, station_point_id) VALUES (5, 2, 2);
INSERT INTO zone (zone_id, zone_number, station_point_id) VALUES (6, 1, 3);

INSERT INTO fare (fare_id, checkin_zone, checkout_zone, transport_mode, fare) VALUES (1, 1, 1,'train', 2.50);
INSERT INTO fare (fare_id, checkin_zone, checkout_zone, transport_mode, fare) VALUES (2, 2, 2,'train' ,2.00);
INSERT INTO fare (fare_id, checkin_zone, checkout_zone, transport_mode, fare) VALUES (3, 2, 3,'train', 2.00);
INSERT INTO fare (fare_id, checkin_zone, checkout_zone, transport_mode, fare) VALUES (4, 3, 2,'train', 2.00);
INSERT INTO fare (fare_id, checkin_zone, checkout_zone, transport_mode, fare) VALUES (5, 1, 2,'train', 3.00);
INSERT INTO fare (fare_id, checkin_zone, checkout_zone, transport_mode, fare) VALUES (6, 1, 3,'train', 3.00);
INSERT INTO fare (fare_id, checkin_zone, checkout_zone, transport_mode, fare) VALUES (7, null, null, 'bus', 1.80);
