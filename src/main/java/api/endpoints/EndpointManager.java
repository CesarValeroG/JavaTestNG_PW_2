package api.endpoints;

public class EndpointManager {
    public static final String BASE_URL = "https://restful-booker.herokuapp.com";
    public static final String BOOKING = BASE_URL + "/booking";
    public static final String BOOKING_BY_ID = BOOKING + "/%s"; // usar String.format(BOOKING_BY_ID, id)
}