import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class TestApiCreateCourier extends DataCourier {
    @Override
    public void createCourier(){}

    @Test
    @DisplayName("Проверка создания курьера")
    @Description("Курьера можно создать. " +
            "Чтобы создать курьера, нужно передать в ручку все обязательные поля. " +
            "Запрос возвращает правильный код ответа 201")

    public void createNewCourier() {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(request)
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().body("ok", notNullValue())
                .and()
                .statusCode(201);

    }


    @Test
    @DisplayName("Проверка создания дубликата курьера")
    @Description("Нельзя создать двух одинаковых курьеров. " +
            "Если создать пользователя с логином, который уже есть, возвращается ошибка. " +
            "Запрос возвращает правильный код ответа 409")

    public void notCreateDoubleCourier() {
        given()
                .header("Content-type", "application/json")
                .and()
                .body(request)
                .when()
                .post("/api/v1/courier");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(request)
                        .when()
                        .post("/api/v1/courier");
        response.then().statusCode(409);
    }

//    @Test
//    @DisplayName("Проверка создание курьера")
//    @Description("Чтобы создать курьера, нужно передать в ручку все обязательные поля. Запрос возвращает правильный код ответа 201")
//
//    public void createNewCourierWithAllFields() {
//        Response response =
//                given()
//                        .header("Content-type", "application/json")
//                        .and()
//                        .body(request)
//                        .when()
//                        .post("/api/v1/courier");
//        response.then().statusCode(201);
//    }

    @Test
    @DisplayName("Проверка тела ответа при создании курьера")
    @Description("Успешный запрос возвращает ok: true")

    public void successCreateCourierBodyOkTrue() {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(request)
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat()
                .body("ok", equalTo(true));
    }
}
