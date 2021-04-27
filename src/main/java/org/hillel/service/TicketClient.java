package org.hillel.service;
import org.hillel.Journey;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.entity.VehicleSeatEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDate;
import java.util.*;

@Component
public class TicketClient {

    @Autowired
    @Qualifier("dbJourneyServiceImpl")
    private JourneyService journeyService;


    @Autowired
    @Qualifier("TransactionalJourneyService")
    private TransactionalJourneyService transactionalJourneyService;

    @Autowired
    private TransactionalStopService transactionalStopService;

    @Autowired
    private TransactionalVehicleService transactionalVehicleService;

    @Autowired
    private TransactionalVehicleSeatService transactionalVehicleSeatService;

    @Value("${system.message:default value}")
    private String systemMessage;

    public TicketClient() {
    }

//
//    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
//        if (stationFrom == null) throw new IllegalArgumentException("station from must be set");
//        if (stationTo == null) throw new IllegalArgumentException("station to must be set");
//        if (dateFrom == null) throw new IllegalArgumentException("date from must be set");
//        if (dateTo == null) throw new IllegalArgumentException("date to must be set");
//
//        return journeyService.find(stationFrom, stationTo, dateFrom, dateTo);
//    }
//
//    public Collection<Journey> findByStations(String stationFrom, String stationTo) {
//        if (stationFrom == null) throw new IllegalArgumentException("station from must be set");
//        if (stationTo == null) throw new IllegalArgumentException("station to must be set");
//
//        return journeyService.findByStations(stationFrom, stationTo);
//    }
//    @PostConstruct
//    public void init() throws Exception {
//        if (journeyService == null) throw new IllegalArgumentException("journeyService not init");
//        else {
//            System.out.println("@PostConstruct: " + systemMessage);
//        }
//    }
//
//    @PreDestroy
//    public void destroy() throws Exception {
//        System.out.println("destroy bean");
//    }

    ///JourneyEntity manipulation

    public JourneyEntity createOrUpdateJourney(JourneyEntity journey){
        if (journey == null) throw new IllegalArgumentException("JourneyEntity must be set");
        return transactionalJourneyService.createorUpdateJourney(journey);
    }

    public Optional<JourneyEntity> findJourneyById(Long id,boolean withDependencies){
        Assert.notNull(id, "id must be set");
        return id == null ? Optional.empty() : transactionalJourneyService.findById(id,withDependencies);
    }

    public void remove(JourneyEntity journey1) {
        transactionalJourneyService.remove(journey1);
    }


    //VehicleEntity manipulation
    public VehicleEntity createOrUpdateVehicle(VehicleEntity vehicle) {
        return transactionalVehicleService.createOrUpdateVehicle(vehicle);
    }


    public Optional<VehicleEntity>findVehicleById(Long id,boolean withDependencies){
        Assert.notNull(id,"id must be set");
        return id == null ? Optional.empty() : transactionalVehicleService.findById(id,withDependencies);
    }



//    public void removeById(Long id) {
//        transactionalJourneyService.removeById(id);
//    }
//
//    public void remove( final VehicleEntity vehicleEntity){
//        transactionalVehicleService.remove(vehicleEntity);
//    }

    // Stop manipulation
    public StopEntity createOrUpdateStop(final StopEntity stopEntity){
        return transactionalStopService.createOrUpdateStop(stopEntity);
    }

    public Optional<StopEntity> findStopById(Long id,boolean  withDependencies){
        return transactionalStopService.findById(id,withDependencies);
    }


    //Vehicle seats manipulation

    public VehicleSeatEntity createOrUpdateVehicleSeat(VehicleSeatEntity vehicleSeatEntity){
       return transactionalVehicleSeatService.createOrUpdateVehicleSeat(vehicleSeatEntity);
    }

    public Optional<VehicleSeatEntity> findVehicleSeatById(Long id,boolean withDependencies){
        return transactionalVehicleSeatService.findById(id,withDependencies);
    }
}
