package org.hillel.service;
import lombok.NoArgsConstructor;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.entity.VehicleSeatEntity;
import org.hillel.persistence.entity.enums.SqlType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.*;

@Component
@NoArgsConstructor
public class TicketClient<E> {

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


    ///JourneyEntity manipulation
    public JourneyEntity createOrUpdateJourney(JourneyEntity journey){
        if (journey == null) throw new IllegalArgumentException("JourneyEntity must be set");
        return transactionalJourneyService.createOrUpdateJourney(journey);
    }
    public Optional<JourneyEntity> findJourneyById(Long id,boolean withDependencies){
        Assert.notNull(id, "id must be set");
        return id == null ? Optional.empty() : transactionalJourneyService.findById(id,withDependencies);
    }

    public Collection<JourneyEntity> findAllJourney(SqlType sqlType){
        return transactionalJourneyService.findAll(sqlType);
    }

    public Collection<JourneyEntity> findAllJourney(SqlType sql,int startPage, int sizePage, String field, boolean orderType){
        return transactionalJourneyService.findAll(sql,startPage,sizePage,field,orderType);
    }
    public void remove(JourneyEntity journey1) {
        transactionalJourneyService.remove(journey1);
    }
    public void removeById(Long id) {
        transactionalJourneyService.removeById(id);
    }

    //VehicleEntity manipulation
    public VehicleEntity createOrUpdateVehicle(VehicleEntity vehicle) {
        return transactionalVehicleService.createOrUpdateVehicle(vehicle);
    }
    public Optional<VehicleEntity>findVehicleById(Long id,boolean withDependencies){
        Assert.notNull(id,"id must be set");
        return id == null ? Optional.empty() : transactionalVehicleService.findById(id,withDependencies);
    }
    public void removeVehicle(final VehicleEntity vehicleEntity){
        transactionalVehicleService.remove(vehicleEntity);
    }
    public void removeVehicleById(Long id){
        transactionalVehicleService.removeById(id);
    }
    public Collection<VehicleEntity> findVehicleByIds(boolean withDependencies,Long ... ids){
        final Collection<VehicleEntity> byIds = transactionalVehicleService.findByIds(withDependencies, ids);
        return byIds;
    }
    public Collection<VehicleEntity> findAllVehicles(SqlType sqlType){
        return transactionalVehicleService.findAll(sqlType);
    }
    public Collection<VehicleEntity> findAllVehicles(SqlType sql,int startPage, int sizePage, String field, boolean orderType){
        return transactionalVehicleService.findAll(sql,startPage,sizePage,field,orderType);
    }
    public Collection<VehicleEntity> findVehicleByMinSeats(){
        return transactionalVehicleService.findVehicleByMinSeats();
    }
    public Collection<VehicleEntity> findVehicleByMaxSeats(){
        return transactionalVehicleService.findVehicleByMaxSeats();
    }
    public Collection<VehicleEntity> findAllVehiclesByName(String name){
        return transactionalVehicleService.findByName(name);
    }

    // Stop manipulation
    public StopEntity createOrUpdateStop(final StopEntity stopEntity){
        return transactionalStopService.createOrUpdateStop(stopEntity);
    }
    public Optional<StopEntity> findStopById(Long id,boolean  withDependencies){
        return transactionalStopService.findById(id,withDependencies);
    }
    public void removeStop( final StopEntity stopEntity){
        transactionalStopService.remove(stopEntity);
    }
    public void removeStopById(Long id){
        transactionalStopService.removeById(id);
    }
    public Collection<StopEntity>findAllStops(SqlType sqlType){
        return transactionalStopService.findAll(sqlType);
    }
    public Collection<StopEntity> findAllStops(SqlType sql,int startPage, int sizePage, String field, boolean orderType){
        return transactionalStopService.findAll(sql,startPage,sizePage,field,orderType);
    }
    //Vehicle seats manipulation
    public VehicleSeatEntity createOrUpdateVehicleSeat(VehicleSeatEntity vehicleSeatEntity){
       return transactionalVehicleSeatService.createOrUpdateVehicleSeat(vehicleSeatEntity);
    }
    public Optional<VehicleSeatEntity> findVehicleSeatById(Long id,boolean withDependencies){
        return transactionalVehicleSeatService.findById(id,withDependencies);
    }
    public Collection<VehicleSeatEntity> findAllVehicleSeats(SqlType sqlType){
        return transactionalVehicleSeatService.findAll(sqlType);
    }
    public Collection<VehicleSeatEntity> findAllVehicleSeats(SqlType sql,int startPage, int sizePage, String field, boolean orderType){
        return transactionalVehicleSeatService.findAll(sql,startPage,sizePage,field,orderType);
    }
    public void removeVehicleSeat(VehicleSeatEntity vehicleSeatEntity){
        transactionalVehicleSeatService.remove(vehicleSeatEntity);
    }

}
