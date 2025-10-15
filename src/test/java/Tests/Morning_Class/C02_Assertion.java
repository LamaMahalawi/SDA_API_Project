package Tests.Morning_Class;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class C02_Assertion {
    /*
     Given
         https://restful-booker.herokuapp.com/booking
     When
         User sends a GET request to the URL
     Then
         HTTP Status Code should be 200
     And
         Content Type should be JSON
     And
         Status Line should be HTTP/1.1 200 OK
     */

    @Test
    void assertionTest() {

        System.out.println("Sending request to get all bookings..");
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking");

        System.out.println("Verifying Status Code...");
        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200!");

        System.out.println("Verifying Content Type...");
        Assert.assertTrue(response.contentType().contains("application/json"), "Content type is not JSON!");

        System.out.println("Verifying Status Line...");
        Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK", "Status line mismatch!");

        System.out.println("All assertions passed successfully!");
    }
}