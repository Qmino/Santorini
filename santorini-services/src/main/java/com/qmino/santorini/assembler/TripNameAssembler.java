package com.qmino.santorini.assembler;

import com.qmino.santorini.domain.Trip;
import com.qmino.santorini.to.TripNameTo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * A trip name assembler. Creates a TripNameTo object form a given Trip object and vice versa.
 * <p/>
 * <p>
 * <i>Creation-Date</i>: 1/12/13<br>
 * <i>Creation-Time</i>: 12:51<br>
 * </p>
 *
 * @author Elliot Gossieaux
 */
@Component
public class TripNameAssembler {

    private TripNameTo createTo(Trip trip) {
        if (trip == null) {
            return null;
        }
        TripNameTo tripNameTo = new TripNameTo();
        tripNameTo.setId(trip.getId());
        tripNameTo.setTripName(trip.getTripName());
        return tripNameTo;
    }

    public List<TripNameTo> createToList(List<Trip> trips) {
        List<TripNameTo> tripNames = new ArrayList<TripNameTo>();
        for (Trip trip : trips) {
            tripNames.add(createTo(trip));
        }
        return tripNames;
    }
}