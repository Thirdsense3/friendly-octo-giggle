package com.aces.bird.ranking.service.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super("user id " + message + " is not found.");
    }
}
