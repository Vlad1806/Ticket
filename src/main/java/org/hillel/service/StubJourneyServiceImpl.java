package org.hillel.service;

import org.hillel.Journey;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
@Order(1)
public class StubJourneyServiceImpl implements JourneyService  {
    @Override
    public String toString() {
        return "StubJourneyServiceImpl{}";
    }

    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
        return Collections.emptyList();
    }

    @Override
    public Collection<Journey> findByStations(String stationFrom, String stationTo) {
        return Collections.emptyList();
    }
}
