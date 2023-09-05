package com.github.marinagubina.coffee.entity;

import com.github.marinagubina.coffee.constant.CoffeeType;
import com.github.marinagubina.coffee.constant.Sugar;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "coffee_records")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "completion_status")
    private boolean completionStatus;

    @ManyToOne
    @JoinColumn(name = "machine_id")
    private CoffeeMachine machine;

}
