package org.hillel.service;

import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.entity.enums.SqlType;
import org.hillel.persistence.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;


import java.util.Collection;

import java.util.Optional;

@Service
public class TransactionalVehicleService{

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
        vehicle.get().getVehicleSeats().size();
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


    /*HomeWork 6*/
    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAll(SqlType sql, int startPage, int sizePage, String field, boolean orderType){
        final Collection<VehicleEntity> all = vehicleRepository.findAll(sql,startPage,sizePage,field,orderType);
        vehicleDependencies(all);
        return all;
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findVehicleByMinSeats(){
        final Collection<VehicleEntity> all = vehicleRepository.findVehicleByMinSeats();
        vehicleDependencies(all);
        return all;
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findVehicleByMaxSeats(){
        final Collection<VehicleEntity> all = vehicleRepository.findVehicleByMaxSeats();
        vehicleDependencies(all);
        return all;
    }


    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAll(SqlType sqlType){
        Collection<VehicleEntity> all;
        switch (sqlType){
            case HQL:{
                all = vehicleRepository.findAll();
                break;
            }
            case SQL: {
                all = vehicleRepository.findAllAsNative();
                break;
            }
            case NAMED_QUERY:{
                all = vehicleRepository.findAllAsNamed();
                break;
            }
            case STORE_PROCEDURE:{
                all = vehicleRepository.findAllAsStoredProcedure();
                break;
            }
            case CRITERIA:{
                all = vehicleRepository.findAllAsCriteria();
                break;
            }
            default: throw new IllegalArgumentException("Incorrect sql type!!!");
        }
        if (all.isEmpty()) return all;
        vehicleDependencies(all);
        return all;
    }

//    @Autowired
//    private NewTransactionalVehicleService newTransactionalVehicleService;

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
