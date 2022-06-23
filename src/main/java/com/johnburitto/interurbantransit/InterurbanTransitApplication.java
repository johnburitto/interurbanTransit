package com.johnburitto.interurbantransit;

import com.johnburitto.interurbantransit.model.FlightStatus;
import com.johnburitto.interurbantransit.service.impls.BookedPlaceService;
import com.johnburitto.interurbantransit.service.impls.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InterurbanTransitApplication implements CommandLineRunner {
    @Autowired
    FlightService service;

    public static void main(String[] args) {
        SpringApplication.run(InterurbanTransitApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
