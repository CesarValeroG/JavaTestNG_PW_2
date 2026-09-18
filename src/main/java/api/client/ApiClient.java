package api.client;

import api.endpoints.EndpointManager;
import api.models.Booking;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiClient {
    public Response createBooking(Booking booking) {
        return given()
                .contentType(ContentType.JSON)
                .body(booking)
                .log().uri()
                .when().post(EndpointManager.BOOKING)
                .then().log().all()
                .extract().response();
    }

    public Response getBooking(int bookingId) {
        return given()
                .contentType(ContentType.JSON)
                .log().uri()
                .when().get(String.format(EndpointManager.BOOKING_BY_ID, bookingId))
                .then().log().all()
                .extract().response();
    }

    public Response updateBooking(int bookingId, Booking booking) {
        return given()
                .contentType(ContentType.JSON)
                .auth().preemptive().basic("admin", "password123")
                .body(booking)
                .log().uri()
                .when().put(String.format(EndpointManager.BOOKING_BY_ID, bookingId))
                .then().log().all()
                .extract().response();
    }

    public Response deleteBooking(int bookingId) {
        return given()
                .contentType(ContentType.JSON)
                .auth().preemptive().basic("admin", "password123")
                .log().uri()
                .when().delete(String.format(EndpointManager.BOOKING_BY_ID, bookingId))
                .then().log().all()
                .extract().response();
    }
}