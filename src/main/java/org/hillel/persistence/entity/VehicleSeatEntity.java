package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hillel.persistence.entity.util.YesNoConvector;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vehicle_seat")
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "findAllVehicleSeatsAsNamed", query = "from VehicleSeatEntity")
}
)
@Getter
@Setter
@DynamicUpdate
@DynamicInsert
public class VehicleSeatEntity extends AbstractModifyEntity<Long>{

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private VehicleEntity vehicle;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinColumn(name = "journey_id", nullable = false)
    private JourneyEntity journey;

    @Column(name = "seat_number")
    private Integer seatNumber;

    @Column(name = "booked", nullable = false)
    @Convert(converter = YesNoConvector.class)
    private boolean booked;

    public void addVehicle(final VehicleEntity vehicle){
        if (Objects.isNull(vehicle)) throw new IllegalArgumentException("Vehicle must be set!");
        this.vehicle = vehicle;
    }

    public void addJourney(final JourneyEntity journey){
        if (Objects.isNull(journey)) throw new IllegalArgumentException("Journey must be set!");
        this.journey = journey;
    }

    public void removeAllJourneys(){
        //if (CollectionUtils.isEmpty(journeys))return;
        journey = null;
    }

    @Override
    public String toString() {
        return "VehicleSeatEntity{" +
                ", id=" + getId() +
                ", active=" + isActive() +
                ", seatNumber=" + seatNumber +
                ", booked=" + booked +
                '}';
    }
}
