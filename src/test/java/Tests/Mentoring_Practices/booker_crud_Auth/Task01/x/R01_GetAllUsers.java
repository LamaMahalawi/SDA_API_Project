package Tests.Mentoring_Practices.booker_crud_Auth.Task01.x;

import Base_URL.GoRestBaseUrl;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class R01_GetAllUsers extends GoRestBaseUrl {

    @Test
    void getAllUsersTest() {
        given(spec)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("[0].id", notNullValue());
    }
}
