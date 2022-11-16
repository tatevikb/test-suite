package api.enpoints;

import configurations.RequestSpecificationSetup;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Endpoint extends RequestSpecificationSetup {

    public static Response getMessages() {
        return given()
                .spec(setUpAPISpec("/localizations/en/messages?project=reusable_components,photo_editor"))
                .get();
    }
}
