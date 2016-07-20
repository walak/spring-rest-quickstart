package ${groupId}.runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static org.junit.Assert.assertEquals;

public class TomcatRunnerIntegrationTest {
    private static TomcatRunner tomcatRunner;

    @BeforeClass
    public static void setUp() {
        tomcatRunner = TomcatRunner.withDefaults();
        tomcatRunner.start();
    }


    @Test
    public void shouldReturnOKResponse() {
        get("/")
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldReturnCorrectResponseBody() {
        String body = get("/").asString();
        assertEquals("hello", body);
    }

    @AfterClass
    public static void tearDown() {
        tomcatRunner.stop();
    }
}
