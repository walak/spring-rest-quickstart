package ${groupId}.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FooEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public FooEntity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    FooEntity() {

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}