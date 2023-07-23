import org.junit.After;
import org.junit.Before;

import static io.restassured.RestAssured.given;


public class DataCourier extends Url {

    final String log = "runrun";
    final String pass = "1234";
    final String fName = "vanya";

    CreateCourier request = new CreateCourier(log, pass, fName);
    CreateCourier requestWithoutFirstName = new CreateCourier(log, pass);

    @Before
    public void createCourier(){
        given()
                .header("Content-type", "application/json").and()
                .body(request)
                .when()
                .post("/api/v1/courier");
    }

    @After
    public void clear() {
        int courierId = given()
                .header("Content-type", "application/json")
                .and()
                .body(request)
                .when()
                .post("/api/v1/courier/login")
                .then().extract().body().path("id");
        given()
                .header("Content-type", "application/json")
                .when()
                .delete("/api/v1/courier/" + courierId);
    }

}
