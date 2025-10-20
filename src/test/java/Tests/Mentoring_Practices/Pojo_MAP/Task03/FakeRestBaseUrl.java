package Tests.Mentoring_Practices.Pojo_MAP.Task03;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class FakeRestBaseUrl {
    protected RequestSpecification spec;

    @BeforeMethod
    public void setSpec() {
        spec = new RequestSpecBuilder()
                .setBaseUri("https://fakerestapi.azurewebsites.net/api/v1")
                .setContentType(ContentType.JSON)
                .build();
    }
}
