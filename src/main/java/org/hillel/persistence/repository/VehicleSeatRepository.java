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
        if (Objects.nonNull(vehicleEntity) && Objects.nonNull(journeyEntity)){
            if (!entityManager.contains(vehicleEntity) && !entityManager.contains(journeyEntity)){
                entity.setVehicle(vehicleEntity);
                entity.setJourney(journeyEntity);
            }
        }
        return super.createOrUpdate(entity);
    }

    @Override
    public void removeById(Long aLong) {
        super.removeById(aLong);
    }


    public void remove(VehicleSeatEntity entity) {
        super.remove(entity);
    }
}
