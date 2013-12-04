package com.qmino.santorini.to;

import java.util.List;

/**
 * A statistics transfer object.
 * <p/>
 * <p>
 * <i>Creation-Date</i>: 1/12/13<br>
 * <i>Creation-Time</i>: 15:29<br>
 * </p>
 *
 * @author Elliot Gossieaux
 */
public class StatisticsTo {

    private double avgBookingsPerTraveler;
    private double avgPaidForBooking;
    private List<Object[]> mostPopularTrips;

    public double getAvgBookingsPerTraveler() {
        return avgBookingsPerTraveler;
    }

    public void setAvgBookingsPerTraveler(double avgBookingsPerTraveler) {
        this.avgBookingsPerTraveler = avgBookingsPerTraveler;
    }

    public double getAvgPaidForBooking() {
        return avgPaidForBooking;
    }

    public void setAvgPaidForBooking(double avgPaidForBooking) {
        this.avgPaidForBooking = avgPaidForBooking;
    }

    public List<Object[]> getMostPopularTrips() {
        return mostPopularTrips;
    }

    public void setMostPopularTrips(List<Object[]> mostPopularTrips) {
        this.mostPopularTrips = mostPopularTrips;
    }
}
