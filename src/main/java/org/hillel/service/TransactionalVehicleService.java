package org.hillel.service;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionalVehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

//    @Autowired
//    private TransactionTemplate transactionTemplate;
//
//    @Autowired
//    PlatformTransactionManager platformTransactionManager;
//    @PersistenceContext
//    private EntityManagerFactory entityManagerFactory;

    @Transactional
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
        return vehicleRepository.createOrUpdate(vehicle);
    }
    private void vehicleDependencies(Collection<VehicleEntity> all){
        for (VehicleEntity entity2 :all) {
            entity2.getVehicleSeats().size();
        }
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
    public Collection<VehicleEntity> findByIds(boolean withDependencies, Long... ids){
        final Collection<VehicleEntity> byIds = vehicleRepository.findByIds(ids);
        if (byIds.isEmpty()) return byIds;
        if (!withDependencies) return byIds;
        vehicleDependencies(byIds);
        return byIds;
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
        final Collection<VehicleEntity> all = vehicleRepository.findAll();
        if (all.isEmpty()) return all;
        vehicleDependencies(all);
        return all;
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAllAsNative(){
        final Collection<VehicleEntity> allAsNative = vehicleRepository.findAllAsNative();
        vehicleDependencies(allAsNative);
        return allAsNative;
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAllAsNamed(){
        final Collection<VehicleEntity> allVehicleAsNamed = vehicleRepository.findAllAsNamed();
        vehicleDependencies(allVehicleAsNamed);
        return allVehicleAsNamed;
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAllAsCriteria(){
        final Collection<VehicleEntity> allAsCriteria = vehicleRepository.findAllAsCriteria();
        vehicleDependencies(allAsCriteria);
        return allAsCriteria;
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAllAsStoredProcedure(){
        final Collection<VehicleEntity> allAsStoredProcedure = vehicleRepository.findAllAsStoredProcedure();
        vehicleDependencies(allAsStoredProcedure);
        return allAsStoredProcedure;
    }


    @Autowired
    private NewTransactionalVehicleService newTransactionalVehicleService;

    @Transactional(readOnly = true)
//            (noRollbackFor = IllegalArgumentException.class)
    public Collection<VehicleEntity> findByName(String name){
        final Collection<VehicleEntity> byName = vehicleRepository.findByName(name);
//        final VehicleEntity next = byName.iterator().next();
//        next.setName(String.valueOf(System.currentTimeMillis()));
//        System.out.println("save vehicle with id: " + next.getId() + " and new value " + next.getName());
//        newTransactionalVehicleService.createOrUpdateVehicle(next);
        if (byName.isEmpty()) return byName;
        vehicleDependencies(byName);
        return byName;
    }
}
