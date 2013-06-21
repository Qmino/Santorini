package com.qmino.santorini.domain;

import com.qmino.santorini.common.domain.IdentifiedObject;

/**
 * No comment provided yet for this class.
 * <p/>
 * <p>
 * <i>Creation-Date</i>: 19/06/13<br>
 * <i>Creation-Time</i>: 16:57<br>
 * </p>
 *
 * @author Peter Rigole
 * @since SDK1.6
 */
public class Booking extends IdentifiedObject {

    private Trip trip;
    private Traveler traveler;
    private Double pricePaid;

    public Booking() {
    }

    public Booking(Trip trip, Traveler traveler) {
        this.trip = trip;
        this.trip.addBooking(this);
        this.traveler = traveler;
        this.traveler.addBooking(this);
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Traveler getTraveler() {
        return traveler;
    }

    public void setTraveler(Traveler traveler) {
        this.traveler = traveler;
    }

    public Double getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(Double pricePaid) {
        this.pricePaid = pricePaid;
    }

}