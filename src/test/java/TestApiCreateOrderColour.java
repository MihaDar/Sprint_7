import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static io.restassured.RestAssured.given;

@RunWith(Parameterized.class)
public class TestApiCreateOrderColour extends Url {
    private final String[] colors;

    public TestApiCreateOrderColour(String[] colors) {
        this.colors = colors;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {new String[]{"BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{"BLACK", "GREY"}},
                {new String[]{""}},
        };
    }

    @Test
    @DisplayName("Параметрический тест цвета самоката, при создаии заказа.")
    @Description("Можно указать: " +
            "Один из цветов — BLACK или GREY. " +
            "Оба цвета. " +
            "Не указывать цвет")

    public void testApiCreateOrderColour() {

        String firstName = "Misha";
        String lastName = "Mishin";
        String address = "Moskva, 77";
        String metroStation = "4";
        String phone = "+7 800 355 35 35";
        int rentTime = 4;
        String deliveryDate = "2023-08-06";
        String comment = "Go Go Go";

        CreateOrder request = new CreateOrder(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, colors);

        int track = given()
                .header("Content-type", "application/json")
                .and()
                .body(request)
                .when()
                .post("/api/v1/orders")
                .then().assertThat().statusCode(201)

                .extract().body().path("track");
        given()
                .header("Content-type", "application/json")
                .and()
                .body(track)
                .when()
                .put("/api/v1/orders/cancel");
    }
}

