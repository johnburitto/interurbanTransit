package com.johnburitto.interurbantransit;

import com.johnburitto.interurbantransit.model.*;
import com.johnburitto.interurbantransit.service.impls.DriverService;
import com.johnburitto.interurbantransit.service.impls.WorkingBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class InterurbanTransitApplication implements CommandLineRunner {
    @Autowired
    DriverService service;

    public static void main(String[] args) {
        SpringApplication.run(InterurbanTransitApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        
    }
}
