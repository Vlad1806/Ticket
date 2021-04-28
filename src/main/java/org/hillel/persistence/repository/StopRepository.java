package org.hillel.persistence.repository;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopAdditionalInfoEntity;
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

//    @Override
//    public StopEntity createOrUpdate(StopEntity entity) {
//        StopAdditionalInfoEntity stopAdd =  entity.getAdditionalInfo();
//        if (Objects.nonNull(stopAdd)){
//            if (!entityManager.contains(stopAdd)){
//                entity.setAdditionalInfo(entityManager.merge(stopAdd));
//            }
//        }
//        return super.createOrUpdate(entity);
//    }

    @Override
    public void remove(StopEntity entity) {
        entity = findById(entity.getId()).get();
        entity.removeAllJourneys();
        super.remove(entity);
    }

    @Override
    public void removeById(Long id) {
        StopEntity stopEntity = findById(id).get();
        stopEntity.removeAllJourneys();
        super.removeById(id);
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
