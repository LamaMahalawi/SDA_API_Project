package Tests.Mentoring_Practices.booker_crud_Auth.Task01.x;

import Base_URL.GoRestBaseUrl;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static Tests.Mentoring_Practices.booker_crud_Auth.Task01.x.R02_CreateUser.userId;

public class R03_GetUser extends GoRestBaseUrl {

    @Test
    void getUserTest() {
        given(spec)
                .when()
                .get("/users/" + userId)
                .then()
                .statusCode(200)
                .body("id", equalTo(userId))
                .body("name", equalTo("Luli Test"));
    }
}
