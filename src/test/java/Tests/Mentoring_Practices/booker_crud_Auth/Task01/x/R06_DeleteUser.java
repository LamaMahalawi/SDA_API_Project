package Tests.Mentoring_Practices.booker_crud_Auth.Task01.x;

import Base_URL.GoRestBaseUrl;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static Tests.Mentoring_Practices.booker_crud_Auth.Task01.x.R02_CreateUser.userId;

public class R06_DeleteUser extends GoRestBaseUrl {

    @Test
    void deleteUserTest() {
        given(spec)
                .when()
                .delete("/users/" + userId)
                .then()
                .statusCode(204);
    }
}
