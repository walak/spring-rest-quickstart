package ${groupId}.service.foo;

import ${groupId}.dao.foo.FooDao;
import ${groupId}.model.FooEntity;
import ${groupId}.service.BasicDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PersistentFooDaoService extends BasicDaoService<FooEntity, FooDao> implements FooDaoService {

    @Autowired
    public PersistentFooDaoService(FooDao dao) {
        super(dao);
    }
}
