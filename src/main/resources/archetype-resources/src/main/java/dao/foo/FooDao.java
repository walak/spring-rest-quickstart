package ${groupId}.dao.foo;

import ${groupId}.dao.BasicDao;
import ${groupId}.model.FooEntity;
import org.springframework.stereotype.Component;

@Component
public class FooDao extends BasicDao<FooEntity> {

    public FooDao() {
        super(FooEntity.class);
    }
}
