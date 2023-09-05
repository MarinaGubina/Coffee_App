package com.github.marinagubina.coffee.entity;

import com.github.marinagubina.coffee.constant.CoffeeType;
import com.github.marinagubina.coffee.constant.Sugar;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "coffee_records")
public class CoffeeRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type",nullable = false)
    @Enumerated(EnumType.STRING)
    private CoffeeType type;

    @Column(name = "sugar",nullable = false)
    @Enumerated(EnumType.STRING)
    private Sugar sugar;

    @Column(name = "date_time",nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "is_done")
    private boolean isDone;

    @ManyToOne
    @JoinColumn(name = "machine_id")
    private CoffeeMachine machine;

    public Long getId() {
        return id;
    }

    public CoffeeType getType() {
        return type;
    }

    public void setType(CoffeeType type) {
        this.type = type;
    }

    public Sugar getSugar() {
        return sugar;
    }

    public void setSugar(Sugar sugar) {
        this.sugar = sugar;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public CoffeeMachine getMachine() {
        return machine;
    }

    public void setMachine(CoffeeMachine machine) {
        this.machine = machine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoffeeRecord that = (CoffeeRecord) o;
        return isDone == that.isDone && Objects.equals(id, that.id) && type == that.type && sugar == that.sugar && Objects.equals(dateTime, that.dateTime) && Objects.equals(machine, that.machine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, sugar, dateTime, isDone, machine);
    }

    @Override
    public String toString() {
        return "CoffeeRecord{" +
                "id=" + id +
                ", type=" + type +
                ", sugar=" + sugar +
                ", dateTime=" + dateTime +
                ", isDone=" + isDone +
                ", machine=" + machine +
                '}';
    }
}
