package com.github.marinagubina.coffee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CapacityContainerDto {

    private int remainingWater;

    private int remainingCoffee;

    private int remainingMilk;

    private int remainingSugar;
}
