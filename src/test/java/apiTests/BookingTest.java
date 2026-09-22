package apiTests;

import api.models.Booking;
import api.builders.BookingBuilder;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingTest extends BaseApiTest {

    private int bookingId;

    @Test(priority = 1)
    public void createBookingTest() {
        Booking booking = new BookingBuilder()
                .withFirstname("Jim")
                .withLastname("Brown")
                .withTotalprice(111)
                .withDepositpaid(true)
                .withBookingdates("2018-07-01", "2019-07-20")
                .withAdditionalneeds("Breakfast")
                .build();

        Response response = apiClient.createBooking(booking);
        Assert.assertEquals(response.statusCode(), 200);

        bookingId = response.jsonPath().getInt("bookingid");
        System.out.println("Booking ID: " + bookingId);
        logger.info("Booking created with ID: " + bookingId);
    }

    @Test(priority = 2, dependsOnMethods = "createBookingTest")
    public void getBookingTest() {
        Response response = apiClient.getBooking(bookingId);
        Assert.assertEquals(response.statusCode(), 200);

        Booking booking = response.as(Booking.class);  // deserialize the response to Booking object
        Assert.assertEquals(booking.getFirstname(), "Jim");
        Assert.assertEquals(booking.getLastname(), "Brown");
        logger.info("Booking retrieved with ID: " + bookingId);
    }

    @Test(priority = 3, dependsOnMethods = "getBookingTest")
    public void updateBookingTest() {
        Booking updatedBooking = new BookingBuilder()
                .withFirstname("James")
                .withLastname("Bond")
                .withTotalprice(255)
                .withDepositpaid(false)
                .withBookingdates("2020-08-06", "2020-08-20")
                .withAdditionalneeds("Lunch")
                .build();

        Response response = apiClient.updateBooking(bookingId, updatedBooking);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("firstname"), "James");
        logger.info("Booking updated with ID: " + bookingId);
    }
    // Delete
    @Test(priority = 4, dependsOnMethods = "updateBookingTest")
    public void deleteBookingTest() {
        Response response = apiClient.deleteBooking(bookingId);
        Assert.assertEquals(response.statusCode(), 201);
        logger.info("Booking deleted with ID: " + bookingId);
    }
}