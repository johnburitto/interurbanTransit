package com.johnburitto.interurbantransit;

import com.johnburitto.interurbantransit.model.Flight;
import com.johnburitto.interurbantransit.model.FlightStatus;
import com.johnburitto.interurbantransit.repository.FlightMongoRepository;
import com.johnburitto.interurbantransit.service.impls.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class InterurbanTransitApplication implements CommandLineRunner {
    @Autowired
    FlightMongoRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(InterurbanTransitApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
