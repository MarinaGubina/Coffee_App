package com.github.marinagubina.coffee.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "coffee_machines")
public class CoffeeMachine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_on")
    private boolean isOn;

    @Column(name = "amount_water")
    private int remainingWater;
    @Column(name = "amount_coffee")
    private int remainingCoffee;

    @Column(name = "amount_milk")
    private int remainingMilk;

    @Column(name = "amount_sugar")
    private int remainingSugar;

    public Long getId() {
        return id;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public int getRemainingWater() {
        return remainingWater;
    }

    public void setRemainingWater(int remainingWater) {
        this.remainingWater = remainingWater;
    }

    public int getRemainingCoffee() {
        return remainingCoffee;
    }

    public void setRemainingCoffee(int remainingCoffee) {
        this.remainingCoffee = remainingCoffee;
    }

    public int getRemainingMilk() {
        return remainingMilk;
    }

    public void setRemainingMilk(int remainingMilk) {
        this.remainingMilk = remainingMilk;
    }

    public int getRemainingSugar() {
        return remainingSugar;
    }

    public void setRemainingSugar(int remainingSugar) {
        this.remainingSugar = remainingSugar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoffeeMachine that = (CoffeeMachine) o;
        return isOn == that.isOn && remainingWater == that.remainingWater && remainingCoffee == that.remainingCoffee && remainingMilk == that.remainingMilk && remainingSugar == that.remainingSugar && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isOn, remainingWater, remainingCoffee, remainingMilk, remainingSugar);
    }

    @Override
    public String toString() {
        return "CoffeeMachine{" +
                "id=" + id +
                ", isOn=" + isOn +
                ", remainingWater=" + remainingWater +
                ", remainingCoffee=" + remainingCoffee +
                ", remainingMilk=" + remainingMilk +
                ", remainingSugar=" + remainingSugar +
                '}';
    }
}
