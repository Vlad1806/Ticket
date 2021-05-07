package org.hillel.service;

import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.entity.VehicleSeatEntity;
import org.hillel.persistence.repository.VehicleSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionalVehicleSeatService {

    @Autowired
    VehicleSeatRepository vehicleSeatRepository;

    @Transactional
    public VehicleSeatEntity createOrUpdateVehicleSeat(VehicleSeatEntity vehicle){
        return vehicleSeatRepository.createOrUpdate(vehicle);
    }

    @Transactional
    public Optional<VehicleSeatEntity> findById(Long id, boolean withDependencies){
            final Optional<VehicleSeatEntity> vehicleSeat = vehicleSeatRepository.findById(id);
            if (withDependencies && vehicleSeat.isPresent()){
                final VehicleSeatEntity  vehicleSeatEntity = vehicleSeat.get();
                vehicleSeatEntity.getVehicle();
                vehicleSeatEntity.getJourney();
            }
            return vehicleSeat;
    }

    @Transactional(readOnly = true)
    public Collection<VehicleSeatEntity> findAll(){
        return vehicleSeatRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Collection<VehicleSeatEntity> findAllAsNative(){
        return vehicleSeatRepository.findAllAsNative();
    }
    @Transactional(readOnly = true)
    public Collection<VehicleSeatEntity> findAllAsNamed(){
        return vehicleSeatRepository.findAllAsNamed();
    }
    @Transactional(readOnly = true)
    public Collection<VehicleSeatEntity> findAllAsCriteria(){
        return vehicleSeatRepository.findAllAsCriteria();
    }

    @Transactional(readOnly = true)
    public Collection<VehicleSeatEntity> findAllAsStoredProcedure(){
        return vehicleSeatRepository.findAllAsStoredProcedure();
    }






    @Transactional
    public void remove(VehicleSeatEntity vehicleSeatEntityy) {
         vehicleSeatRepository.remove(vehicleSeatEntityy);
    }
}