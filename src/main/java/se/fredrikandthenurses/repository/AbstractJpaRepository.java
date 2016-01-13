package se.fredrikandthenurses.repository;

import se.fredrikandthenurses.exception.RepositoryException;
import se.fredrikandthenurses.model.AbstractEntity;

import javax.persistence.*;
import java.util.List;

public abstract class AbstractJpaRepository<E extends AbstractEntity> implements CrudRepository<E> {

    private final EntityManagerFactory factory;
    private final Class<E> entityClass;
    private EntityManager manager;

    protected AbstractJpaRepository(EntityManagerFactory emf, Class<E> entityClass) {
        this.factory = emf;
        this.entityClass = entityClass;
    }

    public E saveOrUpdate(E entity) {
        manager = factory.createEntityManager();

        try {
            manager.getTransaction().begin();
            if (entity.getId() == null) {
                manager.persist(entity);
            } else {
                manager.merge(entity);
            }

            manager.getTransaction().commit();

        } catch (PersistenceException e) {
            throw new RepositoryException("Could not persist entity: " + entityClass.getSimpleName(), e.getCause());
        } finally {
            manager.close();
        }
        return entity;
    }

    public E remove(E entity){
        manager = factory.createEntityManager();

        try {
            manager.getTransaction().begin();
            manager.remove(manager.contains(entity)? entity: manager.merge(entity));
            manager.getTransaction().commit();

        } catch (PersistenceException e) {
            throw new RepositoryException("Could not remove entity: " + entityClass.getSimpleName(), e.getCause());
        } finally {
            manager.close();
        }

        return entity;
    }

    public E find(Long id) {
        E entityToReturn = null;
        manager = factory.createEntityManager();
        try {
            entityToReturn = manager.find(entityClass, id);
        } catch (PersistenceException e) {
            throw new RepositoryException("Could not find entity: " + entityClass.getSimpleName(), e.getCause());
        } finally {
            manager.close();
        }
        return entityToReturn;
    }

    protected E query(String queryName, Object ...parameters) throws PersistenceException{
        manager = factory.createEntityManager();
        TypedQuery<E> query = manager.createNamedQuery(queryName, entityClass);
        for (int i = 0; i < parameters.length ; i++) {
            query.setParameter(i+1, parameters[i]);
        }
        E entityToReturn = query.getSingleResult();
        manager.close();
        return entityToReturn;
    }

    protected List<E> queryForList(String queryName, Object ...parameters) throws PersistenceException{
        manager = factory.createEntityManager();
        TypedQuery<E> query = manager.createNamedQuery(queryName, entityClass);
        for (int i = 0; i < parameters.length; i++) {
            query.setParameter(i+1, parameters[i]);
        }
        List<E> entityList =  query.getResultList();
        manager.close();
        return entityList;
    }

}
