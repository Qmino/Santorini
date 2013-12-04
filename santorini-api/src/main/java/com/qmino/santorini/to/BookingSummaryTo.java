package com.qmino.santorini.to;

/**
 * A booking summary transfer object.
 * <p/>
 * <p>
 * <i>Creation-Date</i>: 1/12/13<br>
 * <i>Creation-Time</i>: 12:42<br>
 * </p>
 *
 * @author Elliot Gossieaux
 */
public class BookingSummaryTo {

    private Long id;
    private Long tripId;
    private Long travelerId;
    private Double pricePaid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public Long getTravelerId() {
        return travelerId;
    }

    public void setTravelerId(Long travelerId) {
        this.travelerId = travelerId;
    }

    public Double getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(Double pricePaid) {
        this.pricePaid = pricePaid;
    }
}
