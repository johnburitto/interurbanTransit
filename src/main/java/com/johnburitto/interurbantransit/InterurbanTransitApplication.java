package com.johnburitto.interurbantransit;

import com.johnburitto.interurbantransit.model.ContactPerson;
import com.johnburitto.interurbantransit.model.Name;
import com.johnburitto.interurbantransit.model.User;
import com.johnburitto.interurbantransit.model.UserType;
import com.johnburitto.interurbantransit.service.impls.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InterurbanTransitApplication implements CommandLineRunner {
    @Autowired
    UserService service;

    public static void main(String[] args) {
        SpringApplication.run(InterurbanTransitApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        service.create(new User("0", "owner", "owner", UserType.Owner, new ContactPerson(
                new Name("Андрій", "Юрійович", "Борсук"), "+380967283875",
                "johnburitto@gmail.com"
        )));
    }
}
