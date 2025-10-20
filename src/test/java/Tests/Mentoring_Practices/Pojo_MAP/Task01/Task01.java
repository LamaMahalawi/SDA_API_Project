package Tests.Mentoring_Practices.Pojo_MAP.Task01;

import Base_URL.ReqresBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Task01 extends ReqresBaseUrl {

    @Test
    void createUserTest() {


        Map<String, Object> payload = new HashMap<>();
        payload.put("name", "morpheus");
        payload.put("job", "leader");
        System.out.println("Payload Data → " + payload);


        // Send request (with API key header)
        Response response = given(spec)
                .body(payload)
                .post("/users");


        System.out.println("------------- API Response -------------");
        response.prettyPrint();


        response
        .then()
        .statusCode(201)
        .body("name", equalTo(payload.get("name")),
        "job", equalTo(payload.get("job")),
                 "id", notNullValue(),
                 "createdAt", notNullValue());

        System.out.println("------------- Verification Results -------------");
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Name: " + response.jsonPath().getString("name"));
        System.out.println("Job: " + response.jsonPath().getString("job"));
        System.out.println("ID: " + response.jsonPath().getString("id"));
        System.out.println("Created At: " + response.jsonPath().getString("createdAt"));
        System.out.println("All Assertions Passed Successfully");


        }
    }