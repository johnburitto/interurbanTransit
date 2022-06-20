package com.johnburitto.interurbantransit;

import com.johnburitto.interurbantransit.model.FlightStatus;
import com.johnburitto.interurbantransit.service.impls.DriverService;
import com.johnburitto.interurbantransit.service.impls.FlightService;
import com.johnburitto.interurbantransit.service.impls.RouteService;
import com.johnburitto.interurbantransit.service.impls.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;


@SpringBootApplication
public class InterurbanTransitApplication implements CommandLineRunner {
    @Autowired
    FlightService flightService;
    @Autowired
    TransportService transportService;
    @Autowired
    DriverService driverService;
    @Autowired
    RouteService routeService;

    public static void main(String[] args) {
        SpringApplication.run(InterurbanTransitApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
