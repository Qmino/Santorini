package com.qmino.santorini.to;

import java.util.Date;

/**
 * A trip transfer object.
 * <p/>
 * <p>
 * <i>Creation-Date</i>: 19/06/13<br>
 * <i>Creation-Time</i>: 16:11<br>
 * </p>
 *
 * @author Peter Rigole
 * @since SDK1.6
 */
public class TripTo {

    private Long Id;
    private String tripName;
    private String tripSummary;
    private String tripDescription;
    private Date startDate;
    private Date endDate;
    private Integer availablePlaces;
    private Integer totalPlaces;
    private Double price;

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

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
        return availablePlaces;
    }

    public void setAvailablePlaces(Integer availablePlaces) {
        this.availablePlaces = availablePlaces;
    }

    public Integer getTotalPlaces() {
        return totalPlaces;
    }

    public void setTotalPlaces(Integer totalPlaces) {
        this.totalPlaces = totalPlaces;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}