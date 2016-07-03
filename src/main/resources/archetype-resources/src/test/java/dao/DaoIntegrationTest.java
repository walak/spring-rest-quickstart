package ${groupId}.dao;


import ${groupId}.TestConfig;
import ${groupId}.dao.foo.FooDao;
import ${groupId}.model.FooEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DaoIntegrationTest {

    @Autowired
    private FooDao fooDao;

    @Test
    public void shouldSaveDataAndLoadIt() {
        FooEntity fooEntity = new FooEntity(0, "test");

        FooEntity savedEntity = fooDao.save(fooEntity);
        assertNotEquals(savedEntity.getId(), 0);

        Optional<FooEntity> maybeFound = fooDao.getById(savedEntity.getId());

        assertTrue(maybeFound.isPresent());
        assertEquals(fooEntity.getName(), maybeFound.get().getName());
    }

}
