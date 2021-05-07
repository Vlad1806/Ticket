package org.hillel.service;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.entity.VehicleSeatEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

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

    ///JourneyEntity manipulation

    public JourneyEntity createOrUpdateJourney(JourneyEntity journey){
        if (journey == null) throw new IllegalArgumentException("JourneyEntity must be set");
        return transactionalJourneyService.createOrUpdateJourney(journey);
    }

    public Optional<JourneyEntity> findJourneyById(Long id,boolean withDependencies){
        Assert.notNull(id, "id must be set");
        return id == null ? Optional.empty() : transactionalJourneyService.findById(id,withDependencies);
    }

    public Collection<JourneyEntity> findAllJourney(){
        return transactionalJourneyService.findAll();
    }
    public Collection<JourneyEntity> findAllJourneyAsNative(){
        return transactionalJourneyService.findAllAsNative();
    }
    public Collection<JourneyEntity> findAllJourneyAsNamed(){
        return transactionalJourneyService.findAllJourneyAsNamed();
    }
    public Collection<JourneyEntity>findAllJourneyAsCriteria(){
        return transactionalJourneyService.findAllAsCriteria();
    }
    public Collection<JourneyEntity> findAllJourneyAsStoredProcedure(){
        return transactionalJourneyService.findAllAsStoredProcedure();
    }
//    public void remove(JourneyEntity journey1) {
//        transactionalJourneyService.remove(journey1);
//    }
//
//    public void removeById(Long id) {
//        transactionalJourneyService.removeById(id);
//    }


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

    public Collection<VehicleEntity> findAllVehicles(){
        return transactionalVehicleService.findAll();
    }
    public Collection<VehicleEntity> findAllVehiclesAsNative(){
        return transactionalVehicleService.findAllAsNative();
    }
    public Collection<VehicleEntity> findAllVehiclesAsNamed(){
        return transactionalVehicleService.findAllAsNamed();
    }

    public Collection<VehicleEntity>findAllVehiclesAsCriteria(){
        return transactionalVehicleService.findAllAsCriteria();
    }
    public Collection<VehicleEntity> findAllVehiclesAsStoredProcedure(){
        return transactionalVehicleService.findAllAsStoredProcedure();
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

    public Collection<StopEntity>findAllStops(){
        return transactionalStopService.findAll();
    }
    public Collection<StopEntity>findAllStopsAsNative(){
        return transactionalStopService.findAllAsNative();
    }
    public Collection<StopEntity> findAllStopsAsNamed(){
        return transactionalStopService.findAllAsNamed();
    }
    public Collection<StopEntity>findAllStopsAsCriteria(){
        return transactionalStopService.findAllAsCriteria();
    }
    public Collection<StopEntity> findAllStopsAsStoredProcedure(){
        return transactionalStopService.findAllAsStoredProcedure();
    }

    //Vehicle seats manipulation

    public VehicleSeatEntity createOrUpdateVehicleSeat(VehicleSeatEntity vehicleSeatEntity){
       return transactionalVehicleSeatService.createOrUpdateVehicleSeat(vehicleSeatEntity);
    }

    public Optional<VehicleSeatEntity> findVehicleSeatById(Long id,boolean withDependencies){
        return transactionalVehicleSeatService.findById(id,withDependencies);
    }

    public Collection<VehicleSeatEntity> findAllVehicleSeats(){
        return transactionalVehicleSeatService.findAll();
    }
    public Collection<VehicleSeatEntity> findAllVehicleSeatsAsNative(){
        return transactionalVehicleSeatService.findAllAsNative();
    }

    public Collection<VehicleSeatEntity> findAllVehicleSeatsAsNamed(){
        return transactionalVehicleSeatService.findAllAsNamed();
    }
    public Collection<VehicleSeatEntity> findAllVehicleSeatsAsCriteria(){
        return transactionalVehicleSeatService.findAllAsCriteria();
    }
    public Collection<VehicleSeatEntity>  findAllVehicleSeatsAsStoredProcedure(){
        return transactionalVehicleSeatService.findAllAsStoredProcedure();
    }

    public void removeVehicleSeat(VehicleSeatEntity vehicleSeatEntity){
        transactionalVehicleSeatService.remove(vehicleSeatEntity);
    }

}
