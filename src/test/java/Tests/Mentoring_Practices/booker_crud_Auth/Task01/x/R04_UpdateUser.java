package Tests.Mentoring_Practices.booker_crud_Auth.Task01.x;

import Base_URL.GoRestBaseUrl;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static Tests.Mentoring_Practices.booker_crud_Auth.Task01.x.R02_CreateUser.userId;

public class R04_UpdateUser extends GoRestBaseUrl {

    @Test
    void updateUserTest() {
        String payload = """
                {
                  "name": "Luli Updated",
                  "email": "luli.updated@example.com",
                  "status": "inactive"
                }
                """;

        given(spec)
                .body(payload)
                .when()
                .put("/users/" + userId)
                .then()
                .statusCode(200)
                .body("name", equalTo("Luli Updated"))
                .body("status", equalTo("inactive"));
    }
}
