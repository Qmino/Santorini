package com.qmino.santorini.assembler;

import com.qmino.santorini.domain.Booking;
import com.qmino.santorini.domain.Trip;
import com.qmino.santorini.to.TripSummaryTo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * A trip summary assembler. Creates a TripSummaryTo object form a given Trip object.
 * <p/>
 * <p>
 * <i>Creation-Date</i>: 19/06/13<br>
 * <i>Creation-Time</i>: 16:40<br>
 * </p>
 *
 * @author Peter Rigole
 * @since SDK1.6
 */
@Component
public class TripSummaryAssembler {

    private TripSummaryTo createTo(Trip trip) {
        if (trip == null) {
            return null;
        }
        TripSummaryTo tripSummaryTo = new TripSummaryTo();
        tripSummaryTo.setTripName(trip.getTripName());
        tripSummaryTo.setTripSummary(trip.getTripSummary());
        tripSummaryTo.setStartDate(trip.getStartDate());
        tripSummaryTo.setEndDate(trip.getEndDate());
        tripSummaryTo.setAvailablePlaces(trip.getAvailablePlaces());
        tripSummaryTo.setTotalPlaces(trip.getTotalPlaces());
        tripSummaryTo.setPrice(trip.getPrice());
        return tripSummaryTo;
    }

    public List<TripSummaryTo> createToList(List<Trip> trips) {
        List<TripSummaryTo> tripSummaries = new ArrayList<TripSummaryTo>();
        for (Trip trip : trips) {
            tripSummaries.add(createTo(trip));
        }
        return tripSummaries;
    }
}