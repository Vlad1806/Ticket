package org.hillel.service;

import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class NewTransactionalVehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;


    @Transactional(propagation = Propagation.REQUIRED)
    public VehicleEntity createOrUpdateVehicle(VehicleEntity vehicle){
//        return transactionTemplate.execute((status)-> vehicleRepository.createOrUpdate(vehicle));
//        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
//        final TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
//        try {
//            vehicleRepository.createOrUpdate(vehicle);
//            platformTransactionManager.commit(transactionStatus);
//        }
//        catch (TransactionException exception){
//            platformTransactionManager.rollback(transactionStatus);
//        }
//        EntityManager em = entityManagerFactory.createEntityManager();
//        final EntityTransaction transaction = em.getTransaction();
//        transaction.begin();
//        em.persist(vehicle);
//        transaction.commit();
//
//        transaction.rollback();
        final VehicleEntity orUpdate = vehicleRepository.createOrUpdate(vehicle);
        if (true) {
            throw new IllegalArgumentException("new exception");
        }
        return orUpdate;
    }

    @Transactional(readOnly = true)
    public Optional<VehicleEntity> findById(Long id,boolean withDependencies){
        final Optional<VehicleEntity> vehicle = vehicleRepository.findById(id);
        if (!vehicle.isPresent()) return vehicle;
        if (!withDependencies) return vehicle;
        final VehicleEntity vehicleEntity = vehicle.get();
        vehicleEntity.getVehicleSeats().size();
        return vehicle;
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findByIds(Long ...ids){
        return vehicleRepository.findByIds(ids);
    }

    @Transactional
    public void remove(VehicleEntity vehicleEntity){
        vehicleRepository.remove(vehicleEntity);
    }

    @Transactional
    public void removeById(Long id){
        vehicleRepository.removeById(id);
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAll(){
        return vehicleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findByName(String name){
        return vehicleRepository.findByName(name);
    }
}


