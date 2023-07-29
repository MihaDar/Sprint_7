import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class TestApiLoginCourier extends DataCourier {
    @Test
    @DisplayName("Проверка авторизации курьера")
    @Description("Курьер может авторизоваться. " +
            "Для авторизации нужно передать все обязательные поля")

    public void canLoginCourier() {
        requestWithoutFirstName = new CreateCourier(log, pass);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(requestWithoutFirstName)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().statusCode(200);
    }

    @Test
    @DisplayName("Проверка авторизации курьера с неправильным паролем")
    @Description("Система вернёт ошибку, если неправильно указать логин или пароль")

    public void cannotLoginCourierIncorrectPassword() {
        requestWithoutFirstName = new CreateCourier(log, pass + log);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(requestWithoutFirstName)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().statusCode(404);
    }

    @Test
    @DisplayName("Проверка авторизации курьера без пароля")
    @Description("Если какого-то поля нет, запрос возвращает ошибку")

    public void cannotLoginCourierWithoutPassword() {
        requestWithoutFirstName = new CreateCourier(log, "");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(requestWithoutFirstName)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().statusCode(400);
    }

    @Test
    @DisplayName("Проверка авторизации курьера без логина")
    @Description("Если какого-то поля нет, запрос возвращает ошибку")

    public void cannotLoginCourierWithoutLogin() {
        requestWithoutFirstName = new CreateCourier("", pass);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(requestWithoutFirstName)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().statusCode(400);
    }

    @Test
    @DisplayName("Проверка авторизации курьера с неправильным логином")
    @Description("Если авторизоваться под несуществующим пользователем, запрос возвращает ошибку")

    public void cannotLoginCourierIncorrectLogin() {
        requestWithoutFirstName = new CreateCourier(log + pass, pass);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(requestWithoutFirstName)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().statusCode(404);
    }

    @Test
    @DisplayName("Проверка тела ответа при авторизации курьера")
    @Description("Успешный запрос возвращает id")

    public void canLoginCourierResponseId() {
        requestWithoutFirstName = new CreateCourier(log, pass);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(requestWithoutFirstName)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().assertThat().body("id", notNullValue());
    }
}
