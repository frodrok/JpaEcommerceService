package se.fredrikandthenurses.repository;

import se.fredrikandthenurses.model.AbstractEntity;

import java.util.List;

public interface CrudRepository<T extends AbstractEntity> {

    T saveOrUpdate(T entity);
    T remove(T entity);
    T find(Long id);
    List<T> getAll();

}
