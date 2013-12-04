package com.qmino.santorini.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Trip domain object.
 * <p/>
 * <p>
 * <i>Creation-Date</i>: 19/06/13<br>
 * <i>Creation-Time</i>: 16:37<br>
 * </p>
 *
 * @author Peter Rigole
 * @since SDK1.6
 */
@Entity
@Table( name = "TRIPS" )
public class Trip {
    private Long id;

    private String tripName;
    private String tripSummary;
    private String tripDescription;
    private Date startDate;
    private Date endDate;
    private double price;
    private int totalPlaces;
    private List<Booking> bookings  = new ArrayList<Booking>(0);

    public Trip() {
        // for use by hibernate
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

    @Column(nullable = false)
    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    @Column(nullable = false)
    public String getTripSummary() {
        return tripSummary;
    }

    public void setTripSummary(String tripSummary) {
        this.tripSummary = tripSummary;
    }

    @Column(nullable = false)
    public String getTripDescription() {
        return tripDescription;
    }

    public void setTripDescription(String tripDescription) {
        this.tripDescription = tripDescription;
    }

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Transient
    public int getAvailablePlaces() {
      return getTotalPlaces() - getBookings().size();
    }

    @Column(columnDefinition = "NUMERIC", nullable = false)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Column(nullable = false)
    public int getTotalPlaces() {
        return totalPlaces;
    }

    public void setTotalPlaces(int totalPlaces) {
        this.totalPlaces = totalPlaces;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "trip")
    public List<Booking> getBookings() {
        return this.bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public void addBooking(Booking booking) {
        getBookings().add(booking);
    }
}