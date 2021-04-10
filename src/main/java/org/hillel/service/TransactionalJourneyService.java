package org.hillel.service;

import org.hillel.Journey;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@Service
public class TransactionalJourneyService {

    @Autowired
    private JourneyRepository journeyRepository;



    @Transactional
    public Long createJourney(final JourneyEntity journeyEntity){
        if (Objects.isNull(journeyEntity)) throw new IllegalArgumentException("journeyEntity must be set for creation!");
        return journeyRepository.create(journeyEntity);
    }
}
