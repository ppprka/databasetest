package ru.danko.testtask.exception;

public class DaoServiceException extends Exception {

    public DaoServiceException() {
        super();
    }

    public DaoServiceException(String message) {
        super(message);
    }

    public DaoServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public DaoServiceException(Throwable throwable) {
        super(throwable);
    }
}