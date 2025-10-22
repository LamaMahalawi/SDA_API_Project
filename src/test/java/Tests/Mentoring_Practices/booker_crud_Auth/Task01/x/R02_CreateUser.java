package Tests.Mentoring_Practices.booker_crud_Auth.Task01.x;

import Base_URL.GoRestBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class R02_CreateUser extends GoRestBaseUrl {

    public static int userId;

    @Test
    void createUserTest() {
        String payload = """
                {
                  "name": "Luli Test",
                  "gender": "female",
                  "email": "luli.test987@example.com",
                  "status": "active"
                }
                """;

        Response response = given(spec)
                .body(payload)
                .when()
                .post("/users");

        response.then()
                .statusCode(201)
                .body("name", equalTo("Luli Test"));

        userId = response.jsonPath().getInt("id");
        System.out.println("User created with ID: " + userId);
    }
}
