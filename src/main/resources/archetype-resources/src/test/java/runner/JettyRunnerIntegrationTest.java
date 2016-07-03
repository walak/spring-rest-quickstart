package ${groupId}.runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static org.junit.Assert.assertEquals;

public class JettyRunnerIntegrationTest {

    private static JettyRunner jettyRunner;

    @BeforeClass
    public static void setUp() {
        jettyRunner = JettyRunner.withDefaults();
        jettyRunner.start();
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
        jettyRunner.stop();
    }
}
