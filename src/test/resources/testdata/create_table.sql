-- ============================================================
-- BlazeDemo Automation Framework — MySQL Test Data Schema
-- Database: blazedemo_testdata
-- Table:    flight_bookings
--
-- Column order matches the 12-column schema in DESIGN.md Section 8.
-- name_on_card maps to the nameOnCard field on the BlazeDemo purchase form.
--
-- Run this script once to set up the database before using DatabaseDataReader.
-- Update config.properties (db.username / db.password) before connecting.
-- ============================================================

CREATE DATABASE IF NOT EXISTS blazedemo_testdata
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE blazedemo_testdata;

CREATE TABLE IF NOT EXISTS flight_bookings (
    id               INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    departure_city   VARCHAR(100) NOT NULL,
    destination_city VARCHAR(100) NOT NULL,
    name             VARCHAR(150) NOT NULL,
    address          VARCHAR(200) NOT NULL,
    city             VARCHAR(100) NOT NULL,
    state            VARCHAR(100) NOT NULL,
    zip              VARCHAR(20)  NOT NULL,
    card_type        VARCHAR(50)  NOT NULL,
    card_number      VARCHAR(20)  NOT NULL,
    card_month       VARCHAR(5)   NOT NULL,
    card_year        VARCHAR(6)   NOT NULL,
    name_on_card     VARCHAR(150) NOT NULL
);

-- Sample test data (mirrors bookings.csv)
INSERT INTO flight_bookings
    (departure_city, destination_city, name, address, city, state, zip,
     card_type, card_number, card_month, card_year, name_on_card)
VALUES
    ('Philadelphia', 'Buenos Aires', 'John Smith', '123 Main Street',
     'Philadelphia', 'PA', '19103', 'Visa', '4111111111111111', '10', '2026', 'John Smith'),

    ('Boston', 'Rome', 'Jane Doe', '456 Oak Avenue',
     'Boston', 'MA', '02101', 'American Express', '5500005555555559', '12', '2027', 'Jane Doe');
