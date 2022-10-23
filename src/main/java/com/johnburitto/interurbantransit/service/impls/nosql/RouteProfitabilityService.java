package com.johnburitto.interurbantransit.service.impls.nosql;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class RouteProfitabilityService
 * @version 1.0.0
 * @since 21.06.2022, 22:43
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.exceptions.ApiRequestException;
import com.johnburitto.interurbantransit.model.nosql.BookedPlace;
import com.johnburitto.interurbantransit.model.nosql.Route;
import com.johnburitto.interurbantransit.model.nosql.RouteProfitability;
import com.johnburitto.interurbantransit.repository.RouteProfitabilityMongoRepository;
import com.johnburitto.interurbantransit.service.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RouteProfitabilityService implements IService<RouteProfitability> {
    @Autowired
    RouteProfitabilityMongoRepository routeProfitabilityRepository;
    @Autowired
    BookedPlaceService bookedPlaceService;

    public List<RouteProfitability> initAndGetAll() {
        getAll().forEach(this::initWithUpdate);

        return getAll();
    }

    @Override
    public RouteProfitability create(RouteProfitability routeProfitability) {
        routeProfitability.setId(generateNextIndex());
        routeProfitability.setCreatedAt(LocalDateTime.now());
        routeProfitability.setUpdatedAt(LocalDateTime.now());
        init(routeProfitability);

        return routeProfitabilityRepository.save(routeProfitability);
    }

    private String generateNextIndex() {
        List<RouteProfitability> data = routeProfitabilityRepository.findAll();

        try {
            return String.valueOf(Integer.parseInt(data.get(data.size() - 1).getId()) + 1);
        }
        catch (Exception e) {
            return "0";
        }
    }

    private void init(RouteProfitability routeProfitability) {
        Route routeToFind = routeProfitability.getRoute();
        List<BookedPlace> allBookedPlaces = new ArrayList<>();

        for (long i = 0; i <= routeProfitability.daysOfAccrual(); i++) {
            allBookedPlaces.addAll(
                    bookedPlaceService.getAllByRouteAndEndDay(routeToFind, routeProfitability.getStartDay().plusDays(i))
            );
        }

        routeProfitability.setNumberOfPassengersAndCalcProfitability(allBookedPlaces);
    }

    private void initWithUpdate(RouteProfitability routeProfitability) {
        init(routeProfitability);
        update(routeProfitability);
    }

    @Override
    public RouteProfitability get(String id) {
        return routeProfitabilityRepository.findById(id).orElseThrow( () -> new ApiRequestException("NotFound!", HttpStatus.NOT_FOUND));
    }

    @Override
    public RouteProfitability update(RouteProfitability routeProfitability) {
        routeProfitability.setCreatedAt(get(routeProfitability.getId()).getCreatedAt());
        routeProfitability.setUpdatedAt(LocalDateTime.now());

        return routeProfitabilityRepository.save(routeProfitability);
    }

    @Override
    public void delete(String id) {
        routeProfitabilityRepository.deleteById(id);
    }

    @Override
    public List<RouteProfitability> getAll() {
        return routeProfitabilityRepository.findAll();
    }

    public List<RouteProfitability> getAllInPage(int size, int pageNumber) {
        return routeProfitabilityRepository.findAll(PageRequest.of(pageNumber, size, Sort.by("id"))).getContent();
    }
}
