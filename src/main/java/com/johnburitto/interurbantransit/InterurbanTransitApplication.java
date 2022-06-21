package com.johnburitto.interurbantransit;

import com.johnburitto.interurbantransit.model.RouteProfitability;
import com.johnburitto.interurbantransit.service.impls.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class InterurbanTransitApplication implements CommandLineRunner {
    @Autowired
    RouteProfitabilityService routeProfitabilityService;
    @Autowired
    RouteService routeService;

    public static void main(String[] args) {
        SpringApplication.run(InterurbanTransitApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
