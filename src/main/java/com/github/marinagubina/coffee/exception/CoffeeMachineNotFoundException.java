package com.github.marinagubina.coffee.exception;

public class CoffeeMachineNotFoundException extends RuntimeException{

    private final long id;

    public CoffeeMachineNotFoundException(long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Not found coffee machine with id : " + id;
    }
}
