package com.github.marinagubina.coffee.service.impl;

import com.github.marinagubina.coffee.dto.CapacityContainerDto;
import com.github.marinagubina.coffee.dto.CoffeeMachineDto;
import com.github.marinagubina.coffee.entity.CoffeeMachine;
import com.github.marinagubina.coffee.exception.CoffeeMachineNotFoundException;
import com.github.marinagubina.coffee.exception.ContainersOverflowingExceptions;
import com.github.marinagubina.coffee.mapper.CoffeeMachineMapper;
import com.github.marinagubina.coffee.repository.CoffeeMachineRepository;
import com.github.marinagubina.coffee.service.CoffeeMachineService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CoffeeMachineServiceImpl implements CoffeeMachineService {

    private final CoffeeMachineRepository machineRepository;
    private final CoffeeMachineMapper mapper;

    @Override
    public CoffeeMachineDto createMachine(CapacityContainerDto containerDto) {
        if(containerDto.getRemainingWater() > 1000 ||
            containerDto.getRemainingCoffee() > 1000 ||
            containerDto.getRemainingMilk() > 1000 ||
            containerDto.getRemainingSugar() > 1000){
            throw new ContainersOverflowingExceptions();
        }
        else{
        CoffeeMachine coffeeMachine = mapper.toEntity(containerDto);
        coffeeMachine.setEnabled(true);
        machineRepository.save(coffeeMachine);
        return mapper.toDto(coffeeMachine);
        }
    }

    @Override
    public void turnOn(Long id) {
        CoffeeMachine machine = machineRepository.findById(id).orElseThrow(() -> new CoffeeMachineNotFoundException(id));
        machine.setEnabled(true);
        machineRepository.save(machine);
    }

    @Override
    public void turnOff(Long id) {
        CoffeeMachine machine = machineRepository.findById(id).orElseThrow(() -> new CoffeeMachineNotFoundException(id));
        machine.setEnabled(false);
        machineRepository.save(machine);
    }

    @Override
    public CoffeeMachineDto getMachineById(Long id) {
        CoffeeMachine coffeeMachine = machineRepository.findById(id)
                .orElseThrow(() -> new CoffeeMachineNotFoundException(id));
        return mapper.toDto(coffeeMachine);
    }

    @Override
    public CoffeeMachineDto updateMachine(Long id, CapacityContainerDto dto) {
        CoffeeMachine machine = machineRepository.findById(id).orElseThrow(() -> new CoffeeMachineNotFoundException(id));
        int amountWater = machine.getRemainingWater()+ dto.getRemainingWater();
        int amountCoffee = machine.getRemainingCoffee() + dto.getRemainingCoffee();
        int amountMilk = machine.getRemainingMilk() + dto.getRemainingMilk();
        int amountSugar = machine.getRemainingSugar() + dto.getRemainingSugar();
        if(amountWater > 1000 || amountCoffee > 1000 || amountMilk > 1000 || amountSugar > 1000){
            throw new ContainersOverflowingExceptions();
        } else{
        machine.setRemainingWater(amountWater);
        machine.setRemainingCoffee(amountCoffee);
        machine.setRemainingMilk(amountMilk);
        machine.setRemainingSugar(amountSugar);
        machineRepository.save(machine);
        }
        return mapper.toDto(machine);
    }

    @Override
    public void deleteMachine(Long id) {
        machineRepository.deleteById(id);
    }

    @Override
    public Page<CoffeeMachineDto> getAllMachines(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        Page<CoffeeMachine> coffeeMachinePage = machineRepository.findAll(pageable);
        return mapper.toPage(coffeeMachinePage);
    }
}
