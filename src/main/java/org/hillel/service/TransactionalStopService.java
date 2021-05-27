package org.hillel.service;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.entity.enums.SqlType;
import org.hillel.persistence.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service(value = "TransactionalStopService")
public class TransactionalStopService {

    @Autowired
    private StopRepository stopRepository;


    @Transactional
    public StopEntity createOrUpdateStop(StopEntity stopEntity){
        return stopRepository.createOrUpdate(stopEntity);
    }

    @Transactional(readOnly = true)
    Optional<StopEntity> findById(Long id,boolean withDependencies){
        final Optional<StopEntity> stop = stopRepository.findById(id);
        if (withDependencies && stop.isPresent()){
            final StopEntity stopEntity = stop.get();
            stopEntity.getCommonInfo();
            stopEntity.getJourneys();
        }
        return stop;
    }

    @Transactional
    public void remove(StopEntity stopEntity){
        stopRepository.remove(stopEntity);
    }
    @Transactional
    public void removeById(Long id) {
        stopRepository.removeById(id);
    }


    @Transactional(readOnly = true)
    public Collection<StopEntity> findAll(SqlType sqlType){
        return stopRepository.findAll(sqlType);
    }
    
    /*HomeWork 6*/
    @Transactional(readOnly = true)
    public Collection<StopEntity> findAll(SqlType sql, int startPage, int sizePage, String field, boolean orderType){
        final Collection<StopEntity> all = stopRepository.findAll(sql,startPage,sizePage,field,orderType);
        return all;
    }

//    @Transactional
//    public Long createStop(final StopEntity stopEntity) {
//        if (Objects.isNull(stopEntity))
//            throw new IllegalArgumentException("journeyEntity must be set for creation!");
//        return stopRepository.create(stopEntity);
//    }
}
