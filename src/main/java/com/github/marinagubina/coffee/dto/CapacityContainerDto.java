package com.github.marinagubina.coffee.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

public class CapacityContainerDto {
    @Min(-1000)
    @Max(1000)
    private int remainingWater;
    @Min(-1000)
    @Max(1000)
    private int remainingCoffee;
    @Min(-1000)
    @Max(1000)
    private int remainingMilk;
    @Min(-1000)
    @Max(1000)
    private int remainingSugar;

    public int getRemainingWater() {
        return remainingWater;
    }

    public int getRemainingCoffee() {
        return remainingCoffee;
    }

    public int getRemainingMilk() {
        return remainingMilk;
    }

    public int getRemainingSugar() {
        return remainingSugar;
    }
}
