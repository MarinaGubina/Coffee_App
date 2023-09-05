package com.github.marinagubina.coffee.constant;

import lombok.Getter;

@Getter
public enum CoffeeType {

    ESPRESSO(7,30,0),
    AMERICANO(7,100,0),
    CAPPUCCINO(7,60,20),
    LATTE(7,60,30),
    HOT_MILK(0,0,100);

    private final int coffeeAmount;
    private final int waterAmount;
    private final int milkAmount;

    CoffeeType(int coffeeAmount, int waterAmount, int milkAmount) {
        this.coffeeAmount = coffeeAmount;
        this.waterAmount = waterAmount;
        this.milkAmount = milkAmount;
    }

}
