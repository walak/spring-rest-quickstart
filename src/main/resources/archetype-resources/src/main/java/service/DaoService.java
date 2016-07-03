package ${groupId}.service;

import java.util.Optional;

public interface DaoService<T> {

    T save(T entity);

    Optional<T> findById(long id);
}
