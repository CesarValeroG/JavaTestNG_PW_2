package api.builders;

import api.models.Booking;
import api.models.BookingDates;

public class BookingBuilder {
    private final Booking booking = new Booking();

    public BookingBuilder withFirstname(String firstname) {
        booking.setFirstname(firstname);
        return this;
    }

    public BookingBuilder withLastname(String lastname) {
        booking.setLastname(lastname);
        return this;
    }

    public BookingBuilder withTotalprice(int totalprice) {
        booking.setTotalprice(totalprice);
        return this;
    }

    public BookingBuilder withDepositpaid(boolean depositpaid) {
        booking.setDepositpaid(depositpaid);
        return this;
    }

    public BookingBuilder withBookingdates(String checkin, String checkout) {
        BookingDates dates = new BookingDates();
        dates.setCheckin(checkin);
        dates.setCheckout(checkout);
        booking.setBookingdates(dates);
        return this;
    }

    public BookingBuilder withAdditionalneeds(String additionalneeds) {
        booking.setAdditionalneeds(additionalneeds);
        return this;
    }

    public Booking build() {
        return booking;
    }
}
