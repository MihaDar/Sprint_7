import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TestApiCreateCourierWithoutPassword extends DataCourier {

    @Override
    public void createCourier(){}
    public void clear(){}

    @Test
    @DisplayName("Проверка создание курьера без пароля")
    @Description("Если одного из полей нет, запрос возвращает ошибку. " +
            "Запрос возвращает правильный код ответа 400")

    public void createNewCourierBodyWithoutPassword() {

        request = new CreateCourier(log,"",fName);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(request)
                        .when()
                        .post("/api/v1/courier");
        response.then().statusCode(400);
    }
}
