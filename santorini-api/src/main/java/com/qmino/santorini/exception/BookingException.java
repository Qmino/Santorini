package com.qmino.santorini.exception;

/**
 * A booking exception.
 * <p/>
 * <p>
 * <i>Creation-Date</i>: 19/06/13<br>
 * <i>Creation-Time</i>: 16:28<br>
 * </p>
 *
 * @author Peter Rigole
 * @since SDK1.6
 */
public class BookingException extends RuntimeException {

    public BookingException() {
    }

    public BookingException(String message) {
        super(message);
    }

    public BookingException(String message, Throwable cause) {
        super(message, cause);
    }

}