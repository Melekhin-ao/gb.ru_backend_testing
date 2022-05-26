package ru.geekbrains;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

public class ImageAnalysisTest extends SpoonaccularTest {

    @Test
    void testBurgerAnalysis() {
        given()
                .multiPart(getFile("burger.jpg"))
                .expect()
                .when()
                .post("food/images/analyze");
    }

    @Test
    void testIceCreamAnalysis() {
        given()
                .multiPart(getFile("icecream.png"))
                .expect()
                .when()
                .post("food/images/analyze");
    }
}
