package ${groupId}.model;


@Entity
public class FooEntity {

    private long id;
    private String name;

    public FooEntity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}