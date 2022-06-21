package com.johnburitto.interurbantransit;

import com.johnburitto.interurbantransit.model.BookedPlace;
import com.johnburitto.interurbantransit.service.impls.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class InterurbanTransitApplication implements CommandLineRunner {
    @Autowired
    BookedPlaceService bookedPlaceService;
    @Autowired
    FlightService flightService;
    @Autowired
    PassengerService passengerService;

    public static void main(String[] args) {
        SpringApplication.run(InterurbanTransitApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
