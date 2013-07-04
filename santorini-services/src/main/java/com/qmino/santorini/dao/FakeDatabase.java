package com.qmino.santorini.dao;

import com.qmino.santorini.domain.Traveler;
import com.qmino.santorini.domain.Trip;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        addDummyData();
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

    public void storeTravelers(List<Traveler> newTravelers) {
        for (Traveler traveler : newTravelers) {
            if (traveler.getId() == null) {
                traveler.setId(travelerIdSequence++);
                travelers.add(traveler);
            } // Those with an id are already stored.
        }
    }

    private void addDummyData() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Trip trip01 = new Trip();
            trip01.setId(1L);
            trip01.setTripName("Best of the Sahara");
            trip01.setTripSummary("As an avid hiker you gonna have a lot of fun walking through the Sahara.");
            trip01.setTripDescription("We gonna have a very cool walk straight through the Sahara. There is a pickup truck following behind for those who get bored of walking. Upon availability, camels may help you out as wel.");
            trip01.setPrice(550);
            trip01.setTotalPlaces(10);
            trip01.setStartDate(format.parse("25/05/2014"));
            trip01.setEndDate(format.parse("12/06/2014"));
            trips.add(trip01);
            Trip trip02 = new Trip();
            trip02.setId(2L);
            trip02.setTripName("Sail Santorini");
            trip02.setTripSummary("We're going to sail around in the Ionian Sea exploring one island after another.");
            trip02.setTripDescription("Sailing off from the island of Kos, we will discover several interesting islands on our journey to the island of Santorini.");
            trip02.setPrice(950);
            trip02.setTotalPlaces(5);
            trip02.setStartDate(format.parse("21/06/2014"));
            trip02.setEndDate(format.parse("06/07/2014"));
            trips.add(trip02);
            Trip trip03 = new Trip();
            trip03.setId(3L);
            trip03.setTripName("The cool Siberia");
            trip03.setTripSummary("Expect the unexpected on the extremely cool dog sled trip throughout the deepest secrets of Siberia.");
            trip03.setTripDescription("You will be dropped by helicopter into a ancient nomadic tribe where you will get your own dog sled and set start for an unforgettable journey through the coolest places on our planet earth.");
            trip03.setPrice(600);
            trip03.setTotalPlaces(8);
            trip03.setStartDate(format.parse("02/12/2014"));
            trip03.setEndDate(format.parse("02/01/2015"));
            trips.add(trip03);
        } catch (ParseException ignored) {

        }
    }

}