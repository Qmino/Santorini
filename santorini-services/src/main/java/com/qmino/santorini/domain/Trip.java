package com.qmino.santorini.domain;

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
public class Trip extends IdentifiedObject {

    private String tripName;
    private String tripSummary;
    private String tripDescription;
    private Date startDate;
    private Date endDate;
    private double price;
    private int totalPlaces;
    private List<Booking> bookings;

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getTripSummary() {
        return tripSummary;
    }

    public void setTripSummary(String tripSummary) {
        this.tripSummary = tripSummary;
    }

    public String getTripDescription() {
        return tripDescription;
    }

    public void setTripDescription(String tripDescription) {
        this.tripDescription = tripDescription;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getAvailablePlaces() {
        return getTotalPlaces() - getBookings().size();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTotalPlaces() {
        return totalPlaces;
    }

    public void setTotalPlaces(int totalPlaces) {
        this.totalPlaces = totalPlaces;
    }

    public List<Booking> getBookings() {
        if (bookings == null) {
            bookings = new ArrayList<Booking>();
        }
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public void addBooking(Booking booking) {
        getBookings().add(booking);
    }

}