package com.qmino.santorini.domain;

import com.qmino.santorini.common.domain.IdentifiedObject;

import java.util.ArrayList;
import java.util.List;

/**
 * No comment provided yet for this class.
 * <p/>
 * <p>
 * <i>Creation-Date</i>: 19/06/13<br>
 * <i>Creation-Time</i>: 16:58<br>
 * </p>
 *
 * @author Peter Rigole
 * @since SDK1.6
 */
public class Traveler extends IdentifiedObject {

    private String firstName;
    private String lastName;
    private String passportNumber;
    private String street;
    private String houseNumber;
    private String zip;
    private String city;
    private String country;
    private String phoneNumber;
    private String emailAddress;
    private List<Booking> previousTrips;
    private List<Booking> bookedTrips;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<Booking> getPreviousTrips() {
        if (previousTrips == null) {
            previousTrips = new ArrayList<Booking>();
        }
        return previousTrips;
    }

    public void setPreviousTrips(List<Booking> previousTrips) {
        this.previousTrips = previousTrips;
    }

    public List<Booking> getBookedTrips() {
        if (bookedTrips == null) {
            bookedTrips = new ArrayList<Booking>();
        }
        return bookedTrips;
    }

    public void setBookedTrips(List<Booking> bookedTrips) {
        this.bookedTrips = bookedTrips;
    }

    public void addBooking(Booking booking) {
        getBookedTrips().add(booking);
    }

}