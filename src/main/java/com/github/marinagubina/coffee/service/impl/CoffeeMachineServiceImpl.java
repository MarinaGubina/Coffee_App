package com.github.marinagubina.coffee.service.impl;

import com.github.marinagubina.coffee.dto.CapacityContainerDto;
import com.github.marinagubina.coffee.entity.CoffeeMachine;
import com.github.marinagubina.coffee.exception.CoffeeMachineNotFoundException;
import com.github.marinagubina.coffee.exception.ContainersOverflowingExceptions;
import com.github.marinagubina.coffee.repository.CoffeeMachineRepository;
import com.github.marinagubina.coffee.service.CoffeeMachineService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CoffeeMachineServiceImpl implements CoffeeMachineService {

    private final CoffeeMachineRepository machineRepository;

    public CoffeeMachineServiceImpl(CoffeeMachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    @Override
    public CoffeeMachine createMachine(CapacityContainerDto containerDto) {
        if(containerDto.getRemainingWater() > 1000 ||
            containerDto.getRemainingCoffee() > 1000 ||
            containerDto.getRemainingMilk() > 1000 ||
            containerDto.getRemainingSugar() > 1000){
            throw new ContainersOverflowingExceptions();
        }
        else{
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.setRemainingWater(containerDto.getRemainingWater());
        coffeeMachine.setRemainingCoffee(containerDto.getRemainingCoffee());
        coffeeMachine.setRemainingMilk(containerDto.getRemainingMilk());
        coffeeMachine.setRemainingSugar(containerDto.getRemainingSugar());
        coffeeMachine.setOn(true);
        return machineRepository.save(coffeeMachine);
        }
    }

    @Override
    public void turnOn(Long id) {
        CoffeeMachine machine = machineRepository.findById(id).orElseThrow(() -> new CoffeeMachineNotFoundException(id));
        machine.setOn(true);
        machineRepository.save(machine);
    }

    @Override
    public void turnOff(Long id) {
        CoffeeMachine machine = machineRepository.findById(id).orElseThrow(() -> new CoffeeMachineNotFoundException(id));
        machine.setOn(false);
        machineRepository.save(machine);
    }

    @Override
    public CoffeeMachine getMachineById(Long id) {
        return machineRepository.findById(id).orElseThrow(() -> new CoffeeMachineNotFoundException(id));
    }

    @Override
    public CoffeeMachine updateMachine(Long id, CapacityContainerDto dto) {
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
        return machineRepository.save(machine);
        }
    }

    @Override
    public void deleteMachine(Long id) {
        machineRepository.deleteById(id);
    }

    @Override
    public Page<CoffeeMachine> getAllMachines(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return machineRepository.findAllMachines(pageable);
    }
}
