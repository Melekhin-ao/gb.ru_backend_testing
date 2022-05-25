package ru.geekbrains;

import io.restassured.RestAssured;
import net.javacrumbs.jsonunit.JsonAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;
import static net.javacrumbs.jsonunit.core.Option.IGNORING_ARRAY_ORDER;
import static org.hamcrest.Matchers.*;

public class RecipesTest {
    private static final String API_KEY = "a6db1d1b10de42a29ddd8513df5fd950";

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://api.spoonacular.com/recipes/";
    }

    @Test
    void testRecipeInfoSearch() throws IOException {
        String actually = given()
                .log()
                .all()
                .param("apiKey", API_KEY)
                .pathParam("id", 716429)
                .expect()
                .body("vegetarian", is(false))
                .body("glutenFree", is(false))
                .body("vegan", is(false))
                .body("cheap", is(false))
                .body("dairyFree", is(false))
                .body("veryHealthy", is(false))
                .body("veryPopular", is(false))
                .body("sustainable", is(false))
                .body("lowFodmap", is(false))
                .when()
                .get("{id}/information")
                .body()
                .prettyPrint();

        String expected = getResourceAsString("RecipeInfoExpected.json");

        JsonAssert.assertJsonEquals(
                expected,
                actually,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );
    }

    @Test
    void testSimilarRecipesSearch() throws IOException {

        String actually = given()
                .log()
                .all()
                .param("apiKey", API_KEY)
                .param("number", 1)
                .pathParam("id", 715538)
                .expect()
                .when()
                .get("{id}/similar")
                .prettyPrint();

        String expected = getResourceAsString("SimilarRecipesExpected.json");

        JsonAssert.assertJsonEquals(
                expected,
                actually,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );
    }

    @Test
    void testPriceBreakdown() throws IOException {

        String actually = given()
                .log()
                .all()
                .param("apiKey", API_KEY)
                .pathParam("id", 1003464)
                .expect()
                .when()
                .get("{id}/priceBreakdownWidget.json")
                .prettyPrint();

        String expected = getResourceAsString("PriceBreakdownExpected.json");

        JsonAssert.assertJsonEquals(
                expected,
                actually,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );
    }

    @Test
    void testNutritionByIdSearch() throws IOException {

        String actually = given()
                .log()
                .all()
                .param("apiKey", API_KEY)
                .pathParam("id", 1003464)
                .expect()
                .body("calories", is("316k"))
                .body("carbs", is("49g"))
                .body("fat", is("12g"))
                .body("protein", is("3g"))
                .when()
                .get("{id}/nutritionWidget.json")
                .prettyPrint();

        String expected = getResourceAsString("NutritionByIdExpected.json");

        JsonAssert.assertJsonEquals(
                expected,
                actually,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );
    }

    @Test
    void testSummarizeRecipeSearch() throws IOException {

        given()
                .log()
                .all()
                .param("apiKey", API_KEY)
                .pathParam("id", 4632)
                .expect()
                .body("id", is(4632))
                .body("title", is("Soy-and-Ginger-Glazed Salmon with Udon Noodles"))
                .when()
                .get("{id}/summary")
                .prettyPrint();
    }

    public String getResourceAsString(String resource) throws IOException {
        InputStream stream = getClass().getResourceAsStream(resource);
        assert stream != null;
        byte[] bytes = stream.readAllBytes();
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
