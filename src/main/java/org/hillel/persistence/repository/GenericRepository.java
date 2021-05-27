package org.hillel.persistence.repository;

import org.hillel.persistence.entity.enums.SqlType;

import javax.swing.text.html.parser.Entity;
import java.util.Collection;
import java.util.Optional;

public interface GenericRepository<E, ID> {

    E createOrUpdate(E entity);

    Optional<E> findById(ID id);

    void removeById(ID id);

    void remove(E entity);

    Collection<E> findByIds(ID ... ids);

    Collection<E> findByName(String name);

    Collection<E> findAll();

    Collection<E> findAll(SqlType sql);

//    Collection<E> findAllAsNative();
//
//    Collection<E> findAllAsNamed();
//
//    Collection<E> findAllAsCriteria();
//
//    Collection<E> findAllAsStoredProcedure();
}
