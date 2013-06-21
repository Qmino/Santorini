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

    private TripSummaryTo tripSummary;
    private List<TravelerSummaryTo> travelers;

    public TripSummaryTo getTripSummary() {
        return tripSummary;
    }

    public void setTripSummary(TripSummaryTo tripSummary) {
        this.tripSummary = tripSummary;
    }

    public List<TravelerSummaryTo> getTravelers() {
        return travelers;
    }

    public void setTravelers(List<TravelerSummaryTo> travelers) {
        this.travelers = travelers;
    }

}