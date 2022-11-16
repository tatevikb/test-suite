package configurations;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static configurations.Configurations.API_BASE_URL;
import static io.restassured.http.ContentType.JSON;

public class RequestSpecificationSetup {

    public static RequestSpecification setUpSpec(String baseUrl, String basePath) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setBasePath(basePath)
                .setContentType(JSON)
                .build();
    }

    public static RequestSpecification setUpAPISpec(String basePath) {
        return setUpSpec(API_BASE_URL, basePath);
    }
}
