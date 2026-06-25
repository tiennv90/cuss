# Objective

Design and implement a backend system for a CUSS (Common Use Self Service) check-in kiosk at an airport, which allows passengers to check in, select seats, register baggage, and print boarding passes.

The system must support concurrent check-ins from multiple kiosks, complex transactions, and high performance.

# Overall Architecture

[ Kiosk Desktop App ]
|
v
[ Spring Boot REST API Layer ]
|
+--> Business Service Layer (CheckInService, BaggageService, SeatService)
|
+--> Kafka Producer (logs check-in events, sends notifications)
|
+--> Database Layer (PostgreSQL/MySQL)
|
+--> Kafka Consumer (async processing: update baggage status, send boarding pass emails)
# Database Design (SQL)
passenger
id BIGSERIAL PRIMARY KEY,
pnr VARCHAR(20) UNIQUE,
first_name VARCHAR(50),
last_name VARCHAR(50),
flight_id BIGINT REFERENCES flight(id),
seat_no VARCHAR(5),
status VARCHAR(20)

flight
id BIGSERIAL PRIMARY KEY,
flight_code VARCHAR(20),
departure_time TIMESTAMP,
available_seats INT

baggage
id BIGSERIAL PRIMARY KEY,
passenger_id BIGINT REFERENCES passenger(id),
weight DECIMAL(5,2),
tag_code VARCHAR(20) UNIQUE,
status VARCHAR(20)

checkin_log
id BIGSERIAL PRIMARY KEY,
passenger_id BIGINT,
kiosk_id VARCHAR(20),
timestamp TIMESTAMP DEFAULT NOW(),
event_type VARCHAR(50),
data JSONB

# Main Features / APIs


## POST /api/checkin
Passenger check-in
1. Validate booking (PNR)
2. Update seat (SeatService) and decreaseAvailableSeat (FlightRepository) in Flight with idempotency using optimistic Lock 
3. Register baggage (BaggageService)
4. Commit transaction

## GET /api/seats
Get available seats
Use Java Stream API to filter available seats 

## POST /api/v1/baggage/updateStatus
Update baggage status with 3 different versions. Version 3 present batch processing with  performance optimization.

## POST /api/baggage

Handle concurrency with table locks or transaction isolation

## GET /api/status/{kioskId}

Kiosk status
Query asynchronously via Kafka or cache

## Complex Transaction

Check-in Service

## Multithreading & Concurrency

Handle multiple kiosks concurrently using ExecutorService or @Async.

Prevent race conditions on seat assignment using optimistic lock or ReentrantLock.

## Java Stream API & Collections

Filter available seats


