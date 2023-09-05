package com.github.marinagubina.coffee.service;

import com.github.marinagubina.coffee.dto.CapacityContainerDto;
import com.github.marinagubina.coffee.entity.CoffeeMachine;
import com.github.marinagubina.coffee.exception.CoffeeMachineNotFoundException;
import com.github.marinagubina.coffee.exception.ContainersOverflowingExceptions;
import com.github.marinagubina.coffee.repository.CoffeeMachineRepository;
import com.github.marinagubina.coffee.service.impl.CoffeeMachineServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CoffeeMachineServiceImplTest {
    @Autowired
    private CoffeeMachineServiceImpl coffeeMachineService;

    @MockBean
    private CoffeeMachineRepository machineRepository;

    /*
    @Test
    public void testCreateMachine() {
        CapacityContainerDto containerDto = new CapacityContainerDto(1000, 900, 800, 700);
        CoffeeMachine expected = new CoffeeMachine();
        expected.setRemainingWater(1000);
        expected.setRemainingCoffee(900);
        expected.setRemainingMilk(800);
        expected.setRemainingSugar(700);
        expected.setEnabled(true);
        Mockito.when(machineRepository.save(Mockito.any())).thenReturn(expected);

        CoffeeMachine actual = coffeeMachineService.createMachine(containerDto);

        assertEquals(expected, actual);
    }
*/
    @Test
    public void testCreateMachineWithOverflow() {
        CapacityContainerDto containerDto = new CapacityContainerDto(2000, 2000, 2000, 2000);

        assertThrows(ContainersOverflowingExceptions.class, () -> coffeeMachineService.createMachine(containerDto));
    }
/*
    @Test
    public void testTurnOn() {
        Long machineId = 1L;
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        Mockito.when(machineRepository.findById(machineId)).thenReturn(Optional.of(coffeeMachine));
        Mockito.when(machineRepository.save(Mockito.any())).thenReturn(coffeeMachine);

        coffeeMachineService.turnOn(machineId);

        assertTrue(coffeeMachine.isEnabled());
    }

    @Test
    public void testTurnOnMachineNotFound() {
        Long machineId = 2L;

        Mockito.when(machineRepository.findById(machineId)).thenReturn(Optional.empty());

        assertThrows(CoffeeMachineNotFoundException.class, () -> coffeeMachineService.turnOn(machineId));
    }

    @Test
    public void testTurnOff() {
        Long machineId = 1L;
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        Mockito.when(machineRepository.findById(machineId)).thenReturn(Optional.of(coffeeMachine));
        Mockito.when(machineRepository.save(Mockito.any())).thenReturn(coffeeMachine);

        coffeeMachineService.turnOff(machineId);

        assertFalse(coffeeMachine.isEnabled());
    }

    @Test
    public void testTurnOffMachineNotFound() {
        Long machineId = 2L;

        Mockito.when(machineRepository.findById(machineId)).thenReturn(Optional.empty());

        assertThrows(CoffeeMachineNotFoundException.class, () -> coffeeMachineService.turnOff(machineId));
    }

    @Test
    public void testGetMachineById() {
        Long machineId = 1L;
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        Mockito.when(machineRepository.findById(machineId)).thenReturn(Optional.of(coffeeMachine));

        assertEquals(coffeeMachine, coffeeMachineService.getMachineById(machineId));
    }

    @Test
    public void testGetMachineByIdMachineNotFound() {
        Long machineId = 2L;

        Mockito.when(machineRepository.findById(machineId)).thenReturn(Optional.empty());

        assertThrows(CoffeeMachineNotFoundException.class, () -> coffeeMachineService.getMachineById(machineId));
    }

    @Test
    public void testUpdateMachine() {
        Long machineId = 1L;
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.setRemainingWater(900);
        coffeeMachine.setRemainingCoffee(800);
        coffeeMachine.setRemainingMilk(700);
        coffeeMachine.setRemainingSugar(600);
        coffeeMachine.setEnabled(true);

        CapacityContainerDto containerDto = new CapacityContainerDto(100, 200, 300, 400);

        Mockito.when(machineRepository.findById(machineId)).thenReturn(Optional.of(coffeeMachine));
        Mockito.when(machineRepository.save(Mockito.any())).thenReturn(coffeeMachine);

        CoffeeMachine expected = new CoffeeMachine();
        expected.setRemainingWater(1000);
        expected.setRemainingCoffee(1000);
        expected.setRemainingMilk(1000);
        expected.setRemainingSugar(1000);
        expected.setEnabled(true);

        assertEquals(expected, coffeeMachineService.updateMachine(machineId, containerDto));
    }

    @Test
    public void testUpdateMachineWithOverflow() {
        Long machineId = 1L;
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.setRemainingWater(900);
        coffeeMachine.setRemainingCoffee(800);
        coffeeMachine.setRemainingMilk(700);
        coffeeMachine.setRemainingSugar(600);
        coffeeMachine.setEnabled(true);

        CapacityContainerDto containerDto = new CapacityContainerDto(1000, 200, 300, 400);

        Mockito.when(machineRepository.findById(machineId)).thenReturn(Optional.of(coffeeMachine));
        Mockito.when(machineRepository.save(Mockito.any())).thenReturn(coffeeMachine);
        assertThrows(ContainersOverflowingExceptions.class, () -> coffeeMachineService.updateMachine(machineId, containerDto));
    }

    @Test
    public void testDeleteMachine() {
        Long machineId = 3L;

        coffeeMachineService.deleteMachine(machineId);

        Mockito.verify(machineRepository, Mockito.times(1)).deleteById(machineId);
    }

    @Test
    public void testGetAllMachines() {
        List<CoffeeMachine> coffeeMachines = new ArrayList<>();
        CoffeeMachine cm1 = new CoffeeMachine();
        cm1.setRemainingWater(1000);
        cm1.setRemainingCoffee(900);
        cm1.setRemainingMilk(800);
        cm1.setRemainingSugar(700);
        cm1.setEnabled(true);
        CoffeeMachine cm2 = new CoffeeMachine();
        cm2.setRemainingWater(800);
        cm2.setRemainingCoffee(500);
        cm2.setRemainingMilk(300);
        cm2.setRemainingSugar(900);
        cm2.setEnabled(true);
        coffeeMachines.add(cm1);
        coffeeMachines.add(cm2);
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(machineRepository.findAll(pageable)).thenReturn(new PageImpl<>(coffeeMachines));

        Page<CoffeeMachine> result = coffeeMachineService.getAllMachines(0, 10);

        assertEquals(new PageImpl<>(coffeeMachines), result);
    }*/
}