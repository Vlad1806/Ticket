package org.hillel.persistence.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "journey")
public class JourneyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "station_from")
    private String stationFrom;

    @Column(name = "station_to")
    private String stationTo;

    @Column(name = "departure")
    private LocalDate departure;

    @Column(name = "arrival")
    private LocalDate arrival;


    @Column(name = "route")
    private String route;

    public JourneyEntity(String stationFrom, String stationTo, LocalDate departure, LocalDate arrival) {
        this.stationFrom = stationFrom;
        this.stationTo = stationTo;
        this.departure = departure;
        this.arrival = arrival;
        this.route = stationFrom + " -> " + stationTo;
    }

    public JourneyEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStationFrom() {
        return stationFrom;
    }

    public void setStationFrom(String stationFrom) {
        this.stationFrom = stationFrom;
    }

    public String getStationTo() {
        return stationTo;
    }

    public void setStationTo(String stationTo) {
        this.stationTo = stationTo;
    }

    public LocalDate getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDate departure) {
        this.departure = departure;
    }

    public LocalDate getArrival() {
        return arrival;
    }

    public void setArrival(LocalDate arrival) {
        this.arrival = arrival;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
