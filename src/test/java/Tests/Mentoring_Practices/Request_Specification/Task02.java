package Tests.Mentoring_Practices.Request_Specification;

import Base_URL.JPHBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.List;
import static io.restassured.RestAssured.given;

public class Task02 extends JPHBaseUrl {

    @Test
    void getTodosExampleTest() {

        spec.pathParam("first", "todos");
        Response response = given(spec).get("{first}");


        System.out.println("------------- API Response -------------");
        response.prettyPrint();
        System.out.println("----------------------------------------");

       // 1) Status code = 200
        response.then().statusCode(200).contentType(ContentType.JSON);
        System.out.println("Status code -> 200");
        System.out.println("----------------------------------------");


        JsonPath jsonPath = response.jsonPath();

        // 2) Print all ids > 190 (10 total)
        List<Integer> idList = jsonPath.getList("id.findAll{it > 190}");
        System.out.println("IDs > 190 -> " + idList);
        System.out.println("Total count = " + idList.size());
        System.out.println("----------------------------------------");


        // 3) Print userIds with ids < 5 (4 total)
        List<Integer> userIdList = jsonPath.getList("findAll{it.id < 5}.userId");
        System.out.println("User IDs where id < 5 -> " + userIdList);
        System.out.println("Total count = " + userIdList.size());
        System.out.println("----------------------------------------");


        // 4) Verify title “quis eius est sint explicabo”
        List<String> titles = jsonPath.getList("title");
        boolean containsTitle = titles.contains("quis eius est sint explicabo");
        System.out.println("Does the title 'quis eius est sint explicabo' exist? -> " + containsTitle);
        System.out.println("----------------------------------------");


        // 5) Find id where title = "quo adipisci enim quam ut ab"
        int targetId = jsonPath.getInt("find{it.title == 'quo adipisci enim quam ut ab'}.id");
        System.out.println("The ID for title 'quo adipisci enim quam ut ab' -> " + targetId);
    }
}
