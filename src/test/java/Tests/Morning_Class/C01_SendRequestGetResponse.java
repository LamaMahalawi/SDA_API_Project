package Tests.Morning_Class;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class C01_SendRequestGetResponse {

    public static void main(String[] args){

        System.out.println("Sending request to get all bookings :");
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking" );
        response.prettyPrint();

        int statusCode = response.statusCode();
        System.out.println("______________________________");
        System.out.println("status Code = " + statusCode);
        System.out.println("______________________________");
        System.out.println("status Line = " + response.statusLine());
        System.out.println("______________________________");
        System.out.println("Content Type = " + response.getContentType());
        System.out.println("______________________________");
        System.out.println("response time = " + response.time());
        System.out.println("______________________________");
        System.out.println("header = " + response.header("Server"));
        System.out.println("______________________________");
        System.out.println("All header = " + response.headers());
        System.out.println("______________________________");

    }
}
