package Tests.HW.HW1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class T01_ApiTest {
        public static void main(String[] args) {

            // Send GET request
            Response response = RestAssured.get("https://fakerestapi.azurewebsites.net/api/v1/Users");

            // Get status code
            int statusCode = response.statusCode();
            System.out.println("Status Code: " + statusCode);

            // Print response
            System.out.println("--------------------------------------");
            System.out.println("Response body is:");
            response.prettyPrint();
            System.out.println("--------------------------------------");

            // Assertions
            response.then().statusCode(200);
            response.then().assertThat().header("Content-Type", containsString("application/json"));
            response.then().assertThat().body(notNullValue());

            System.out.println("All assertions passed successfully!");
        }
}

