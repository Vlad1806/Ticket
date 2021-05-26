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

        JourneyEntity journey3 = buildJourney("Одесса","Харьков",
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

        journey3.addStop(buildStop(buildStopAdditionalInfo(929D,929D,LocalDate.now(),"929"),
                buildCommonInfo("929","929")));


        ticketClient.createOrUpdateJourney(journey1);
        ticketClient.createOrUpdateJourney(journey2);
        ticketClient.createOrUpdateJourney(journey3);

        //Create Vehicle seats
        List<VehicleSeatEntity> vehicle1seats = buildVehicleSeatEntity(journey1,vehicle1);

        for (int i = 0; i < vehicle1seats.size(); i++) {
            vehicle1seats.get(i).setBooked(true);
            ticketClient.createOrUpdateVehicleSeat(vehicle1seats.get(i));
        }
        vehicle1.setVehicleSeats(vehicle1seats);
        journey1.setVehicleSeats(vehicle1seats);
        journey1.setVehicle(vehicle1);

        List<VehicleSeatEntity> vehicle2seats = buildVehicleSeatEntity(journey2,vehicle2);

         for (int i = 0; i < vehicle2seats.size(); i++) {
            vehicle2seats.get(i).setBooked(true);
            ticketClient.createOrUpdateVehicleSeat(vehicle2seats.get(i));
        }
        vehicle2.setVehicleSeats(vehicle2seats);
        journey2.setVehicleSeats(vehicle2seats);
        journey2.setVehicle(vehicle2);

        List<VehicleSeatEntity> vehicle3seats = buildVehicleSeatEntity(journey3,vehicle3);

        for (int i = 0; i < vehicle3seats.size(); i++) {
            ticketClient.createOrUpdateVehicleSeat(vehicle3seats.get(i));
        }
        vehicle3.setVehicleSeats(vehicle3seats);
        journey3.setVehicleSeats(vehicle3seats);
        journey3.setVehicle(vehicle3);

        journey1.setActive(false);

        vehicle1.setName("bus_3");
        vehicle2.setActive(false);

        vehicle1seats.get(5).setBooked(false);
        vehicle1seats.get(6).setBooked(false);

        vehicle2seats.get(7).setBooked(false);
        vehicle2seats.get(9).setBooked(false);

//        vehicle3seats.get(1).setBooked(false);
        vehicle3seats.get(2).setBooked(false);

        ticketClient.createOrUpdateVehicleSeat(vehicle1seats.get(5));
        ticketClient.createOrUpdateVehicleSeat(vehicle1seats.get(6));

        ticketClient.createOrUpdateVehicleSeat(vehicle2seats.get(7));
        ticketClient.createOrUpdateVehicleSeat(vehicle2seats.get(9));


        ticketClient.createOrUpdateVehicleSeat(vehicle3seats.get(2));
//        ticketClient.createOrUpdateVehicleSeat(vehicle3seats.get(1));


//        /*  Удаление  Vehicle */
//        ticketClient.removeVehicle(vehicle2);
//        ticketClient.removeVehicleById(vehicle1.getId());
//
//        /* Удаление  информацию по Vehicle */
//        System.out.println("vehicle3"+ vehicle3.getVehicleSeats());
//       List<VehicleSeatEntity> vehicleSeat =  vehicle3.getVehicleSeats();
//        vehicleSeat.stream().forEach(item->ticketClient.removeVehicleSeat(item));
//

        StopEntity stopEntity = journey1.getStops().get(2);
        System.out.println(stopEntity);
        ticketClient.createOrUpdateStop(stopEntity);

        /*  Удаление  stop*/
        ticketClient.removeStop(stopEntity);

//        ticketClient.removeStopById(3L);

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
            seatEntity.setBooked(true);
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
