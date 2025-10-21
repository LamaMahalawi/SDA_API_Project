package Tests.Mentoring_Practices.JsonNode.Task01;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;
import static utilities.ObjectMapperUtils.convertJsonToJava;


public class Task_01 extends RandomUserBaseUrl {
    @Test
    void getRandomUserTest() {
        // Send GET request
        Response response = given(spec).get("/api");
        response.prettyPrint();

        // Deserialize response into POJO
        Test01 actualData = convertJsonToJava(response.asString(), Test01.class);
        ResultsItem user = actualData.getResults().get(0);

        // Assertions
        assertNotNull(user.getEmail(), "Email is null!");
        assertNotNull(user.getLogin().getUsername(), "Username is null!");
        assertNotNull(user.getLogin().getPassword(), "Password is null!");
        assertNotNull(user.getPicture().getMedium(), "Medium picture URL is null!");

        System.out.println("All fields are NOT null and test PASSED!");
    }
}
