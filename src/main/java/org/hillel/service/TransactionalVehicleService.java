package org.hillel.service;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransactionalVehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Transactional
    public VehicleEntity createOrUpdateVehicle(VehicleEntity vehicle){
        return vehicleRepository.createOrUpdate(vehicle);
    }

    @Transactional
    public Optional<VehicleEntity> findById(Long id,boolean withDependencies){
        final Optional<VehicleEntity> vehicle = vehicleRepository.findById(id);
        if (withDependencies && vehicle.isPresent()){
            final VehicleEntity vehicleEntity = vehicle.get();
            vehicleEntity.getVehicleSeats();
            vehicleEntity.getJourneys();
        }
        return vehicle;
    }

    @Transactional
    public void remove(VehicleEntity vehicleEntity){
        vehicleRepository.remove(vehicleEntity);
    }

    @Transactional
    public void removeById(Long id){
        vehicleRepository.removeById(id);
    }
}
