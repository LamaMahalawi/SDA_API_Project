package Base_URL;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class BookersBaseURL {

    protected RequestSpecification spec;

    @BeforeMethod//Before each test method, this will work and initialize the spec object.
    public void setSpec() {
        spec = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .build();
    }


}
