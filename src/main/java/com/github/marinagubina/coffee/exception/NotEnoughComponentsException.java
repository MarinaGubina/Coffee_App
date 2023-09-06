package com.github.marinagubina.coffee.exception;

public class NotEnoughComponentsException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Not enough components for making coffee";
    }
}
