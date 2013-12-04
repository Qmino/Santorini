package com.qmino.santorini.to;

/**
 * A booking transfer object.
 * <p/>
 * <p>
 * <i>Creation-Date</i>: 29/11/13<br>
 * <i>Creation-Time</i>: 14:54<br>
 * </p>
 *
 * @author Elliot Gossieaux
 */
public class BookingTo {

    private Long id;
    private TripTo trip;
    private TravelerSummaryTo traveler;
    private Double pricePaid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TripTo getTrip() {
        return trip;
    }

    public void setTrip(TripTo trip) {
        this.trip = trip;
    }

    public TravelerSummaryTo getTraveler() {
        return traveler;
    }

    public void setTraveler(TravelerSummaryTo traveler) {
        this.traveler = traveler;
    }

    public Double getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(Double pricePaid) {
        this.pricePaid = pricePaid;
    }
}
