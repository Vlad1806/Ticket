package org.hillel.persistence.repository;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.entity.VehicleEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

@Repository
public class StopRepository extends CommonRepository<StopEntity,Long>{
    protected StopRepository() {
        super(StopEntity.class);
    }

    @Override
    public StopEntity createOrUpdate(StopEntity entity) {
//        JourneyEntity journey =  entity.get;
//        if (Objects.nonNull(vehicle)){
//            if (!entityManager.contains(vehicle)){
//                entity.setVehicle(entityManager.merge(vehicle));
//            }
//        }
        return super.createOrUpdate(entity);
    }


    ///Lesson 5 mapping_2
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    public Long create(final StopEntity stopEntity){
//        if (Objects.isNull(stopEntity)) throw new IllegalArgumentException("stopEntity must be set");
//        entityManager.persist(stopEntity);
//        return stopEntity.getId();
//    }
}
