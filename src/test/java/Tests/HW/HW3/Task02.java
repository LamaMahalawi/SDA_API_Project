package Tests.HW.HW3;

import Base_URL.BookStoreBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static utilities.ObjectMapperUtils.getJsonNode;

public class Task02 extends BookStoreBaseUrl {

    public static String token;
    public static String userId;

    // Create User
    @Test(priority = 1)
    void createUserTest() {
        JsonNode payload = getJsonNode("Bookstore_User");
        Response response = given(spec)
                .contentType("application/json")
                .body(payload)
                .when()
                .post("/Account/v1/User");

        System.out.println("------------- API Response (create User) -------------");
        response.prettyPrint();

        response.then()
                .statusCode(201)
                .body("username", equalTo(payload.get("userName").asText()));

        userId = response.jsonPath().getString("userID");
        System.out.println("|--> User created successfully with ID -> " + userId);
    }


    // Generate Token for the user
    @Test(priority = 2)
    void generateTokenTest() {
        JsonNode payload = getJsonNode("Bookstore_User");
        Response response = given(spec)
                .contentType("application/json")
                .body(payload)
                .when()
                .post("/Account/v1/GenerateToken");


        System.out.println("------------- API Response (generate Token) -------------");
        response.prettyPrint();

        response.then()
                .statusCode(200)
                .body("status", equalTo("Success"));

        token = response.jsonPath().getString("token");
        System.out.println("|--> Token generated successfully -> " + token);
    }


    // Get all books
    @Test(priority = 3)
    void getAllBooksTest() {
        Response response = given(spec)
                .when()
                .get("/BookStore/v1/Books");

        System.out.println("------------- API Response (get All Books) -------------");
        response.prettyPrint();

        response.then()
                .statusCode(200)
                .body("books", not(empty()));
        System.out.println("|--> All books retrieved successfully");
    }


    // Assign Book to User
    @Test(priority = 4)
    void assignBooksToUserTest() {

        JsonNode payload = getJsonNode("Book_Assign");
        ((com.fasterxml.jackson.databind.node.ObjectNode) payload).put("userId", userId);

        Response response = given(spec)
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(payload)
                .when()
                .post("/BookStore/v1/Books");

        System.out.println("------------- API Response (assign Books To User) -------------");
        response.prettyPrint();


        response.then()
                .statusCode(201)
                .body("books[0].isbn", notNullValue());

        System.out.println("|--> Book assigned successfully to user -> " + userId);


    }

    // Get User Info
    @Test(priority = 5)
    void getUserInfoTest() {
        Response response = given(spec)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/Account/v1/User/" + userId);

        System.out.println("------------- API Response (get User Info) -------------");
        response.prettyPrint();

        response.then()
                .statusCode(200)
                .body("userId", equalTo(userId));

        System.out.println("|--> User info retrieved successfully!");
    }
}