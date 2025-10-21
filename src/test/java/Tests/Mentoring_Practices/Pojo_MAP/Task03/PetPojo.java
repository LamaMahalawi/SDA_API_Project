package Tests.Mentoring_Practices.Pojo_MAP.Task03;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class PetPojo extends FakeRestBaseUrl  {

    @Test
    void crudActivitiesTest() {

       // Implement CREATE (POST) - Add new activity
        System.out.println("--------------CREATE--------------");
        String dueDate = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        ActivityPojo expectedData = new ActivityPojo(2, "Activity 2", dueDate , true);


        // Send POST request
        Response postResponse = given(spec).body(expectedData).post("/Activities");
        postResponse.prettyPrint();

        // Assert
        assertEquals(postResponse.statusCode(), 200);
        ActivityPojo actualPostData = postResponse.as(ActivityPojo.class);
        assertEquals(actualPostData.getTitle(), expectedData.getTitle());
        assertEquals(actualPostData.getCompleted(), expectedData.getCompleted());
        System.out.println("-> CREATE operation successful");

        Integer id = actualPostData.getId(); // Store ID for later use
        System.out.println("Created ID = " + id);

        // Implement READ (GET) - Retrieve activity details
        System.out.println("--------------READ--------------");
        Response getResponse = given(spec).get("/Activities/" + id);
        getResponse.prettyPrint();

        // Assert
        assertEquals(getResponse.statusCode(), 200);
        ActivityPojo actualGetData = getResponse.as(ActivityPojo.class);
        assertEquals(actualGetData.getTitle(), expectedData.getTitle());
        assertEquals(actualGetData.getCompleted(), expectedData.getCompleted());
        System.out.println("-> READ operation successful");


        // Implement UPDATE (PUT) - Modify existing activity
        System.out.println("--------------UPDATE--------------");
        String currentDate = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        ActivityPojo updatedActivity = new ActivityPojo(2, "Updated Activity Title", currentDate, true);

        // Send PUT request
        Response putResponse = RestAssured.given(spec).body(updatedActivity).put("/Activities/"+ updatedActivity.getId());
        putResponse.prettyPrint();

        // Assert
        ActivityPojo responseBody = putResponse.as(ActivityPojo.class);
        assertEquals(putResponse.statusCode(), 200);
        assertEquals(responseBody.getId(), updatedActivity.getId());
        assertEquals(responseBody.getTitle(), updatedActivity.getTitle());
        assertEquals(responseBody.getCompleted(), updatedActivity.getCompleted());
        System.out.println("-> UPDATE operation successful");



        // Implement DELETE - Remove activity
        System.out.println("--------------DELETE--------------");
        Response deleteResponse = given(spec).delete("/Activities/" + id);
        deleteResponse.prettyPrint();
        assertEquals(deleteResponse.statusCode(), 200);
        System.out.println("-> DELETE operation successful");


    }
}