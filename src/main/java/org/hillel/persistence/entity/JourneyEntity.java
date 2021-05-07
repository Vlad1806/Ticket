package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hillel.persistence.entity.enums.DirectionType;

import javax.persistence.*;
import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "journey2")
//uniqueConstraints =
//@UniqueConstraint(name = "uniq_station_from_to",columnNames = {"station_from","station_to"}))
@Getter
@Setter
@NoArgsConstructor
@NamedQueries(value = {
        @NamedQuery(name = "findAllJourneyAsNamed",query = "from JourneyEntity")
})
@DynamicUpdate
@DynamicInsert
public class JourneyEntity extends AbstractModifyEntity<Long>{

    @Column(name = "station_from")
    private String stationFrom;

    @Column(name = "station_to",nullable = false)
    private String stationTo;

    @Column(name = "departure",nullable = false)
    private Instant departure;

    @Column(name = "arrival",nullable = false)
    private Instant arrival;


    @Column(name = "direction",length = 20)
    @Enumerated(EnumType.STRING)
    private DirectionType direction = DirectionType.TO;

    @ManyToOne(cascade = {CascadeType.PERSIST},fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicle;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinTable(name = "journey_stop",indexes = @Index(name = "journey_stop_inx",
            columnList = "journey_id,stop_id"),
        joinColumns = @JoinColumn(name = "journey_id"),
        inverseJoinColumns = @JoinColumn(name = "stop_id")
    )
    private List<StopEntity> stops = new ArrayList<>();

    public void addStop(final StopEntity stop){
        if (stop == null) throw new ArithmeticException("Stop must be set");
        if (stops == null) stops = new ArrayList<>();
        stops.add(stop);
        stop.addJourney(this);
    }

    public void addVehicle(final VehicleEntity vehicle){
        this.vehicle = vehicle;
    }

    @OneToMany(mappedBy = "journey",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<VehicleSeatEntity> vehicleSeats = new ArrayList<>();

    public void addVehicleSeat(final VehicleSeatEntity vehicleSeat){
        if (Objects.isNull(vehicleSeat)) throw new ArithmeticException("VehicleSeat must be set");
        if (Objects.isNull(vehicleSeats)) vehicleSeats = new ArrayList<>();
        vehicleSeats.add(vehicleSeat);
        vehicleSeat.addJourney(this);
    }


    @Override
    public String toString() {
        return new StringJoiner(",",JourneyEntity.class.getSimpleName() + "[","]")
                .add("stationFrom='" + stationFrom + "'")
                .add("stationTo='" + stationTo + "'")
                .add("departure='" + departure + "'")
                .add("arrival='" + arrival + "'")
                .add("direction='" + direction + "'")
                .add("vehicle='" + vehicle + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JourneyEntity)) return false;
        JourneyEntity journeyEntity = (JourneyEntity) o;
        return getId() != null && Objects.equals(getId(),journeyEntity.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
