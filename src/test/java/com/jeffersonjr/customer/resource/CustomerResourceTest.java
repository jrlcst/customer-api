package com.jeffersonjr.customer.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
class CustomerResourceTest {

    @Test
    void shouldReturnExistingCustomer() {
        given()
                .when().get("/v1/customers/cus-001")
                .then()
                .statusCode(200)
                .body("id", is("cus-001"))
                .body("name", is("Maria Silva"))
                .body("document", is("12345678900"))
                .body("status", is("ACTIVE"));
    }

    @Test
    void shouldReturnBlockedCustomer() {
        given()
                .when().get("/v1/customers/cus-002")
                .then()
                .statusCode(200)
                .body("status", is("BLOCKED"));
    }

    @Test
    void shouldReturnNotFoundForMissingCustomer() {
        given()
                .when().get("/v1/customers/cus-999")
                .then()
                .statusCode(404);
    }
}