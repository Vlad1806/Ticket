package org.hillel.persistence.repository;

import lombok.SneakyThrows;
import org.hillel.persistence.entity.AbstractModifyEntity;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

public abstract class CommonRepository<E extends AbstractModifyEntity<ID>,ID extends Serializable> implements GenericRepository<E, ID> {

    @PersistenceContext
    protected EntityManager entityManager;
    private final Class<E> entityClass;

    protected CommonRepository(Class<E> entityClass) {
        this.entityClass = entityClass;
    }


    @Override
    public E createOrUpdate(E entity) {
        Assert.notNull(entity, "entity must be set");
        if (Objects.isNull(entity.getId())) {
            entityManager.persist(entity);
        } else {
            return entityManager.merge(entity);
        }
        return entity;
    }

    @Override
    public Optional<E> findById(ID id) {
        if (Objects.isNull(id)) return Optional.empty();
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    @SneakyThrows
    @Override
    public void removeById(ID id) {
//        E entity  = entityClass.getDeclaredConstructor().newInstance();
//        entity.setId(id);
        final E reference = entityManager.getReference(entityClass, id);
        entityManager.remove(reference);
    }

    @Override
    public void remove(E entity) {
        if (entityManager.contains(entity)) {
            entityManager.remove(entity);
        } else {
            removeById(entity.getId());
        }
    }
}
