package Tests.Mentoring_Practices.Pojo_MAP.Task03;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import Tests.Mentoring_Practices.Pojo_MAP.Task03.ActivityPojo;
import Tests.Mentoring_Practices.Pojo_MAP.Task03.FakeRestBaseUrl;

import static io.restassured.RestAssured.given;

public class Task03 extends FakeRestBaseUrl{

    @Test
    public void crudActivitiesTest() {

        // Implement CREATE (POST) - Add new activity
        ActivityPojo newActivity = new ActivityPojo(2003, "Learn API Testing", "2025-12-31T00:00:00Z", true);

        Response postResponse = given(spec)
                .body(newActivity)
                .when()
                .post("/Activities");

        System.out.println("---------- CREATE ----------");
        postResponse.prettyPrint();
        Assert.assertEquals(postResponse.statusCode(), 200);
        System.out.println("Created (POST) successfully");


        // Implement READ (GET) - Retrieve activity details
        Response getResponse = given(spec)
                .when()
                .get("/Activities/10");


        System.out.println("---------- READ ----------");
        getResponse.prettyPrint();
        Assert.assertEquals(getResponse.statusCode(), 200);
        ActivityPojo actualGet = getResponse.as(ActivityPojo.class);
        Assert.assertEquals(actualGet.getId(), 10);
        System.out.println("Read (GET) successfully");


        // Implement UPDATE (PUT) - Modify existing activity
        ActivityPojo updatedActivity = new ActivityPojo(10, "Updated Title", "2025-12-31T00:00:00Z", false);

        Response putResponse = given(spec)
                .body(updatedActivity)
                .when()
                .put("/Activities/10");


        System.out.println("---------- UPDATE ----------");
        putResponse.prettyPrint();
        Assert.assertEquals(putResponse.statusCode(), 200);
        System.out.println("Updated (PUT) successfully");


        // Implement DELETE - Remove activity
        Response deleteResponse = given(spec)
                .when()
                .delete("/Activities/10");

        System.out.println("---------- DELETE ----------");
        deleteResponse.prettyPrint();
        Assert.assertEquals(deleteResponse.statusCode(), 200);
        System.out.println("Deleted successfully");
    }
}