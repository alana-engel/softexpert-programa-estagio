package com.softexpert.library.repository;

public class CannotLocateRepositoryException extends RuntimeException {

    private static final long serialVersionUID = 7168072726042505362L;
    
    public CannotLocateRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

}
