package com.qmino.santorini.assembler;

import com.qmino.santorini.domain.Booking;
import com.qmino.santorini.to.BookingSummaryTo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * A booking summary assembler. Creates a BookingSummaryTo object form a given Booking object and vice versa.
 * <p/>
 * <p>
 * <i>Creation-Date</i>: 1/12/13<br>
 * <i>Creation-Time</i>: 12:51<br>
 * </p>
 *
 * @author Elliot Gossieaux
 */
@Component
public class BookingSummaryAssembler {

    private BookingSummaryTo createTo(Booking booking) {
        if (booking == null) {
            return null;
        }
        BookingSummaryTo bookingSummaryTo = new BookingSummaryTo();
        bookingSummaryTo.setId(booking.getId());
        bookingSummaryTo.setTripId(booking.getTrip().getId());
        bookingSummaryTo.setTravelerId(booking.getTraveler().getId());
        bookingSummaryTo.setPricePaid(booking.getPricePaid());
        return bookingSummaryTo;
    }

    public List<BookingSummaryTo> createToList(List<Booking> bookings) {
        List<BookingSummaryTo> bookingSummaries = new ArrayList<BookingSummaryTo>();
        for (Booking booking : bookings) {
            bookingSummaries.add(createTo(booking));
        }
        return bookingSummaries;
    }
}
