package ru.ktelabs.exceptions;

public class ScheduleNotCreatedException extends RuntimeException {
    public ScheduleNotCreatedException(String message) {
        super(message);
    }
}
