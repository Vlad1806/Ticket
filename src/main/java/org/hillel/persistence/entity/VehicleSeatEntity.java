package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vehicle_seat")
@NoArgsConstructor
@Getter
@Setter
@DynamicUpdate
@DynamicInsert
public class VehicleSeatEntity extends AbstractModifyEntity<Long>{

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private VehicleEntity vehicle;

    @ManyToOne
    @JoinColumn(name = "journey_id", nullable = false)
    private JourneyEntity journey;

    @Column(name = "free_seats",nullable = false)
    private int freeSeats;
    @Id
    private Long id;

    public void addVehicle(final VehicleEntity vehicle){
        if (Objects.isNull(vehicle)) throw new IllegalArgumentException("Vehicle must be set!");
        this.vehicle = vehicle;
    }

    public void addJourney(final JourneyEntity journey){
        if (Objects.isNull(journey)) throw new IllegalArgumentException("Journey must be set!");
        this.journey = journey;
    }


    @Override
    public String toString() {
        return "VehicleSeat{" +
                "vehicle=" + vehicle +
                ", freeSeats=" + freeSeats +
                '}';
    }

}
