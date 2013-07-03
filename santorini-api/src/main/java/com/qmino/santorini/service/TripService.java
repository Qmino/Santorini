package com.qmino.santorini.service;

import com.qmino.santorini.exception.BookingException;
import com.qmino.santorini.to.BookingConfirmationTo;
import com.qmino.santorini.to.BookingRequestTo;
import com.qmino.santorini.to.TripSummaryTo;
import com.qmino.santorini.to.TripTo;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
@Path("/rest/trips")
public interface TripService {

    /**
     * Retrieve all available trips.
     *
     * @return A list containing the summary data of all available trips.
     * @summary Retrieve all available trips.
     */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    List<TripSummaryTo> getAllAvailableTrips();

    /**
     * Retrieve detailed trip information about a given trip.
     *
     * @param tripId The given trip ID.
     * @return Detailed trip information about the trip with the given ID.
     * @throws NoSuchElementException There is no trip matching the given ID.
     * @summary Retrieve detailed trip information about a given trip.
     */
    @GET
    @Path("/{tripId}")
    @Produces(MediaType.APPLICATION_JSON)
    TripTo getTripInfo(@PathParam("tripId") Long tripId) throws NoSuchElementException;

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

}