package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
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
@NamedQueries(value = {
        @NamedQuery(name = "findAllVehicleAsNamed",query = "from VehicleEntity")
})
@NamedStoredProcedureQueries(
        @NamedStoredProcedureQuery(
                name = "findAllVehicle",
                procedureName = "find_all_vehicles",
                parameters = @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR,type = Class.class),
                resultClasses = VehicleEntity.class
        )
)
public class VehicleEntity extends AbstractModifyEntity<Long> {

    @Column(name = "name")
    private String name;

    @Column(name = "max_seats")
    private int maxSeats;

    @OneToMany(mappedBy = "vehicle",cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    List<JourneyEntity> journeys = new ArrayList<>();
//    Set<JourneyEntity> journeys = new HashSet<>();
    public void addJourney(final JourneyEntity journeyEntity){
        if (journeyEntity == null) {
            journeys = new ArrayList<>();
        }
        journeys.add(journeyEntity);
        journeyEntity.addVehicle(this);
    }

    @OneToMany(mappedBy = "vehicle",cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY,orphanRemoval = true)
    private List<VehicleSeatEntity> vehicleSeats = new ArrayList<>();


    public void addVehicleSeat(final VehicleSeatEntity vehicleSeat){
        if (Objects.isNull(vehicleSeat)) throw new ArithmeticException("VehicleSeat must be set");
        if (Objects.isNull(vehicleSeats)) vehicleSeats = new ArrayList<>();
        vehicleSeats.add(vehicleSeat);
        vehicleSeat.addVehicle(this);
    }

//    public void removeAllJVehicleSeat(){
//        if (CollectionUtils.isEmpty(vehicleSeats))return;
//        vehicleSeats.forEach(item -> item.se(null));
//    }
    public void removeAllJourneys(){
        if (CollectionUtils.isEmpty(journeys))return;
        journeys.forEach(item -> item.setVehicle(null));
    }

    @Override
    public String toString() {
        return "VehicleEntity{" +
                "id='" + getId() + '\'' +
                "name='" + name + '\'' +
                ", vehicleSeats=" + vehicleSeats +
                '}';
    }
}
