package com.antogian.boringapp;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class AttributeIntegrationTests extends BoringAppTests {

    //GET
    @Test
    public void testShouldSuccessfullyGetAttributes() {
        given()
                .when()
                .get("/api/v1/attributes")
                .then()
                .contentType(JSON)
                .statusCode(HttpStatus.OK.value());
    }

    //PUT
    @Test
    public void testShouldSuccessfullyCreateAttribute() {
        final String putJson = "{\n" +
                "  \"category\": \"cat999\",\n" +
                "  \"description\": \"baz\"" +
                " \n}";
        given()
                .header("Content-type", "application/json")
                .and()
                .body(putJson)
                .when()
                .put("/api/v1/attributes")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    //GET
    @Test
    public void testShouldSuccessfullyGetAttributesByCategory() {
        given()
                .when()
                .get("/api/v1/attributes/categories?category=cat01")
                .then()
                .contentType(JSON)
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void testShouldSuccessfullyCreateAndRetrieveAttribute() {
        given()
                .when()
                .get("/api/v1/attributes/categories?category=xyz")
                .then()
                .body("size()", is(0));
        final String putJson = "{\n" +
                "  \"category\": \"xyz\",\n" +
                "  \"description\": \"lorem ipsum\"" +
                " \n}";
        given()
                .header("Content-type", "application/json")
                .and()
                .body(putJson)
                .when()
                .put("/api/v1/attributes")
                .then()
                .statusCode(HttpStatus.OK.value());
        Response response = given()
                .when()
                .get("/api/v1/attributes/categories?category=xyz")
                .then()
                .contentType(JSON)
                .extract().response();
        Assertions.assertEquals(response.body().jsonPath().getString("description[0]"), "lorem ipsum");
    }

}
