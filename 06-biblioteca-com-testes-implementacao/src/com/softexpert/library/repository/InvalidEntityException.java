package com.softexpert.library.repository;

public class InvalidEntityException extends RuntimeException {

    private static final long serialVersionUID = 5351023194712378129L;

    public InvalidEntityException(String message) {
        super(message);
    }

}
