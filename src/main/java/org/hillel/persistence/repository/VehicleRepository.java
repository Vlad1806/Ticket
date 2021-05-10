package org.hillel.persistence.repository;
import org.hillel.persistence.entity.VehicleEntity;

import org.springframework.stereotype.Repository;

import java.util.Collection;
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

//    @Override
//    public Collection<VehicleEntity> findAll() {
////        return entityManager.createNamedQuery("findAll",VehicleEntity.class).getResultList();
//        return entityManager.createNamedStoredProcedureQuery("findAllVehicle").getResultList();
//    }


    @Override
    public Collection<VehicleEntity> findByName(String name) {
//        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        final CriteriaQuery<VehicleEntity> query = criteriaBuilder.createQuery(VehicleEntity.class);
//        final Root<VehicleEntity> from = query.from(VehicleEntity.class);
//        final Join<Object, Object> journeys = from.join(VehicleEntity_.JOURNEYS, JoinType.LEFT);
//        final Predicate byJourneyName = criteriaBuilder.equal(journeys.get(JourneyEntity_.STATION_TO), criteriaBuilder.parameter(String.class,"stationFromParam"));
//        journeys.on(byJourneyName);
//        final Predicate byName = criteriaBuilder.equal(from.get(VehicleEntity_.NAME), criteriaBuilder.parameter(String.class,"nameParam"));
//        final Predicate byActive = criteriaBuilder.equal(from.get(VehicleEntity_.ACTIVE), criteriaBuilder.parameter(Boolean.class,"activeParam"));
//        return entityManager.createQuery(query.
//                select(from).
//                where(byName,byActive).orderBy(new OrderImpl(from.get(VehicleEntity_.ID),false)))
//                .setParameter( "nameParam",name)
//                .setParameter("activeParam",true)
//                .setParameter("stationFromParam","Львов")
//                .getResultList();

        return entityManager
                .createQuery("select v from VehicleEntity v left join v.journeys js on js.vehicle.id = v.id order by v.id asc",VehicleEntity.class)
                .setFirstResult(2)
                .setMaxResults(3)
                .getResultList();
    }


    public Collection<VehicleEntity> findVehicleByMinSeats(){
        return  entityManager.createNativeQuery(
                "select * from vehicle where id = " +
                       "(select vehicle_id from ( " +
                "SELECT vehicle_id, MIN(COUNT(*)) OVER (partition by vehicle_id) FROM vehicle_seat " +
                "where booked = 'no' " +
                "GROUP BY vehicle_id " +
                "order by min asc " +
                "limit 1) k)"
        ,VehicleEntity.class).getResultList();
    }

    public Collection<VehicleEntity> findVehicleByMaxSeats(){
        return  entityManager.createNativeQuery(
                "select * from vehicle where id = " +
                        "(select vehicle_id from ( " +
                        "SELECT vehicle_id, Max(COUNT(*)) OVER (partition by vehicle_id) FROM vehicle_seat " +
                        "where booked = 'no' " +
                        "GROUP BY vehicle_id " +
                        "order by max desc " +
                        "limit 1) k)"
                ,VehicleEntity.class).getResultList();
    }

}
