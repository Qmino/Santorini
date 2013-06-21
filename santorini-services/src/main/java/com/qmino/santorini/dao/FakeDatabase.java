package com.qmino.santorini.dao;

import com.qmino.santorini.domain.Traveler;
import com.qmino.santorini.domain.Trip;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * A fake in-memory database.
 * <p/>
 * <p>
 * <i>Creation-Date</i>: 19/06/13<br>
 * <i>Creation-Time</i>: 16:35<br>
 * </p>
 *
 * @author Peter Rigole
 * @since SDK1.6
 */
@Repository
public class FakeDatabase {

    private long tripIdSequence = 0;
    private long travelerIdSequence = 0;
    private final List<Trip> trips = new ArrayList<Trip>();
    private final List<Traveler> travelers = new ArrayList<Traveler>();

    public FakeDatabase() {
        // ToDo: add trips at startup.
    }

    public List<Trip> findAllTrips() {
        return trips;
    }

    public Trip findTripById(Long tripId) {
        for (Trip trip : trips) {
            if (tripId.equals(trip.getId())) {
                return trip;
            }
        }
        return null;
    }

    public Traveler findTravelerById(Long id) {
        for (Traveler currentTraveler : travelers) {
            if (id.equals(currentTraveler.getId())) {
                return currentTraveler;
            }
        }
        return null;
    }

    public void storeTravelers(List<Traveler> travelers) {
        for (Traveler traveler : travelers) {
            if (traveler.getId() == null) {
                traveler.setId(travelerIdSequence++);
                travelers.add(traveler);
            } // Those with an id are already stored.
        }
    }

}