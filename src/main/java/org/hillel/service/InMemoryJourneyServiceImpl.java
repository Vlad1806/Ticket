package org.hillel.service;
import org.hillel.Journey;
import org.hillel.persistence.entity.JourneyEntity;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component("inMemoryJourneyService")
@Order(2)
public class InMemoryJourneyServiceImpl implements JourneyService {

    //private final String id;

    @Override
    public String toString() {
        return "InMemoryJourneyServiceImpl{}";
    }

    public InMemoryJourneyServiceImpl() {
        System.out.println("call constructor InMemoryJourneyServiceImpl ");
        //id = identify;
    }

    private Map<String, List<Journey>> storage = new HashMap<>();

    {
        storage.put("Odessa->Kiev",createJourney("Odessa","Kiev"));
        storage.put("Kiev->Odessa",createJourney("Kiev","Odessa"));
        storage.put("Lviv->Kiev",createJourney("Lviv","Kiev"));
    }


    public List<Journey> createJourney(String from, String to) {
        return Arrays.asList(
                new Journey(from, to, LocalDate.now(), LocalDate.now().plusDays(1)),
                new Journey(from, to, LocalDate.now().plusDays(1), LocalDate.now().plusDays(2)),
                new Journey(from, to, LocalDate.now().plusDays(2), LocalDate.now().plusDays(3))
        );
    }

    @Override
    public Long createJourney(JourneyEntity journeyEntity) {
        return null;
    }

    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
        if (storage == null || storage.isEmpty()) return Collections.emptyList();
        List<Journey> journeys = storage.get(stationFrom + " -> " + stationTo);
        if (journeys == null || journeys.isEmpty()) return Collections.emptyList();
        List<Journey> out = new ArrayList<>();
        for(Journey item : journeys){
            if (item.getDeparture().equals(dateFrom) && item.getArrival().equals(dateTo)){
                out.add(item);
            }
        }
        return Collections.unmodifiableList(out);
    }

    @Override
    public Collection<Journey> findByStations(String stationFrom, String stationTo) {
        if (storage == null || storage.isEmpty()) return Collections.emptyList();
        List<Journey> journeys = storage.get(stationFrom + " -> " + stationTo);
        if (journeys == null || journeys.isEmpty()) return Collections.emptyList();
        List<Journey> out = new ArrayList<>(journeys);
        return Collections.unmodifiableList(out);
    }

    @Override
    public Optional<JourneyEntity> getById(Long id,boolean withDependencies) {
        return Optional.empty();
    }
}
