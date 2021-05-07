package org.hillel;
import org.hillel.config.RootConfig;
import org.hillel.persistence.entity.*;
import org.hillel.persistence.entity.enums.DirectionType;
import org.hillel.service.TicketClient;
import org.springframework.beans.BeansException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Starter {
    public static void main(String[] args) throws BeansException {

        //final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("common-beans.xml");
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
        TicketClient ticketClient =  applicationContext.getBean(TicketClient.class);

        //Create Vehicle
        VehicleEntity vehicle1 = buildVehicle("Bus_1",12);
        vehicle1 = ticketClient.createOrUpdateVehicle(vehicle1);

        VehicleEntity vehicle2 = buildVehicle("Train_2",10);
        vehicle2 = ticketClient.createOrUpdateVehicle(vehicle2);

        VehicleEntity vehicle3 = buildVehicle("Train_3",5);
        vehicle3 = ticketClient.createOrUpdateVehicle(vehicle3);

        //Create Journey
        JourneyEntity journey1 = buildJourney("Одесса","Киев",
               Instant.now(),Instant.now().plusSeconds(10000L),DirectionType.TO,true);
        //Create Journey
        JourneyEntity journey2 = buildJourney("Одесса","Львов",
                Instant.now(),Instant.now().plusSeconds(10000L),DirectionType.TO,true);
        // Add stops
        journey1.addStop(buildStop(buildStopAdditionalInfo(12D,12D,LocalDate.now(),"Одесса"),
                buildCommonInfo("Котовский_1","Котовский описание")));
        journey1.addStop(buildStop(buildStopAdditionalInfo(13D,13D,LocalDate.now(),"Винница"),
                buildCommonInfo("Винница_1","Винница описание")));
        journey1.addStop(buildStop(buildStopAdditionalInfo(130D,130D,LocalDate.now(),"Белая церковь"),
                buildCommonInfo("Белая церковь_1","Белая церковь описание")));

        journey2.addStop(buildStop(buildStopAdditionalInfo(12D,12D,LocalDate.now(),"Одесса2"),
                buildCommonInfo("Котовский_2","Котовский2 описание")));
        journey2.addStop(buildStop(buildStopAdditionalInfo(13D,13D,LocalDate.now(),"Винница2"),
                buildCommonInfo("Винница_2","Винница2 описание")));
        journey2.addStop(buildStop(buildStopAdditionalInfo(130D,130D,LocalDate.now(),"Белая церковь2"),
                buildCommonInfo("Белая церковь_2","Белая церковь2 описание")));


        journey2.addStop(buildStop(buildStopAdditionalInfo(99D,99D,LocalDate.now(),"99"),
                buildCommonInfo("99","99")));

        journey1.addVehicle(vehicle1);
        journey2.addVehicle(vehicle2);

        ticketClient.createOrUpdateJourney(journey1);
        ticketClient.createOrUpdateJourney(journey2);

        //Create Vehicle seats
        List<VehicleSeatEntity> Vehicle1seats = buildVehicleSeatEntity(journey1,vehicle1);

        for (int i = 0; i < Vehicle1seats.size(); i++) {
            ticketClient.createOrUpdateVehicleSeat(Vehicle1seats.get(i));
        }
        vehicle1.setVehicleSeats(Vehicle1seats);
        journey1.setVehicleSeats(Vehicle1seats);
        List<VehicleSeatEntity> Vehicle2seats = buildVehicleSeatEntity(journey2,vehicle2);
        for (int i = 0; i < Vehicle2seats.size(); i++) {
            ticketClient.createOrUpdateVehicleSeat(Vehicle2seats.get(i));
        }
        vehicle2.setVehicleSeats(Vehicle2seats);
        journey2.setVehicleSeats(Vehicle2seats);
        journey1.setActive(false);

        System.out.println(vehicle1);

        vehicle1.setName("bus_3");
        vehicle2.setActive(false);
//
        Vehicle1seats.get(5).setBooked(true);
        Vehicle2seats.get(7).setBooked(true);
//
        for (int i = 0; i < Vehicle1seats.size(); i++) {
            ticketClient.createOrUpdateVehicleSeat(Vehicle1seats.get(i));
        }
        for (int i = 0; i < Vehicle2seats.size(); i++) {
            ticketClient.createOrUpdateVehicleSeat(Vehicle2seats.get(i));
        }

        /*  Удаление  Vehicle */ //работает
//        ticketClient.removeVehicle(vehicle2);
//        ticketClient.removeVehicleById(vehicle1.getId());
//

        /* Удаление  информацию по Vehicle */
//        System.out.println("vehicle2"+ vehicle2.getVehicleSeats());
//        List<VehicleSeatEntity> vehicleSeat =  vehicle2.getVehicleSeats();
//        vehicleSeat.stream().forEach(item->ticketClient.removeVehicleSeat(item));
//
//
//
//        StopEntity stopEntity = journey2.getStops().get(3);


        /*  Удаление  stop*/
//        ticketClient.removeStop(stopEntity);
//        ticketClient.removeStopById(3L);
//        System.out.println("Vehicle3: " + ticketClient.findVehicleById(3L,true));
//        System.out.println(ticketClient.findVehicleByIds(true,1L,2L,3L));
//        System.out.println(ticketClient.findVehicleById(1L,true));
//        System.out.println("All: " + ticketClient.findAllVehicles());
//        System.out.println("Train_2: " + ticketClient.findAllVehiclesByName("Bus_1"));


        /*HomeWork 5*/
//        JourneyEntity
//        System.out.println(ticketClient.findAllJourney());
//        System.out.println(ticketClient.findAllJourneyAsNative());
//        System.out.println(ticketClient.findAllJourneyAsNamed());
//        System.out.println(ticketClient.findAllJourneyAsCriteria());
//        System.out.println(ticketClient.findAllJourneyAsStoredProcedure());

//        StopEntity
//        System.out.println(ticketClient.findAllStops());
//        System.out.println(ticketClient.findAllStopAsNative());
//        System.out.println(ticketClient.findAllStopsAsNamed());
//        System.out.println(ticketClient.findAllStopsAsCriteria());
//        System.out.println(ticketClient.findAllStopsAsStoredProcedure());

//        VehicleSeats
//        System.out.println(ticketClient.findAllVehicleSeats());
//        System.out.println(ticketClient.findAllVehicleSeatsAsNative());
//        System.out.println(ticketClient.findAllVehicleSeatsAsNamed());
//        System.out.println(ticketClient.findAllVehicleSeatsAsCriteria());
//        System.out.println(ticketClient.findAllVehicleSeatsAsStoredProcedure());

//        Vehicle
//        System.out.println(ticketClient.findAllVehicles());
//        System.out.println(ticketClient.findAllVehicleAsNative());
//        System.out.println(ticketClient.findAllVehiclesAsNamed());
//        System.out.println(ticketClient.findAllVehiclesAsCriteria());
        System.out.println(ticketClient.findAllVehiclesAsStoredProcedure());
    }

    private static JourneyEntity buildJourney(final String stationFrom, final String stationTo, final Instant departure, final Instant arrival,DirectionType direction,boolean active) {
        final JourneyEntity journeyEntity = new JourneyEntity();
        journeyEntity.setStationFrom(stationFrom);
        journeyEntity.setStationTo(stationTo);
        journeyEntity.setDeparture(departure);
        journeyEntity.setArrival(arrival);
        journeyEntity.setDirection(direction);
        journeyEntity.setActive(active);
        return journeyEntity;
    }
    private static StopEntity buildStop(StopAdditionalInfoEntity stopAdditionalInfoEntity, final CommonInfo commonInfo) {
        final StopEntity stopEntity = new StopEntity();
        stopEntity.setCommonInfo(commonInfo);
        stopEntity.setActive(true);
        stopEntity.addStopAdditionalInfo(stopAdditionalInfoEntity);
        return stopEntity;
    }
    private static StopAdditionalInfoEntity buildStopAdditionalInfo(final Double latitude, final Double longitude, final LocalDate buildDate,
                                            final String city){
        final StopAdditionalInfoEntity stopAdditionalInfoEntity = new StopAdditionalInfoEntity();
        stopAdditionalInfoEntity.setLatitude(latitude);
        stopAdditionalInfoEntity.setLongitude(longitude);
        stopAdditionalInfoEntity.setDateBuild(buildDate);
        stopAdditionalInfoEntity.setCity(city);
        return stopAdditionalInfoEntity;
    }

    private static List<VehicleSeatEntity> buildVehicleSeatEntity(final JourneyEntity journey,
                                                            final VehicleEntity vehicle){
       List<VehicleSeatEntity> list = new ArrayList<>();
        for (int i = 0; i <= vehicle.getMaxSeats() - 1; i++) {
            VehicleSeatEntity seatEntity = new VehicleSeatEntity();
            seatEntity.setJourney(journey);
            seatEntity.setVehicle(vehicle);
            seatEntity.setSeatNumber(i + 1);
            list.add(seatEntity);
        }
        return list;
    }

    private static VehicleEntity buildVehicle(final String name,int maxSeats) {
        final VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setName(name);
        vehicleEntity.setMaxSeats(maxSeats);
        return vehicleEntity;
    }

    private static CommonInfo buildCommonInfo( String name, String description) {
        CommonInfo commonInfo = new CommonInfo();
        commonInfo.setName(name);
        commonInfo.setDescription(description);
        return commonInfo;
    }
}
