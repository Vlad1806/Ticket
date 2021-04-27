package org.hillel.service;

import org.hillel.Journey;
import org.hillel.persistence.entity.JourneyEntity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

public interface JourneyService {

    Long createJourney(JourneyEntity journeyEntity);

    Collection<Journey> find (String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo);


    Collection<Journey> findByStations (String stationFrom, String stationTo);

    Optional<JourneyEntity> getById(Long id,boolean withDependencies);
}
