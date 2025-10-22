package Tests.Mentoring_Practices.booker_crud_Auth.Task01.x;

import Base_URL.GoRestBaseUrl;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static Tests.Mentoring_Practices.booker_crud_Auth.Task01.x.R02_CreateUser.userId;

public class R05_PartialUpdateUser extends GoRestBaseUrl {

    @Test
    void partialUpdateUserTest() {
        String payload = """
                {
                  "status": "active"
                }
                """;

        given(spec)
                .body(payload)
                .when()
                .patch("/users/" + userId)
                .then()
                .statusCode(200)
                .body("status", equalTo("active"));
    }
}