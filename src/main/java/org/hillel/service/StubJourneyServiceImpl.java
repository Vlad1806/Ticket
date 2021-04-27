package org.hillel.service;

import org.hillel.Journey;
import org.hillel.persistence.entity.JourneyEntity;
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

    @Override
    public Long createJourney(JourneyEntity journeyEntity) {
        return null;
    }

    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
        return Collections.emptyList();
    }

    @Override
    public Collection<Journey> findByStations(String stationFrom, String stationTo) {
        return Collections.emptyList();
    }

    @Override
    public Optional<JourneyEntity> getById(Long i,boolean withDependencies) {
        return Optional.empty();
    }
}
