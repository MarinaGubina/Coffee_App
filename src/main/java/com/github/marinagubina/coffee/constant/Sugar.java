package com.github.marinagubina.coffee.constant;

import lombok.Getter;

@Getter
public enum Sugar {
    NO_SUGAR(0),
    ONE_SUGAR(5),
    TWO_SUGARS(10),
    THREE_SUGARS(15);

    private final int sugarAmount;

    Sugar(int sugarAmount) {
        this.sugarAmount = sugarAmount;
    }

}
