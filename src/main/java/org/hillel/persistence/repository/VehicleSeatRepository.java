package org.hillel.persistence.repository;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.entity.VehicleSeatEntity;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Objects;

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

    @Override
    public Collection<VehicleSeatEntity> findAllAsNamed() {
        return entityManager.createNamedQuery("findAllVehicleSeatsAsNamed",VehicleSeatEntity.class).getResultList();
    }

    @Override
    public void removeById(Long aLong) {
        super.removeById(aLong);
    }


    public void remove(VehicleSeatEntity entity) {
        super.remove(entity);
    }
}
