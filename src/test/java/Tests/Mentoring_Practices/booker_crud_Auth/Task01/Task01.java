package Tests.Mentoring_Practices.booker_crud_Auth.Task01;

import Base_URL.GoRestBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static utilities.ObjectMapperUtils.getJsonNode;

public class Task01  extends GoRestBaseUrl {
    public static int userId;

    // Get all users
    @Test
    void getAllUsersTest() {

        Response response = given(spec).get("/users");

        System.out.println("------------- API Response -------------");
        response.prettyPrint();

        response
                .then()
                .statusCode(200)
                .body("[0].id", notNullValue());
        System.out.println("-> Get all users successful");
        System.out.println("----------------------------------------");
    }

    // Create user
    @Test
    void createUserTest() {
        JsonNode payload = getJsonNode("User");// in test_data ,email should be unique

        Response response = given(spec)
                .body(payload)
                .when()
                .post("/users");

        response.then()
                .statusCode(201)
                .body("name", equalTo(payload.get("name").asText()));

        userId = response.jsonPath().getInt("id");
        System.out.println("-> User created successfully with ID: " + userId + "-> " +payload);
    }

    // Get that user
    @Test
    void getUserTest() {
        given(spec)
                .when()
                .get("/users/" + userId)
                .then()
                .statusCode(200)
                .body("id", equalTo(userId))
                .body("name", equalTo("Lama"));
        System.out.println("-> Get user successful");
    }

    // Update user
    @Test
    void updateUserTest() {
        JsonNode updatedJson = getJsonNode("User_update"); // in test_data

        given(spec)
                .body(updatedJson)
                .when()
                .put("/users/" + userId)
                .then()
                .statusCode(200)
                .body("name", equalTo(updatedJson.get("name").asText()))
                .body("status", equalTo(updatedJson.get("status").asText()));

        System.out.println("-> Update user successful -> " + updatedJson);
    }

    // Partial update
    @Test
    void partialUpdateUserTest() {
        JsonNode patchJson = getJsonNode("User_patch"); // in test_data

        given(spec)
                .body(patchJson)
                .when()
                .patch("/users/" + userId)
                .then()
                .statusCode(200)
                .body("status", equalTo(patchJson.get("status").asText()));

        System.out.println("-> Partial update user successful -> " + patchJson);
    }

    //Delete user
    @Test(dependsOnMethods = "createUserTest")
    void deleteUserTest() {
        given(spec)
                .when()
                .delete("/users/" + userId)
                .then()
                .statusCode(204);

        System.out.println("-> User deleted successfully -> " + userId);
    }

    // Negative test
    @Test (dependsOnMethods = "deleteUserTest")
    void getUserNegativeTest() {
        given(spec)
                .when()
                .get("/users/" + userId)
                .then()
                .statusCode(404);

        System.out.println("-> Negative test passed");
    }
}