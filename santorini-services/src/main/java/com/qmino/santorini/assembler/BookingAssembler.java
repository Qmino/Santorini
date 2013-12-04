package com.qmino.santorini.assembler;

import com.qmino.santorini.domain.Booking;
import com.qmino.santorini.to.BookingTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A booking assembler. Creates a BookingTo object form a given Booking object and vice versa.
 * <p/>
 * <p>
 * <i>Creation-Date</i>: 2/12/13<br>
 * <i>Creation-Time</i>: 10:27<br>
 * </p>
 *
 * @author Elliot Gossieaux
 */
@Component
public class BookingAssembler {

    @Autowired
    private TripAssembler tripAssembler;
    @Autowired
    private TravelerAssembler travelerAssembler;

    public BookingTo createTo(Booking booking) {
        if (booking == null) {
            return null;
        }
        BookingTo bookingTo = new BookingTo();
        bookingTo.setId(booking.getId());
        bookingTo.setTrip(tripAssembler.createTo(booking.getTrip()));
        bookingTo.setTraveler(travelerAssembler.createTo(booking.getTraveler()));
        bookingTo.setPricePaid(booking.getPricePaid());
        return bookingTo;
    }
}
