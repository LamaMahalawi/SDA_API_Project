package Tests.Mentoring_Practices.Pojo_MAP.Task02;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import Tests.Mentoring_Practices.Pojo_MAP.Task02.UserPojo;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class Task02  extends PetStoreBaseUrl  {
    @Test
    void createUserTest() {

        // 1️⃣ إعداد البيانات (Expected Data)
        UserPojo payload = new UserPojo(
                1234,
                "LuluTest",
                "Lulu",
                "Tester",
                "lulu@example.com",
                "Lulu@123",
                "0551234567",
                1
        );

        System.out.println("Payload Data → " + payload);

        // 2️⃣ إرسال الطلب
        Response response = given(spec)
                .body(payload)
                .when()
                .post("/user");

        System.out.println("------------- API Response -------------");
        response.prettyPrint();
        System.out.println("----------------------------------------");

        // 3️⃣ التحقق من النتائج
        response
                .then()
                .statusCode(200)
                .body("$", hasKey("code"))
                .body("$", hasKey("message"));

        // 4️⃣ طباعة النتيجة النهائية
        System.out.println("✅ Test Passed: User created successfully!");
    }
}

