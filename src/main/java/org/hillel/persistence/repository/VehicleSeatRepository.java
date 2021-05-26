package org.hillel.persistence.repository;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.entity.VehicleSeatEntity;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
public class VehicleSeatRepository extends CommonRepository<VehicleSeatEntity,Long> {

    protected VehicleSeatRepository() {
        super(VehicleSeatEntity.class);
    }

    @Override
    public VehicleSeatEntity createOrUpdate(VehicleSeatEntity entity) {
        VehicleEntity vehicleEntity = entity.getVehicle();
        JourneyEntity journeyEntity = entity.getJourney();
        if (Objects.nonNull(vehicleEntity)){
            if (!entityManager.contains(vehicleEntity)){
                entity.setVehicle(entityManager.merge(vehicleEntity));
            }
        }
        if (Objects.nonNull(journeyEntity)){
            if (!entityManager.contains(journeyEntity)){
                entity.setJourney(entityManager.merge(journeyEntity));
            }
        }
        return super.createOrUpdate(entity);
    }
}
