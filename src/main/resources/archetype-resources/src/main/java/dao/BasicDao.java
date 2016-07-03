package ${groupId}.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import java.util.Optional;

public abstract class BasicDao<T> implements Dao<T> {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    private final Class<T> clazz;

    public BasicDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T save(T entity) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
        }
        return entity;
    }

    @Override
    public Optional<T> getById(long id) {
        EntityManager entityManager = getEntityManager();

        try {
            return Optional.ofNullable(entityManager.find(clazz, id));
        } finally {
            entityManager.close();
        }
    }

    protected EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
