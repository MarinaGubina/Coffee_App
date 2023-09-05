package com.github.marinagubina.coffee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "coffee_machines")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeMachine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "amount_water")
    private int remainingWater;
    @Column(name = "amount_coffee")
    private int remainingCoffee;

    @Column(name = "amount_milk")
    private int remainingMilk;

    @Column(name = "amount_sugar")
    private int remainingSugar;
}
