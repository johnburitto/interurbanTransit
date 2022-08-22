package com.johnburitto.interurbantransit;

import com.johnburitto.interurbantransit.model.*;
import com.johnburitto.interurbantransit.service.impls.DriverService;
import com.johnburitto.interurbantransit.service.impls.FlightService;
import com.johnburitto.interurbantransit.service.impls.RouteService;
import com.johnburitto.interurbantransit.service.impls.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootApplication
public class InterurbanTransitApplication implements CommandLineRunner {
    @Autowired
    DriverService driverService;
    @Autowired
    RouteService routeService;
    @Autowired
    TransportService transportService;
    @Autowired
    FlightService flightService;

    public static void main(String[] args) {
        SpringApplication.run(InterurbanTransitApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        flightService.create(
                new Flight(transportService.get(1), driverService.get(1), routeService.get(1), 110, LocalDate.now(), LocalDate.now())
        );
    }
}
