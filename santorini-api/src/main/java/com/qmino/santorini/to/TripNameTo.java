package com.qmino.santorini.to;

/**
 * A trip name transfer object.
 * <p/>
 * <p>
 * <i>Creation-Date</i>: 1/12/13<br>
 * <i>Creation-Time</i>: 15:29<br>
 * </p>
 *
 * @author Elliot Gossieaux
 */
public class TripNameTo {

    private Long Id;
    private String tripName;

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
}