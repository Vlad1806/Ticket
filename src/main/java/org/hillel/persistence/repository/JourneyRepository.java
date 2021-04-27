package org.hillel.persistence.repository;

import org.hibernate.Session;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.VehicleEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;
import java.util.Optional;

@Repository
public class JourneyRepository extends CommonRepository<JourneyEntity,Long>{

    protected JourneyRepository() {
        super(JourneyEntity.class);
    }

    @Override
    public JourneyEntity createOrUpdate(JourneyEntity entity) {
        VehicleEntity vehicle = entity.getVehicle();
        if (Objects.nonNull(vehicle)){
            if (!entityManager.contains(vehicle)){
              entity.setVehicle(entityManager.merge(vehicle));
            }
        }
        return super.createOrUpdate(entity);
    }

//    public EntityManager getEntityManager(){
//        return entityManager;
//    }

//
    //////////Lesson 5 mapping_2
//    @PersistenceContext
//    private EntityManager entityManager;
//
//
//    public Long create(final JourneyEntity journeyEntity){
//        if (Objects.isNull(journeyEntity)) throw new IllegalArgumentException("journeyEntity must be set");
//        entityManager.persist(journeyEntity);
//        return journeyEntity.getId();
//    }
//
//    public Optional<JourneyEntity> findById(Long id) {
////        Session session = entityManager.unwrap(Session.class);
////        final JourneyEntity value = session.byMultipleIds(JourneyEntity.class).multiLoad(id).get(0) ;
//        return Optional.ofNullable(entityManager.find(JourneyEntity.class,id));
//    }
//
//    public JourneyEntity save(JourneyEntity journey) {
//        final JourneyEntity merge = entityManager.merge(journey);
////        entityManager.flush();
//        return merge;
//    }
}
