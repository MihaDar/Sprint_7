import io.restassured.RestAssured;
import org.junit.Before;

abstract class Url {
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }
}
