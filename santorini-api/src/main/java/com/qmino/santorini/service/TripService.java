package com.qmino.santorini.service;

import com.qmino.santorini.exception.BookingException;
import com.qmino.santorini.to.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Trip service interfaces.
 * <p/>
 * <p>
 * <i>Creation-Date</i>: 19/06/13<br>
 * <i>Creation-Time</i>: 16:07<br>
 * </p>
 *
 * @author Peter Rigole
 * @since SDK1.6
 */
@Path("/rest")
public interface TripService {

    /**
     * Retrieve booking information about a given booking.
     *
     * @param bookingId The given booking ID.
     * @return Booking information about the booking with the given ID.
     * @throws NoSuchElementException There is no booking matching the given ID.
     * @summary Retrieve booking information about a given booking.
     */
    @GET
    @Path("bookings/{bookingId}")
    @Produces(MediaType.APPLICATION_JSON)
    BookingTo getBookingInfo(@PathParam("bookingId") Long bookingId) throws NoSuchElementException;

    /**
     * Retrieve all available trips.
     *
     * @return A list containing the name data of all available trips.
     * @summary Retrieve all available trips.
     */
    @GET
    @Path("trips/")
    @Produces(MediaType.APPLICATION_JSON)
    List<TripNameTo> getAllAvailableTrips();

    /**
     * Retrieve detailed trip information about a given trip.
     *
     * @param tripId The given trip ID.
     * @return Detailed trip information about the trip with the given ID.
     * @throws NoSuchElementException There is no trip matching the given ID.
     * @summary Retrieve detailed trip information about a given trip.
     */
    @GET
    @Path("trips/{tripId}")
    @Produces(MediaType.APPLICATION_JSON)
    TripTo getTripInfo(@PathParam("tripId") Long tripId) throws NoSuchElementException;

    /**
     * Retrieve traveler information about a given traveler.
     *
     * @param travelerId The given traveler ID.
     * @return Traveler information about the traveler with the given ID.
     * @throws NoSuchElementException There is no traveler matching the given ID.
     * @summary Retrieve traveler information about a given traveler.
     */
    @GET
    @Path("travelers/{travelerId}")
    @Produces(MediaType.APPLICATION_JSON)
    TravelerSummaryTo getTravelerInfo(@PathParam("travelerId") Long travelerId) throws NoSuchElementException;

    /**
     * Retrieve the trips of a given traveler.
     *
     * @param travelerId The given traveler ID.
     * @return The trips of the traveler with the given ID.
     * @throws NoSuchElementException There is no traveler matching the given ID.
     * @summary Retrieve the trips of a given traveler.
     */
    @GET
    @Path("travelers/trips/{travelerId}")
    @Produces(MediaType.APPLICATION_JSON)
    List<TripSummaryTo> getTravelerTrips(@PathParam("travelerId") Long travelerId) throws NoSuchElementException;

    /**
     * Book a trip.
     *
     * @param bookingRequestTo The booking information.
     * @return The booking confirmation data.
     * @throws BookingException The requested booking could not be established.
     * @summary Book a trip.
     */
    @POST
    @Path("/book")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    BookingConfirmationTo bookTrip(BookingRequestTo bookingRequestTo) throws BookingException;

    /**
     * Update a booking.
     *
     * @param bookingId The given booking ID.
     *        bookingTo The booking information.
     * @return The booking confirmation data.
     * @throws BookingException The requested booking could not be updated.
     * @summary Update a booking.
     */
    @PUT
    @Path("/book/{bookingId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    BookingConfirmationTo updateBooking(@PathParam("bookingId") Long bookingId, BookingTo bookingTo) throws BookingException;

    /**
     * Retrieve statistics.
     *
     * @param numberMostPopular The how many most popular trips?
     * @return Statistics information.
     * @summary Retrieve statistics information.
     */
    @GET
    @Path("/statistics/{numberMostPopular}")
    @Produces(MediaType.APPLICATION_JSON)
    StatisticsTo getStatistics(@PathParam("numberMostPopular") int numberMostPopular);
}