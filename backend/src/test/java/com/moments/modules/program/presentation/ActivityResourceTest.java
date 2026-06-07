package com.moments.modules.program.presentation;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class ActivityResourceTest {

    private static final String ACTIVITY_ID = "40000000-0000-0000-0000-000000000001";
    private static final String UNKNOWN_ID = "99999999-9999-9999-9999-999999999999";

    @Test
    void shouldReturnActivityById() {
        given()
                .when()
                .get("/activities/{id}", ACTIVITY_ID)
                .then()
                .statusCode(200)
                .body("id", equalTo(ACTIVITY_ID))
                .body("title", equalTo("Morning Hike"))
                .body("location", equalTo("Mountain Trail"))
                .body("description", equalTo("A beautiful morning hike"))
                .body("timeslot.start", notNullValue())
                .body("timeslot.end", notNullValue())
                .body("planner", notNullValue())
                .body("planner.id", equalTo("10000000-0000-0000-0000-000000000001"))
                .body("planner.lastName", equalTo("Doe"))
                .body("planner.firstName", equalTo("John"))
                .body("participants", hasSize(1));
    }

    @Test
    void shouldReturn404WhenActivityNotFound() {
        given()
                .when()
                .get("/activities/{id}", UNKNOWN_ID)
                .then()
                .statusCode(404)
                .body("error", equalTo("RESOURCE_NOT_FOUND"))
                .body("message", containsString("Activity not found"));
    }

    @Test
    void shouldReturnJsonContentType() {
        given()
                .when()
                .get("/activities/{id}", ACTIVITY_ID)
                .then()
                .contentType("application/json");
    }

    @Test
    void shouldReturnParticipantsWithDetails() {
        given()
                .when()
                .get("/activities/{id}", ACTIVITY_ID)
                .then()
                .statusCode(200)
                .body("participants[0].id", equalTo("10000000-0000-0000-0000-000000000002"))
                .body("participants[0].lastName", equalTo("Smith"))
                .body("participants[0].firstName", equalTo("Jane"));
    }

    @Test
    void shouldReturnTimeslotWithStartAndEnd() {
        given()
                .when()
                .get("/activities/{id}", ACTIVITY_ID)
                .then()
                .statusCode(200)
                .body("timeslot.start", notNullValue())
                .body("timeslot.end", notNullValue());
    }

    @Test
    void shouldReturn200ForPostActivity() {
        given()
                .contentType("application/json")
                .body("""
                            {
                                "title": "New Activity",
                                "location": "Park",
                                "description": "Fun activity",
                                "plannerId": "10000000-0000-0000-0000-000000000001",
                                "start": "2025-07-20T08:00:00",
                                "end": "2025-07-20T12:00:00",
                                "eventId": "20000000-0000-0000-0000-000000000001"
                            }
                        """)
                .when()
                .post("/activities")
                .then()
                .statusCode(200);
    }
}
