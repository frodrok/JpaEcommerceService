package se.fredrikandthenurses.repository;

import se.fredrikandthenurses.model.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

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

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            manager.close();
        }
        // return manager.find(entityClass, entity.getId());
        return entity;
    }

    public E remove(E entity) {
        manager = factory.createEntityManager();

        try {
            manager.getTransaction().begin();
            manager.remove(manager.contains(entity)? entity: manager.merge(entity));
            manager.getTransaction().commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            manager.close();
        }
        return entityToReturn;
    }

    /*public List<E> getAll() {
        List<E> entityList;
        manager = factory.createEntityManager();

        try {
            entityList = manager.createQuery("SELECT o from " + entityClass.getSimpleName() + " o").getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        manager.close();
        return entityList;
    }*/


    protected E query(String queryName, Object ...parameters) {
        manager = factory.createEntityManager();
        TypedQuery<E> query = manager.createNamedQuery(queryName, entityClass);
        for (int i = 0; i < parameters.length ; i++) {
            query.setParameter(i+1, parameters[i]);
        }
        E entityToReturn = query.getSingleResult();
        manager.close();
        return entityToReturn;
    }

    protected List<E> queryForList(String queryName, Object ...parameters) {
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
