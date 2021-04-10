package org.hillel.persistence.repository;

import org.hillel.persistence.entity.JourneyEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

@Repository
public class JourneyRepository  {

    @PersistenceContext
    private EntityManager entityManager;

    public Long create(final JourneyEntity journeyEntity){
        if (Objects.isNull(journeyEntity)) throw new IllegalArgumentException("journeyEntity must be set");
        entityManager.persist(journeyEntity);
        return journeyEntity.getId();
    }
}
