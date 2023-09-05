package com.github.marinagubina.coffee.service;

import com.github.marinagubina.coffee.dto.CapacityContainerDto;
import com.github.marinagubina.coffee.entity.CoffeeMachine;
import jakarta.persistence.Column;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

public interface CoffeeMachineService {

    CoffeeMachine createMachine(CapacityContainerDto containerDto);
    void turnOn(Long id);
    void turnOff(Long id);
    CoffeeMachine getMachineById(Long id);
    CoffeeMachine updateMachine(Long id, CapacityContainerDto dto);
    void deleteMachine(Long id);
    Page<CoffeeMachine> getAllMachines(int page, int pageSize);
}
