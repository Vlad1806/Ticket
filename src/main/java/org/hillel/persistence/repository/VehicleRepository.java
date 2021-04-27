package org.hillel.persistence.repository;


import org.hillel.persistence.entity.VehicleEntity;
import org.springframework.stereotype.Repository;

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
}
