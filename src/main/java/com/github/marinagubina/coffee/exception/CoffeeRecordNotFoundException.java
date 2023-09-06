package com.github.marinagubina.coffee.exception;

public class CoffeeRecordNotFoundException extends RuntimeException{
    private final long id;

    public CoffeeRecordNotFoundException(Long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Not found coffee record with id : " + id;
    }
}
