package ${groupId}.dao;

import java.util.Optional;

public interface Dao<T> {
    T save(T entity);
    Optional<T> getById(long id);

}
