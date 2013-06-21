package com.qmino.santorini.service;

import com.google.common.base.Strings;
import com.qmino.santorini.assembler.TravelerAssembler;
import com.qmino.santorini.assembler.TripAssembler;
import com.qmino.santorini.assembler.TripSummaryAssembler;
import com.qmino.santorini.dao.FakeDatabase;
import com.qmino.santorini.domain.Booking;
import com.qmino.santorini.domain.Traveler;
import com.qmino.santorini.domain.Trip;
import com.qmino.santorini.exception.BookingException;
import com.qmino.santorini.to.BookingConfirmationTo;
import com.qmino.santorini.to.BookingRequestTo;
import com.qmino.santorini.to.TravelerSummaryTo;
import com.qmino.santorini.to.TripSummaryTo;
import com.qmino.santorini.to.TripTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The TripService implementation.
 * <p/>
 * <p>
 * <i>Creation-Date</i>: 19/06/13<br>
 * <i>Creation-Time</i>: 16:31<br>
 * </p>
 *
 * @author Peter Rigole
 * @since SDK1.6
 */
@Service
public class TripServiceImpl implements TripService {

    // ToDo: add component-scan in applicationContext.xml
    @Autowired
    private FakeDatabase fakeDatabase;
    @Autowired
    private TripSummaryAssembler tripSummaryAssembler;
    @Autowired
    private TripAssembler tripAssembler;
    @Autowired
    private TravelerAssembler travelerAssembler;

    @Override
    public List<TripSummaryTo> getAllAvailableTrips() {
        List<Trip> trips = fakeDatabase.findAllTrips();
        return tripSummaryAssembler.createToList(trips);
    }

    @Override
    public TripTo getTripInfo(Long tripId) throws NoSuchElementException {
        Trip trip = fakeDatabase.findTripById(tripId);
        if (trip == null) {
            throw new NoSuchElementException("There is no trip with id " + tripId + ".");
        }
        return tripAssembler.createTo(trip);
    }

    @Override
    public BookingConfirmationTo bookTrip(BookingRequestTo bookingRequestTo) throws BookingException {
        checkIfValidBooking(bookingRequestTo);
        Trip trip = checkAndFetchTrip(bookingRequestTo.getTripId());
        List<Traveler> travelers = mergeWithExistingTravelers(bookingRequestTo.getTravelers());
        int numberOfTravelers = travelers.size();
        if (numberOfTravelers > trip.getAvailablePlaces()) {
            throw new BookingException(
                    "There is no place in trip " + trip.getTripName() + " for " + numberOfTravelers + " travelers.");
        }
        List<Booking> bookings = new ArrayList<Booking>(numberOfTravelers);
        for (Traveler traveler : travelers) {
            bookings.add(new Booking(trip, traveler));
        }
        fakeDatabase.storeTravelers(travelers);
        BookingConfirmationTo bookingConfirmation = new BookingConfirmationTo();
        bookingConfirmation.setTravelers(travelerAssembler.createToList(travelers));
        bookingConfirmation.setTripSummary(tripSummaryAssembler.createTo(trip));
        return bookingConfirmation;
    }

    /**
     * Transform the given traveler summary TO into traveler objects or fetch the existing traveler domain object
     * for travelers that already exist in the system (they already have an id).
     *
     * @param travelerSummaries The traveler summary TO objects that need to be transformed to domain objects.
     * @return The traveler domain objects corresponding to the given TOs.
     */
    private List<Traveler> mergeWithExistingTravelers(List<TravelerSummaryTo> travelerSummaries) {
        List<Traveler> travelers = new ArrayList<Traveler>();
        for (TravelerSummaryTo travelerSummary : travelerSummaries) {
            Traveler traveler = null;
            if (travelerSummary.getId() != null) {
                // There exists a traveler with this id if the result of this fetch is not null.
                traveler = fakeDatabase.findTravelerById(travelerSummary.getId());
            }
            if (traveler == null) {
                // We create a new traveler domain object.
                traveler = travelerAssembler.createEntity(travelerSummary);
            }
            travelers.add(traveler);
        }
        return travelers;
    }

    /**
     * Check if the given booking request is valid.
     *
     * @param bookingRequestTo The given booking request.
     * @throws BookingException The booking request is not valid.
     */
    private void checkIfValidBooking(BookingRequestTo bookingRequestTo) throws BookingException {
        if (bookingRequestTo == null) {
            throw new BookingException("No booking request received.");
        }
        if (bookingRequestTo.getTripId() == null) {
            throw new BookingException("No trip id present in booking request.");
        }
        if (bookingRequestTo.getTravelers() == null || bookingRequestTo.getTravelers().isEmpty()) {
            throw new BookingException("No travelers received with booking request.");
        }
        for (TravelerSummaryTo traveler : bookingRequestTo.getTravelers()) {
            checkIfValidTraveler(traveler);
        }
    }

    /**
     * Check if the given traveler object is valid.
     *
     * @param traveler The given traveler object.
     * @throws BookingException The traveler TO is not valid.
     */
    private void checkIfValidTraveler(TravelerSummaryTo traveler) throws BookingException {
        if (traveler == null) {
            throw new BookingException("Empty traveler data received.");
        }
        if (Strings.isNullOrEmpty(traveler.getFirstName())) {
            throw new BookingException("Empty traveler's first name received.");
        }
        if (Strings.isNullOrEmpty(traveler.getLastName())) {
            throw new BookingException("Empty traveler's last name received.");
        }
        if (Strings.isNullOrEmpty(traveler.getPassportNumber())) {
            throw new BookingException("Empty traveler's passport number received.");
        }
        if (Strings.isNullOrEmpty(traveler.getCity())) {
            throw new BookingException("Empty traveler's city received.");
        }
        if (Strings.isNullOrEmpty(traveler.getCountry())) {
            throw new BookingException("Empty traveler's country of residence received.");
        }
        if (Strings.isNullOrEmpty(traveler.getEmailAddress())) {
            throw new BookingException("Empty traveler's email address received.");
        }
        if (Strings.isNullOrEmpty(traveler.getHouseNumber())) {
            throw new BookingException("Empty traveler's house number received.");
        }
        if (Strings.isNullOrEmpty(traveler.getPhoneNumber())) {
            throw new BookingException("Empty traveler's phone number received.");
        }
        if (Strings.isNullOrEmpty(traveler.getStreet())) {
            throw new BookingException("Empty traveler's street name received.");
        }
        if (Strings.isNullOrEmpty(traveler.getZip())) {
            throw new BookingException("Empty traveler's zip code received.");
        }
    }

    /**
     * Fetch the trip domain object with the given id.
     *
     * @param tripId The trip id.
     * @return The trip with the requested id.
     * @throws BookingException There is no trip with the given id.
     */
    private Trip checkAndFetchTrip(Long tripId) throws BookingException {
        Trip trip = fakeDatabase.findTripById(tripId);
        if (trip == null) {
            throw new BookingException("There is no trip with id " + tripId + ".");
        }
        return trip;
    }

}