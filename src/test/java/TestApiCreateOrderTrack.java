import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;


public class TestApiCreateOrderTrack extends DataOrder {

    @Test
    @DisplayName("Проверка тела ответа при создаии заказа")
    @Description("Тело ответа содержит track")

    public void apiCreateOrderTrack() {
        int track =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(request)
                        .when()
                        .post("/api/v1/orders")
                        .then().assertThat()
                        .body("track", notNullValue())
                        .extract().body().path("track");
        given()
                .header("Content-type", "application/json")
                .and()
                .body(track)
                .when()
                .put("/api/v1/orders/cancel");
    }
}
