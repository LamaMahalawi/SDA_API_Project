package Tests.HW.HW2;


import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ObjectMapperUtils;

import static Tests.Mentoring_Practices.booker_crud_Auth.Task01.Task01.userId;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utilities.ObjectMapperUtils.updateJsonNode;

public class Task03 extends FakeStoreBaseUrl {

    @Test
    void createCartTest() {

        // products (array of product objects with productId and quantity
        JsonNode payload = ObjectMapperUtils.getJsonNode("cart"); //(from test_data folder)
        System.out.println("Original Payload: " + payload.toPrettyString());
        System.out.println("----------------------------------------");

        // Modify the JsonNode to add additional fields as needed
        updateJsonNode(payload, "userId", 9);
        updateJsonNode(payload, "date", "2025-10-21");
        System.out.println("Updated Payload: " + payload.toPrettyString());

        // Send a POST request to the create cart endpoint
        Response response = given(spec)
                .body(payload)
                .post("/carts");

        System.out.println("------------- API Response -------------");
        response.prettyPrint();
        System.out.println("----------------------------------------");


        // Assert that the response status code indicates success
        // Assert that the returned cart contains the expected data
        response
                .then()
                .statusCode(201)
                .body("userId", equalTo(payload.get("userId").intValue()))
                .body("date", equalTo(payload.get("date").asText()));

        System.out.println("Cart created successfully and verified");
    }
}