package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
public class VehicleEntity extends AbstractModifyEntity<Long> {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "vehicle",cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    List<JourneyEntity> journeys = new ArrayList<>();
//    Set<JourneyEntity> journeys = new HashSet<>();

    public void addJourney(final JourneyEntity journeyEntity){
        if (journeyEntity == null) {
            journeys = new ArrayList<>();
        }
        journeys.add(journeyEntity);
        journeyEntity.setVehicle(this);
    }

    @OneToMany(mappedBy = "vehicle",cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    private List<VehicleSeatEntity> vehicleSeats = new ArrayList<>();

    public void addSeat(VehicleSeatEntity seatEntity){
        if (seatEntity == null) return;
        if (vehicleSeats == null) vehicleSeats = new ArrayList<>();
        vehicleSeats.add(seatEntity);
        seatEntity.setVehicle(this);
    }



    public void addVehicleSeat(final VehicleSeatEntity vehicleSeat){
        if (Objects.isNull(vehicleSeat)) throw new ArithmeticException("VehicleSeat must be set");
        if (Objects.isNull(vehicleSeats)) vehicleSeats = new ArrayList<>();
        vehicleSeats.add(vehicleSeat);
        vehicleSeat.addVehicle(this);
    }


    public void removeAllJourneys(){
        if (CollectionUtils.isEmpty(journeys))return;
        journeys.forEach(item -> item.setVehicle(null));
    }

    @Override
    public String toString() {
        return "VehicleEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
