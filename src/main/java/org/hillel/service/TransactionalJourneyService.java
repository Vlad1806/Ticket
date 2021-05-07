package org.hillel.service;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;


@Service(value = "TransactionalJourneyService")
public class TransactionalJourneyService{

    @Autowired
    private JourneyRepository journeyRepository;



    @Transactional
    public JourneyEntity createOrUpdateJourney(final JourneyEntity journeyEntity ){
        //if (Objects.isNull(journeyEntity)) throw new IllegalArgumentException("journeyEntity must be set for creation!");
//        System.out.println("Create journey ");
//        final JourneyEntity orUpdate = journeyRepository.createOrUpdate(journeyEntity);
//        System.out.println("Get journey by id ");
//        journeyRepository.getEntityManager().flush();
//        if (journeyEntity.getId() == 1) {
//            throw new IllegalArgumentException("Error flush");
//        }
//        JourneyEntity journey = journeyRepository.findById(orUpdate.getId()).get();
//        System.out.println("remove journey by id ");
//        journeyRepository.removeById(journey.getId());
//
//        JourneyEntity journey2 = new JourneyEntity();
//        journey2.setArrival(orUpdate.getArrival());
//        journey2.setDeparture(orUpdate.getDeparture());
//        journey2.setStationFrom(orUpdate.getStationFrom());
//        journey2.setStationTo(orUpdate.getStationTo());
//        journey2.setActive(false);

//        boolean isNew = Objects.isNull(journeyEntity.getId());
//        if (!isNew)
        return journeyRepository.createOrUpdate(journeyEntity);
    }


//    @Transactional
//    //@Override
//    public Long createJourney(final JourneyEntity journeyEntity){
//        if (Objects.isNull(journeyEntity)) throw new IllegalArgumentException("journeyEntity must be set for creation!");
//        return journeyRepository.create(journeyEntity);
//    }

    @Transactional(readOnly = true)
    public Optional<JourneyEntity> findById(Long id,boolean withDependencies) {
        final Optional<JourneyEntity> byId = journeyRepository.findById(id);
        if (withDependencies && byId.isPresent()){
            final JourneyEntity journeyEntity = byId.get();
            journeyEntity.getVehicle().getName();
            journeyEntity.getStops().size();
        }
        return byId;
    }
    @Transactional
    public void remove(JourneyEntity journey) {
        journeyRepository.remove(journey);
    }
    @Transactional
    public void removeById(Long journeyId) {
        journeyRepository.removeById(journeyId);
     }
// Lesson 5 mappping_2
//    @Transactional
//    public void save(JourneyEntity journey){
//        final JourneyEntity save = journeyRepository.save(journey);
//        save.setStationFrom("test station from");
//    }




    /*HomeWork 5*/
    @Transactional(readOnly = true)
    public Collection<JourneyEntity> findAll(){
        final Collection<JourneyEntity> all = journeyRepository.findAll();
        journeyDependencies(all);
//      all.forEach((x)->x.getVehicle());
//      all.forEach(x->x.getVehicle().getVehicleSeats().size());
        return all;
    }


    @Transactional(readOnly = true)
    public Collection<JourneyEntity> findAllAsNative(){
        final Collection<JourneyEntity> allAsNative = journeyRepository.findAllAsNative();
        journeyDependencies(allAsNative);
        return allAsNative;
    }

    private void journeyDependencies(Collection<JourneyEntity> all){
        for (JourneyEntity entity2 :all) {
            entity2.getVehicle();
            entity2.getVehicle().getVehicleSeats().size();
        }
    }
    @Transactional(readOnly = true)
    public Collection<JourneyEntity> findAllJourneyAsNamed(){
        final Collection<JourneyEntity> allJourneyAsNamed = journeyRepository.findAllAsNamed();
        journeyDependencies(allJourneyAsNamed);
        return allJourneyAsNamed;
    }


    @Transactional(readOnly = true)
    public Collection<JourneyEntity> findAllAsCriteria(){
        final Collection<JourneyEntity> allAsCriteria = journeyRepository.findAllAsCriteria();
        journeyDependencies(allAsCriteria);
        return allAsCriteria;
    }

    @Transactional(readOnly = true)
    public Collection<JourneyEntity> findAllAsStoredProcedure(){
        final Collection<JourneyEntity> allAsStoredProcedure = journeyRepository.findAllAsStoredProcedure();
        journeyDependencies(allAsStoredProcedure);
        return allAsStoredProcedure;
    }

}






