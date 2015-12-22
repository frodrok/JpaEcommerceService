package se.fredrikandthenurses.repository;

import se.fredrikandthenurses.model.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public abstract class AbstractJpaRepository<E extends AbstractEntity> implements CrudRepository<E> {

    private final EntityManagerFactory factory;
    private final Class<E> entityClass;
    private EntityManager manager;

    public AbstractJpaRepository(EntityManagerFactory emf, Class<E> entityClass) {
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

    public List<E> getAll() {
        List<E> entityList;
        manager = factory.createEntityManager();

        try {
            entityList = manager.createQuery("SELECT o from " + entityClass.getSimpleName() + " o").getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        manager.close();
        return entityList;
    }

    public TypedQuery<E> query(String queryName) {
        return manager.createNamedQuery(queryName, entityClass);
    }

    public List<E> queryForList(String queryName) {
        TypedQuery<E> query = manager.createNamedQuery(queryName, entityClass);
        return query.getResultList();
    }



}
