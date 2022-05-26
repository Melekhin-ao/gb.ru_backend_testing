package ru.geekbrains;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

public class ImageClassificationTest extends SpoonaccularTest {

    @Test
    void testBurgerClassification() {
        given()
                .multiPart(getFile("burger.jpg"))
                .expect()
                .body("category", is("burger"))
                .body("probability", greaterThan(0.7F))
                .when()
                .post("food/images/classify");
    }

    @Test
    void testIceCreamClassification() {
        given()
                .multiPart(getFile("icecream.png"))
                .expect()
                .body("category", is("ice_cream"))
                .body("probability", greaterThan(0.7F))
                .when()
                .post("food/images/classify");
    }
}
