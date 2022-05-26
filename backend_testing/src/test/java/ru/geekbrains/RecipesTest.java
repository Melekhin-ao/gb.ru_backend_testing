package ru.geekbrains;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RecipesTest extends SpoonaccularTest {

    @Test
    void testRecipeInfoSearch() throws Exception {
        String actually = given()
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
                .get("recipes/{id}/information")
                .body()
                .prettyPrint();

        String expected = getResource("RecipeInfoExpected.json");
        assertJson(expected, actually);
    }

    @Test
    void testSimilarRecipesSearch() throws Exception {

        String actually = given()
                .param("number", 1)
                .pathParam("id", 715538)
                .expect()
                .when()
                .get("recipes/{id}/similar")
                .prettyPrint();

        String expected = getResource("SimilarRecipesExpected.json");
        assertJson(expected, actually);
    }

    @Test
    void testPriceBreakdown() throws Exception {

        String actually = given()
                .pathParam("id", 1003464)
                .expect()
                .when()
                .get("recipes/{id}/priceBreakdownWidget.json")
                .prettyPrint();

        String expected = getResource("PriceBreakdownExpected.json");
        assertJson(expected, actually);
    }

    @Test
    void testNutritionByIdSearch() throws Exception {

        String actually = given()
                .pathParam("id", 1003464)
                .expect()
                .body("calories", is("316k"))
                .body("carbs", is("49g"))
                .body("fat", is("12g"))
                .body("protein", is("3g"))
                .when()
                .get("recipes/{id}/nutritionWidget.json")
                .prettyPrint();

        String expected = getResource("NutritionByIdExpected.json");
        assertJson(expected, actually);
    }

        @Test
        void testSummarizeRecipeSearch () throws Exception {
            given()
                    .pathParam("id", 4632)
                    .expect()
                    .body("id", is(4632))
                    .body("title", is("Soy-and-Ginger-Glazed Salmon with Udon Noodles"))
                    .when()
                    .get("recipes/{id}/summary")
                    .prettyPrint();
        }
    }

