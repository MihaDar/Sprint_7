import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class TestApiOrderList extends Url {
    @Test
    @DisplayName("Ппрверка списка заказов")
    @Description("В теле ответа, возвращается список заказов")

    public void apiOrderList(){
        Response orders = given()
            .get("/api/v1/orders");
        orders.then().assertThat().body("orders", notNullValue());
}
}
