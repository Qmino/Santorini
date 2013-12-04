package com.qmino.santorini.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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

@Entity
@Table( name = "BOOKINGS" )
public class Booking {

    private Long id;

    private Trip trip;
    private Traveler traveler;
    private Double pricePaid;

    public Booking() {
        // for use by hibernate
    }

    public Booking(Trip trip, Traveler traveler, Double pricePaid) {
        // for application use, to create new bookings
        this.trip = trip;
        this.trip.addBooking(this);
        this.traveler = traveler;
        this.traveler.addBooking(this);
        this.pricePaid = pricePaid;
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRIP_ID", nullable = false)
    public Trip getTrip() {
        return this.trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRAVELER_ID", nullable = false)
    public Traveler getTraveler() {
        return this.traveler;
    }

    public void setTraveler(Traveler traveler) {
        this.traveler = traveler;
    }

    @Column(columnDefinition = "NUMERIC", nullable = false)
    public Double getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(Double pricePaid) {
        this.pricePaid = pricePaid;
    }
}