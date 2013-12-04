package com.qmino.santorini.service;

import com.google.common.base.Strings;
import com.qmino.santorini.assembler.*;
import com.qmino.santorini.dao.FakeDatabase;
import com.qmino.santorini.domain.Booking;
import com.qmino.santorini.domain.Traveler;
import com.qmino.santorini.domain.Trip;
import com.qmino.santorini.exception.BookingException;
import com.qmino.santorini.to.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(readOnly = true)
public class TripServiceImpl implements TripService {

    // ToDo: add component-scan in applicationContext.xml
    @Autowired
    private FakeDatabase fakeDatabase;
    @Autowired
    private BookingAssembler bookingAssembler;
    @Autowired
    private BookingSummaryAssembler bookingSummaryAssembler;
    @Autowired
    private TripSummaryAssembler tripSummaryAssembler;
    @Autowired
    private TripNameAssembler tripNameAssembler;
    @Autowired
    private TripAssembler tripAssembler;
    @Autowired
    private TravelerAssembler travelerAssembler;

    @Override
    public List<TripNameTo> getAllAvailableTrips() {
        List<Trip> trips = fakeDatabase.findAllTrips();
        return tripNameAssembler.createToList(trips);
    }

    @Override
    public BookingTo getBookingInfo(Long bookingId) throws NoSuchElementException {
        Booking booking = fakeDatabase.findBookingById(bookingId);
        if (booking == null) {
            throw new NoSuchElementException("There is no booking with id " + bookingId + ".");
        }
        return bookingAssembler.createTo(booking);
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
    public TravelerSummaryTo getTravelerInfo(Long travelerId) throws NoSuchElementException {
        Traveler traveler = fakeDatabase.findTravelerById(travelerId);
        if (traveler == null) {
            throw new NoSuchElementException("There is no traveler with id " + travelerId + ".");
        }
        return travelerAssembler.createTo(traveler);
    }

    @Override
    public List<TripSummaryTo> getTravelerTrips(Long travelerId) throws NoSuchElementException {
        Traveler traveler = fakeDatabase.findTravelerById(travelerId);
        if (traveler == null) {
            throw new NoSuchElementException("There is no traveler with id " + travelerId + ".");
        }
        return tripSummaryAssembler.createToList(traveler.getTrips());
    }

    @Override
    @Transactional(readOnly = false)
    public BookingConfirmationTo bookTrip(BookingRequestTo bookingRequestTo) throws BookingException {
        checkIfValidBookingRequest(bookingRequestTo);
        Trip trip = checkAndFetchTrip(bookingRequestTo.getTripId());
        List<Traveler> travelers = mergeWithExistingTravelers(bookingRequestTo.getTravelers());
        int numberOfTravelers = travelers.size();
        if (numberOfTravelers > trip.getAvailablePlaces()) {
            throw new BookingException(
                    "There is no place in trip " + trip.getTripName() + " for " + numberOfTravelers + " travelers.");
        }
        List<Booking> bookings = new ArrayList<Booking>(numberOfTravelers);
        for (Traveler traveler : travelers) {
            bookings.add(new Booking(trip, traveler, trip.getPrice()));
        }
        fakeDatabase.storeTravelers(travelers);
        fakeDatabase.storeBookings(bookings);
        BookingConfirmationTo bookingConfirmation = new BookingConfirmationTo();
        bookingConfirmation.setBookings(bookingSummaryAssembler.createToList(bookings));
        return bookingConfirmation;
    }

    @Override
    @Transactional(readOnly = false)
    public BookingConfirmationTo updateBooking(Long bookingId, BookingTo bookingTo) throws BookingException {
        // fetch the old data
        Booking oldBooking = checkAndFetchBooking(bookingId);
        // validate and fetch the new data
        checkIfValidBooking(bookingTo);
        Trip newTrip = checkAndFetchTrip(bookingTo.getTrip().getId());
        Traveler newTraveler;
        if (bookingTo.getTraveler().getId() == null) {
            newTraveler = travelerAssembler.createEntity(bookingTo.getTraveler());
            fakeDatabase.storeTraveler(newTraveler);
        }
        else {
            newTraveler = checkAndFetchTraveler(bookingTo.getTraveler().getId());
        }
        // test for vacancies if this person is changing trips
        if ((!newTrip.getId().equals(oldBooking.getTrip().getId())) && (newTrip.getAvailablePlaces() == 0)) {
            throw new BookingException("There is no place left in trip " + newTrip.getTripName() + ".");
        }
        // update the booking
        oldBooking.setTrip(newTrip);
        oldBooking.setTraveler(newTraveler);
        oldBooking.setPricePaid(bookingTo.getPricePaid());
        fakeDatabase.updateBooking(oldBooking);
        // send back confirmation
        BookingConfirmationTo bookingConfirmation = new BookingConfirmationTo();
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        bookings.add(oldBooking);
        bookingConfirmation.setBookings(bookingSummaryAssembler.createToList(bookings));
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
     * Check if the given booking is valid.
     *
     * @param bookingTo The given booking.
     * @throws BookingException The booking is not valid.
     */
    private void checkIfValidBooking(BookingTo bookingTo) throws BookingException {
        if (bookingTo == null) {
            throw new BookingException("No booking received.");
        }
        if (bookingTo.getId() == null) {
            throw new BookingException("No booking id present in booking.");
        }
        checkIfValidTrip(bookingTo.getTrip());
        checkIfValidTraveler(bookingTo.getTraveler());

        if (bookingTo.getPricePaid() == null) {
            throw new BookingException("No price paid present in booking.");
        }
    }

    /**
     * Check if the given booking request is valid.
     *
     * @param bookingRequestTo The given booking request.
     * @throws BookingException The booking request is not valid.
     */
    private void checkIfValidBookingRequest(BookingRequestTo bookingRequestTo) throws BookingException {
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
     * Check if the given trip object is valid.
     *
     * @param trip The given trip object.
     * @throws BookingException The trip TO is not valid.
     */
    private void checkIfValidTrip(TripTo trip) throws BookingException {
        if (trip == null) {
            throw new BookingException("Empty trip data received.");
        }
        if (trip.getId() == null) {
            throw new BookingException("No trip id present in trip.");
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
        // if an id is included, it is used to override the other traveler information
        if (traveler.getId() == null) {

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
    }

    /**
     * Fetch the booking domain object with the given id.
     *
     * @param bookingId The booking id.
     * @return The booking with the requested id.
     * @throws BookingException There is no booking with the given id.
     */
    private Booking checkAndFetchBooking(Long bookingId) throws BookingException {
        Booking booking = fakeDatabase.findBookingById(bookingId);
        if (booking == null) {
            throw new BookingException("There is no booking with id " + bookingId + ".");
        }
        return booking;
    }

    /**
     * Fetch the traveler domain object with the given id.
     *
     * @param travelerId The traveler id.
     * @return The traveler with the requested id.
     * @throws BookingException There is no traveler with the given id.
     */
    private Traveler checkAndFetchTraveler(Long travelerId) throws BookingException {
        Traveler traveler = fakeDatabase.findTravelerById(travelerId);
        if (traveler == null) {
            throw new BookingException("There is no traveler with id " + travelerId + ".");
        }
        return traveler;
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

    @Override
    public StatisticsTo getStatistics(int numberMostPopular) {
        StatisticsTo statisticsTo = new StatisticsTo();
        // Average bookings per traveler
        statisticsTo.setAvgBookingsPerTraveler(fakeDatabase.avgBookingsPerTraveler());
        // Average amount paid for booking
        statisticsTo.setAvgPaidForBooking(fakeDatabase.avgPaidForBooking());
        // Most popular trips
        List<Object[]> mostPopularTrips = fakeDatabase.mostPopularTrips(numberMostPopular);
        for (Object[] tuple : mostPopularTrips) {
            tuple[0] = tripAssembler.createTo((Trip)tuple[0]);
            if (tuple[1] == null) {
                tuple[1] = 0;
            }
        }
        statisticsTo.setMostPopularTrips(mostPopularTrips);
        return statisticsTo;
    }
}