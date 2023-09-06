package com.github.marinagubina.coffee.dto;

public class CapacityContainerDto {
    private int remainingWater;

    private int remainingCoffee;

    private int remainingMilk;

    private int remainingSugar;

    public CapacityContainerDto() {
    }

    public CapacityContainerDto(int remainingWater, int remainingCoffee, int remainingMilk, int remainingSugar) {
        this.remainingWater = remainingWater;
        this.remainingCoffee = remainingCoffee;
        this.remainingMilk = remainingMilk;
        this.remainingSugar = remainingSugar;
    }

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
