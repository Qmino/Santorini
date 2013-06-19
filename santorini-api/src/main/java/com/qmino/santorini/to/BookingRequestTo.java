package com.qmino.santorini.to;

import java.util.List;

/**
 * No comment provided yet for this class.
 * <p/>
 * <p>
 * <i>Creation-Date</i>: 19/06/13<br>
 * <i>Creation-Time</i>: 16:18<br>
 * </p>
 *
 * @author Peter Rigole
 * @since SDK1.6
 */
public class BookingRequestTo {

    private Long tripId;
    private List<TravelerTo> travelers;

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public List<TravelerTo> getTravelers() {
        return travelers;
    }

    public void setTravelers(List<TravelerTo> travelers) {
        this.travelers = travelers;
    }
}