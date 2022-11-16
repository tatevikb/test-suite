package api;

import api.enpoints.Endpoint;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.apache.http.HttpStatus.SC_OK;

public class GetMessagesTest {

    @Test
    public void getMessagesTest() {
        Endpoint.getMessages()
                .then()
                .statusCode(SC_OK)
                .body(matchesJsonSchemaInClasspath("schema/messages.json"));
    }
}
