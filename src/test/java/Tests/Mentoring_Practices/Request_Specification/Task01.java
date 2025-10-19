package Tests.Mentoring_Practices.Request_Specification;

import Base_URL.BookersBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Task01 extends BookersBaseURL {

    @Test
    void getBookingExample1Test() {

        spec.pathParams("first", "booking", "second", 32);
        Response response = given(spec).get("{first}/{second}");


        System.out.println("------------- API Response -------------");
        response.prettyPrint();
        System.out.println("----------------------------------------");


        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("John"))
                .body("lastname", equalTo("Smith"))
                .body("totalprice", equalTo(111));

        System.out.println("All validations passed successfully!");
    }
}
