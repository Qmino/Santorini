package com.qmino.santorini.dao;

import com.qmino.santorini.domain.Booking;
import com.qmino.santorini.domain.Traveler;
import com.qmino.santorini.domain.Trip;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
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

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Booking findBookingById(Long bookingId) {
        return (Booking)getCurrentSession().get(Booking.class, bookingId);
    }

    @SuppressWarnings("unchecked")
    public List<Trip> findAllTrips() {
        return getCurrentSession().createQuery("from Trip").list();
    }

    public Trip findTripById(Long tripId) {
        return (Trip)getCurrentSession().get(Trip.class, tripId);
    }

    public Traveler findTravelerById(Long travelerId) {
        return (Traveler)getCurrentSession().get(Traveler.class, travelerId);
    }

    public void storeTraveler(Traveler traveler) {
        getCurrentSession().save(traveler);
    }

    public void storeTravelers(List<Traveler> newTravelers) {
        for (Traveler traveler : newTravelers) {
            // Those with an id are already stored.
            if (traveler.getId() == null) {
                getCurrentSession().save(traveler);
            }
        }
    }

    public void storeBookings(List<Booking> newBookings) {
        for (Booking booking : newBookings) {
            getCurrentSession().save(booking);
        }
    }

    public void updateBooking(Booking booking) {
        getCurrentSession().update(booking);
    }

    public double avgBookingsPerTraveler() {
        String sql = "select avg(c)" +
                     " from" +
                     "   (select travelers.id, count(bookings.id) c" +
                     "      from travelers left join bookings on travelers.id=bookings.traveler_id" +
                     "  group by travelers.id) t";

        Object queryResult = getCurrentSession().createSQLQuery(sql).uniqueResult();
        if (queryResult == null) {
            return 0;
        }
        else {
            return ((BigDecimal)queryResult).doubleValue();
        }
    }

    public double avgPaidForBooking() {
        String hql = "select avg(b.pricePaid)" +
                     "  from Booking b";

        Object queryResult = getCurrentSession().createQuery(hql).uniqueResult();
        if (queryResult == null) {
            return 0;
        }
        else {
            return ((Double)queryResult).doubleValue();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> mostPopularTrips(int numberMostPopular) {
        String hql = "   select t, avg(booking.pricePaid)" +
                     "     from Trip t left join t.bookings as booking" +
                     " group by t.id" +
                     " order by count(booking.id) desc";

        Query query = getCurrentSession().createQuery(hql);
        query.setMaxResults(numberMostPopular);
        return query.list();
    }
}