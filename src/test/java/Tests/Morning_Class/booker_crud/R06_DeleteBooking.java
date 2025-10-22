package Tests.Morning_Class.booker_crud;

import Base_URL.BookersBaseURL;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;
import static Tests.Morning_Class.booker_crud.R02_CreateBooking.bookingId;

public class R06_DeleteBooking extends BookersBaseURL {

    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    When
        Send the delete request
    Then
        Status code should be 201
    And
        Response body should be "Created"
     */

    @Test
    void R06_DeleteBookingTest() {

        //Send the request
        Response response = given(spec).delete("/booking/" + bookingId);
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(201)
                .time(lessThan(1000L))
                .body(containsString("Created"));
    }
}