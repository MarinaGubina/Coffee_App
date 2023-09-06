package com.github.marinagubina.coffee.exception;

public class ContainersOverflowingExceptions extends RuntimeException{

    @Override
    public String getMessage() {
        return "Insufficient container capacity. The maximum volume of containers is 1000.";
    }
}
