package com.github.marinagubina.coffee.exception;

public class CoffeeMachineConditionException extends RuntimeException{

    @Override
    public String getMessage() {
        return "Turn on the coffee machine";
    }
}
