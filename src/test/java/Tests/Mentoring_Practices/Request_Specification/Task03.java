package Tests.Mentoring_Practices.Request_Specification;

import Base_URL.BookersBaseURL;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class Task03 extends BookersBaseURL {

    @Test
    void getEmployeesTest() {

        Response response = RestAssured.get("https://dummy.restapiexample.com/api/v1/employees");

        System.out.println("------------- API Response -------------");
        response.prettyPrint();
        System.out.println("----------------------------------------");

        // Verify status code
        response.then().statusCode(200).contentType(ContentType.JSON);
        System.out.println("Status Code = 200");
        System.out.println("----------------------------------------");


        JsonPath jsonPath = response.jsonPath();
        // Extract all employee names
        List<String> names = jsonPath.getList("data.employee_name");
        System.out.println("All Employee Names = " + names);
        System.out.println("----------------------------------------");

        // There are 24 employees ( Assert number of employees = 24 )
        Assert.assertEquals(names.size(), 24, "Expected 24 employees");
        System.out.println("Total Employees = " + names.size());
        System.out.println("----------------------------------------");

        // "Tiger Nixon" and "Garrett Winters" are among them
        boolean hasTiger = jsonPath.getBoolean("data.any{it.employee_name == 'Tiger Nixon'}");
        boolean hasGarrett = jsonPath.getBoolean("data.any{it.employee_name == 'Garrett Winters'}");
        System.out.println("Is 'Tiger Nixon' found? -> " + hasTiger);
        System.out.println("Is 'Garrett Winters' found? -> " + hasGarrett);
        System.out.println("----------------------------------------");


        // Highest age = 66
        int highestAge = jsonPath.getInt("data.max{it.employee_age.toInteger()}.employee_age");
        Assert.assertEquals(highestAge, 66, "Expected highest age = 66");
        System.out.println("Highest Age = " + highestAge);
        System.out.println("----------------------------------------");

        // Youngest = "Tatyana Fitzpatrick"
        String youngestName = jsonPath.getString("data.min{it.employee_age.toInteger()}.employee_name");
        Assert.assertEquals(youngestName, "Tatyana Fitzpatrick", "Expected youngest = Tatyana Fitzpatrick");
        System.out.println("Youngest Employee = " + youngestName);
        System.out.println("----------------------------------------");


        // Total Salary = 6,644,770
        int totalSalary = jsonPath.getInt("data.sum{it.employee_salary.toInteger()}");
        Assert.assertEquals(totalSalary, 6644770, "Expected total salary = 6644770");
        System.out.println("Total Salary = " + totalSalary);
        System.out.println("----------------------------------------");

    }
}

