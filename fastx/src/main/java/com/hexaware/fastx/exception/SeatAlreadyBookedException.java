// SeatAlreadyBookedException.java
package com.hexaware.fastx.exception;

public class SeatAlreadyBookedException extends RuntimeException {
    public SeatAlreadyBookedException(String message) {
        super(message);
    }
}
