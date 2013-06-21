package com.qmino.santorini.assembler;

import com.qmino.santorini.domain.Trip;
import com.qmino.santorini.to.TripTo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * A trip assembler. Creates a TripTo object form a given Trip object.
 * <p/>
 * <p>
 * <i>Creation-Date</i>: 19/06/13<br>
 * <i>Creation-Time</i>: 16:50<br>
 * </p>
 *
 * @author Peter Rigole
 * @since SDK1.6
 */
@Component
public class TripAssembler {

    public TripTo createTo(Trip trip) {
        if (trip == null) {
            return null;
        }
        TripTo tripTo = new TripTo();
        tripTo.setTripName(trip.getTripName());
        tripTo.setTripSummary(trip.getTripSummary());
        tripTo.setTripDescription(trip.getTripDescription());
        tripTo.setStartDate(trip.getStartDate());
        tripTo.setEndDate(trip.getEndDate());
        tripTo.setAvailablePlaces(trip.getAvailablePlaces());
        tripTo.setTotalPlaces(trip.getTotalPlaces());
        tripTo.setPrice(trip.getPrice());
        return tripTo;
    }

    public List<TripTo> createToList(List<Trip> trips) {
        List<TripTo> tripSummaries = new ArrayList<TripTo>();
        for (Trip trip : trips) {
            tripSummaries.add(createTo(trip));
        }
        return tripSummaries;
    }

}