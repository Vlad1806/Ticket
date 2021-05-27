package org.hillel.service;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.entity.VehicleSeatEntity;
import org.hillel.persistence.entity.enums.SqlType;
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
    public Collection<VehicleSeatEntity> findAll(SqlType sqlType){
        return vehicleSeatRepository.findAll(sqlType);
    }

    /*HomeWork 6*/
    @Transactional(readOnly = true)
    public Collection<VehicleSeatEntity> findAll(SqlType sql, int startPage, int sizePage, String field, boolean orderType){
        return vehicleSeatRepository.findAll(sql,startPage,sizePage,field,orderType);
    }

    @Transactional
    public void remove(VehicleSeatEntity vehicleSeatEntity) {
         vehicleSeatRepository.remove(vehicleSeatEntity);
    }
}
