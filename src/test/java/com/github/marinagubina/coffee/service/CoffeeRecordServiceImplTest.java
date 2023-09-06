package com.github.marinagubina.coffee.service;

import com.github.marinagubina.coffee.constant.CoffeeType;
import com.github.marinagubina.coffee.constant.Sugar;
import com.github.marinagubina.coffee.entity.CoffeeMachine;
import com.github.marinagubina.coffee.entity.CoffeeRecord;
import com.github.marinagubina.coffee.exception.CoffeeRecordNotFoundException;
import com.github.marinagubina.coffee.exception.NotEnoughComponentsException;
import com.github.marinagubina.coffee.repository.CoffeeMachineRepository;
import com.github.marinagubina.coffee.repository.CoffeeRecordRepository;
import com.github.marinagubina.coffee.service.impl.CoffeeRecordServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CoffeeRecordServiceImplTest {

    @Autowired
    private CoffeeRecordServiceImpl recordService;

    @MockBean
    private CoffeeRecordRepository recordRepository;

    @MockBean
    private CoffeeMachineRepository machineRepository;

    @Test
    public void testCreateRecord() {
        Long machineId = 1L;
        CoffeeType coffeeType = CoffeeType.CAPPUCCINO;
        Sugar sugar = Sugar.ONE_SUGAR;
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.setRemainingWater(800);
        coffeeMachine.setRemainingCoffee(500);
        coffeeMachine.setRemainingMilk(300);
        coffeeMachine.setRemainingSugar(900);
        coffeeMachine.setEnabled(true);

        Mockito.when(machineRepository.findById(machineId)).thenReturn(Optional.of(coffeeMachine));

        CoffeeRecord result = recordService.createRecord(machineId, coffeeType, sugar);

        Mockito.verify(recordRepository, Mockito.times(1)).save(Mockito.any(CoffeeRecord.class));
        Mockito.verify(machineRepository, Mockito.times(1)).save(coffeeMachine);
    }

    @Test
    public void testCreateRecord_NotEnoughComponentsException() {
        Long machineId = 1L;
        CoffeeType coffeeType = CoffeeType.CAPPUCCINO;
        Sugar sugar = Sugar.ONE_SUGAR;
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.setRemainingWater(0);
        coffeeMachine.setRemainingCoffee(500);
        coffeeMachine.setRemainingMilk(300);
        coffeeMachine.setRemainingSugar(900);
        coffeeMachine.setEnabled(true);

        when(machineRepository.findById(machineId)).thenReturn(Optional.of(coffeeMachine));

        CoffeeRecord coffeeRecord = new CoffeeRecord();
        coffeeRecord.setDateTime(LocalDateTime.now());
        coffeeRecord.setMachine(coffeeMachine);
        coffeeRecord.setType(coffeeType);
        coffeeRecord.setSugar(sugar);
        coffeeRecord.setCompletionStatus(false);

        when(recordRepository.save(coffeeRecord)).thenReturn(coffeeRecord);

        assertThrows(NotEnoughComponentsException.class, () -> recordService.createRecord(machineId, coffeeType, sugar));
    }

    @Test
    public void testGetRecordById() {
        Long recordId = 1L;
        CoffeeRecord coffeeRecord = new CoffeeRecord();

        when(recordRepository.findById(recordId)).thenReturn(Optional.of(coffeeRecord));

        assertEquals(coffeeRecord, recordService.getRecordById(recordId));
    }

    @Test
    public void testGetRecordByIdRecordNotFound() {
        Long recordId = 2L;

        when(recordRepository.findById(recordId)).thenReturn(Optional.empty());

        assertThrows(CoffeeRecordNotFoundException.class,() -> recordService.getRecordById(recordId));
    }

    @Test
    public void testDeleteRecords() {
        Long recordId = 3L;

        recordService.deleteRecord(recordId);

        Mockito.verify(recordRepository, Mockito.times(1)).deleteById(recordId);
    }

    @Test
    public void testGetAllRecords() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.setRemainingWater(800);
        coffeeMachine.setRemainingCoffee(500);
        coffeeMachine.setRemainingMilk(300);
        coffeeMachine.setRemainingSugar(900);
        coffeeMachine.setEnabled(true);

        List<CoffeeRecord> coffeeRecords = new ArrayList<>();
        CoffeeRecord cr1 = new CoffeeRecord();
        cr1.setSugar(Sugar.ONE_SUGAR);
        cr1.setType(CoffeeType.CAPPUCCINO);
        cr1.setCompletionStatus(true);
        cr1.setDateTime(LocalDateTime.now());
        cr1.setMachine(coffeeMachine);

        CoffeeRecord cr2 = new CoffeeRecord();
        cr1.setSugar(Sugar.NO_SUGAR);
        cr1.setType(CoffeeType.AMERICANO);
        cr1.setCompletionStatus(true);
        cr1.setDateTime(LocalDateTime.now());
        cr1.setMachine(coffeeMachine);

        coffeeRecords.add(cr1);
        coffeeRecords.add(cr2);
        Pageable pageable = PageRequest.of(0, 10);

        when(recordRepository.findAll(pageable)).thenReturn(new PageImpl<>(coffeeRecords));

        Page<CoffeeRecord> result = recordService.getAllRecords(0, 10);

        assertEquals(new PageImpl<>(coffeeRecords),result);
    }
}
