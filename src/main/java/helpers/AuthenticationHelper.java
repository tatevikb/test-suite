package helpers;

import org.json.JSONObject;
import configurations.Configurations;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class AuthenticationHelper {

    public static String getKey() {

        final JSONObject body = new JSONObject();
        body.put("username", Configurations.USERNAME);
        body.put("email", Configurations.USERNAME);
        body.put("isLocal", false);
        body.put("password", Configurations.PASSWORD);

        final RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(Configurations.API_BASE_URL)
                .setBasePath("/user-account/signin.json")
                .setContentType(JSON)
                .build();

        return given()
                .spec(requestSpecification)
                .body(body.toString())
                .post()
                .then()
                .extract()
                .path("key");
    }
}
