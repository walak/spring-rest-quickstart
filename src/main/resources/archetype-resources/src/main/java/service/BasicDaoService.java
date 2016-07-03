package ${groupId}.service;

import ${groupId}.dao.Dao;

import java.util.Optional;

public abstract class BasicDaoService<A, B extends Dao<A>> implements DaoService<A> {

    private final B dao;

    protected BasicDaoService(B dao) {
        this.dao = dao;
    }

    @Override
    public A save(A entity) {
        return dao.save(entity);
    }

    @Override
    public Optional<A> findById(long id) {
        return dao.getById(id);
    }

    protected B getDao() {
        return dao;
    }
}
