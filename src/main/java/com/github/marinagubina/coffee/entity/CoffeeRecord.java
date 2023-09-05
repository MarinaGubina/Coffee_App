package com.github.marinagubina.coffee.entity;

import com.github.marinagubina.coffee.constant.CoffeeType;
import com.github.marinagubina.coffee.constant.Sugar;
import jakarta.persistence.*;

import java.time.LocalDateTime;

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
}
