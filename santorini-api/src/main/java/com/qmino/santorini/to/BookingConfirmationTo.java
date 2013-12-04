package com.qmino.santorini.to;

import java.util.List;

/**
 * A booking confirmation transfer object.
 * <p/>
 * <p>
 * <i>Creation-Date</i>: 19/06/13<br>
 * <i>Creation-Time</i>: 16:17<br>
 * </p>
 *
 * @author Peter Rigole
 * @since SDK1.6
 */
public class BookingConfirmationTo {

    private List<BookingSummaryTo> bookings;

    public List<BookingSummaryTo> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingSummaryTo> bookings) {
        this.bookings = bookings;
    }
}