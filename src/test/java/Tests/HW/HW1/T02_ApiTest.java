package Tests.HW.HW1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class T02_ApiTest {
    @Test
    void validateApiResponse() {

        //1. Send GET request to the endpoint
        String url = "https://fakerestapi.azurewebsites.net/api/v1/Users";
        Response response = RestAssured.get(url);

        System.out.println("------------ API Response ------------");
        System.out.println("- Status Code: " + response.statusCode());
        System.out.println("- Content Type: " + response.contentType());
        System.out.println("- Status Line: " + response.statusLine());
        System.out.println("--------------------------------------");

       //2. Validate response status code
        response.then().statusCode(200);
        System.out.println("Status code is 200");

        //3. Validate response headers
        response.then().assertThat().contentType("application/json");
        System.out.println("Content-Type is 'application/json'");

        //4. Verify server information
        response.then().assertThat().header("Server", containsString("Kestrel"));
        System.out.println("Server header contains 'Kestrel'");

        System.out.println("All validations passed successfully!");
    }
}
