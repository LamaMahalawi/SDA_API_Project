package Tests.Mentoring_Practices.JsonNode.Task02;

import Base_URL.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ObjectMapperUtils;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class Task_02 extends PetStoreBaseUrl {

    @Test
    void createPetTest() {
        // Path Parameter
        spec.pathParam("first", "pet");

        // JSON Payload as String
        String jsonStr = """
            {
              "id": 43,
              "category": {
                "id": 0,
                "name": "Aldo"
              },
              "name": "doggie",
              "photoUrls": [
                "string",
                "stringExtra"
              ],
              "tags": [
                {
                  "id": 0,
                  "name": "Very Cute"
                },
                {
                  "id": 1,
                  "name": "Brown"
                },
                {
                  "id": 3,
                  "name": "female"
                }
              ],
              "status": "available"
            }
            """;

        // Convert JSON → POJO
        PetPojo payload = ObjectMapperUtils.convertJsonToJava(jsonStr, PetPojo.class);

        // Send POST Request
        Response response = given(spec)
                .body(payload)
                .post("/{first}");
        response.prettyPrint();

        // Assertions
        response
                .then()
                .statusCode(200)
                .body("name", equalTo(payload.getName()))
                .body("status", equalTo(payload.getStatus()))
                .body("category.name", equalTo(payload.getCategory().getName()))
                .body("tags[0].name", equalTo(payload.getTags().get(0).getName()));

        System.out.println(" Pet created successfully!");
    }}