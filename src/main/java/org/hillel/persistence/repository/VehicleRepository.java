package org.hillel.persistence.repository;


import org.hillel.persistence.entity.VehicleEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class VehicleRepository extends CommonRepository<VehicleEntity,Long> {


    protected VehicleRepository() {
        super(VehicleEntity.class);
    }


    @Override
    public void remove(VehicleEntity entity) {
        entity = findById(entity.getId()).get();
        entity.removeAllJourneys();
        super.remove(entity);
    }

    @Override
    public void removeById(Long id) {
        Optional<VehicleEntity> vehicleEntity = findById(id);
        vehicleEntity.get().removeAllJourneys();
        super.removeById(id);
    }
}
