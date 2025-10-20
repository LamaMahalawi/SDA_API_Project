package Tests.Mentoring_Practices.Pojo_MAP.Task02;

import Base_URL.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import Tests.Mentoring_Practices.Pojo_MAP.Task02.UserPojo;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class Task02  extends PetStoreBaseUrl {
    @Test
    public void createUserTest() {
        Integer id = 43;

        // Endpoint
        spec.pathParam("first", "user");

        // Expected (Map)
        Map<String, Object> expectedMap = new HashMap<>();
        expectedMap.put("code", 200);
        expectedMap.put("type", "unknown");
        expectedMap.put("message", id.toString());

        // Expected (Pojo)
        SuccessfulUserCreationPojo expectedPojo =
                new SuccessfulUserCreationPojo(200, "unknown", id.toString());

        // Payload
        UserPojo payload = new UserPojo(
                id,
                "TomHanks",
                "Tom",
                "Hanks",
                "string@email.com",
                "string123",
                "500000000",
                0
        );

        // Send request
        Response response = given(spec)
                .body(payload)
                .when()
                .post("{first}");

        System.out.println("------------- API Response -------------");
        response.prettyPrint();
        System.out.println("----------------------------------------");

        // Hamcrest checks
        response.then()
                .statusCode(200)
                .body("$", hasKey("code"))
                .body("$", hasKey("type"))
                .body("$", hasKey("message"));

        // Assert with Map
        Map<String, Object> actualMap = response.as(Map.class);
        Assert.assertEquals(actualMap.get("code"), expectedMap.get("code"));
        Assert.assertEquals(actualMap.get("type"), expectedMap.get("type"));
        Assert.assertEquals(actualMap.get("message"), expectedMap.get("message"));

        // Assert with Pojo
        SuccessfulUserCreationPojo actualPojo = response.as(SuccessfulUserCreationPojo.class);
        Assert.assertEquals(actualPojo.getCode(), expectedPojo.getCode());
        Assert.assertEquals(actualPojo.getType(), expectedPojo.getType());
        Assert.assertEquals(actualPojo.getMessage(), expectedPojo.getMessage());
    }
}