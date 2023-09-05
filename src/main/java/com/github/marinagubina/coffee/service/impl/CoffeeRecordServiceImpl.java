package com.github.marinagubina.coffee.service.impl;

import com.github.marinagubina.coffee.constant.CoffeeType;
import com.github.marinagubina.coffee.constant.Sugar;
import com.github.marinagubina.coffee.entity.CoffeeMachine;
import com.github.marinagubina.coffee.entity.CoffeeRecord;
import com.github.marinagubina.coffee.exception.CoffeeMachineConditionException;
import com.github.marinagubina.coffee.exception.CoffeeMachineNotFoundException;
import com.github.marinagubina.coffee.exception.CoffeeRecordNotFoundException;
import com.github.marinagubina.coffee.exception.NotEnoughComponentsException;
import com.github.marinagubina.coffee.repository.CoffeeMachineRepository;
import com.github.marinagubina.coffee.repository.CoffeeRecordRepository;
import com.github.marinagubina.coffee.service.CoffeeRecordService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CoffeeRecordServiceImpl implements CoffeeRecordService {

    private final CoffeeRecordRepository recordRepository;
    private final CoffeeMachineRepository machineRepository;

    public CoffeeRecordServiceImpl(CoffeeRecordRepository recordRepository, CoffeeMachineRepository machineRepository) {
        this.recordRepository = recordRepository;
        this.machineRepository = machineRepository;
    }

    @Override
    public CoffeeRecord createRecord(Long idMachine, CoffeeType type, Sugar sugar) {
        CoffeeMachine coffeeMachine = machineRepository.findById(idMachine)
                .orElseThrow(()-> new CoffeeMachineNotFoundException(idMachine));
        checkConditionalCoffeeMachine(coffeeMachine);
        int amountWater = type.getWaterAmount();
        int amountCoffee = type.getCoffeeAmount();
        int amountMilk = type.getMilkAmount();
        int amountSugar = sugar.getSugarAmount();
        CoffeeRecord coffee = new CoffeeRecord();
        coffee.setDateTime(LocalDateTime.now());
        coffee.setType(type);
        coffee.setSugar(sugar);
        if(checkContainer(coffeeMachine,amountWater,amountCoffee,amountMilk,amountSugar)){
            coffee.setCompletionStatus(true);
            coffeeMachine.setRemainingWater(coffeeMachine.getRemainingWater() - amountWater);
            coffeeMachine.setRemainingCoffee(coffeeMachine.getRemainingCoffee() - amountCoffee);
            coffeeMachine.setRemainingMilk(coffeeMachine.getRemainingMilk() - amountMilk);
            coffeeMachine.setRemainingSugar(coffeeMachine.getRemainingSugar() - amountSugar);
            machineRepository.save(coffeeMachine);
            coffee.setMachine(coffeeMachine);
            return recordRepository.save(coffee);
        }
        else {
            coffee.setCompletionStatus(false);
            coffee.setMachine(coffeeMachine);
            recordRepository.save(coffee);
            throw new NotEnoughComponentsException();
        }
    }

    private void checkConditionalCoffeeMachine(CoffeeMachine coffeeMachine){
        if (!coffeeMachine.isEnabled()) {throw new CoffeeMachineConditionException();}
    }

    private boolean checkContainer(CoffeeMachine coffeeMachine,int amountWater,int amountCoffee,
                                   int amountMilk, int amountSugar){
        return coffeeMachine.getRemainingWater() >= amountWater &&
                coffeeMachine.getRemainingCoffee() >= amountCoffee &&
                coffeeMachine.getRemainingMilk() >= amountMilk &&
                coffeeMachine.getRemainingSugar() >= amountSugar;
    }

    @Override
    public CoffeeRecord getRecordById(Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new CoffeeRecordNotFoundException(id));
    }

    @Override
    public void deleteRecord(Long id) {
        recordRepository.deleteById(id);
    }

    @Override
    public Page<CoffeeRecord> getAllRecords(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return recordRepository.findAll(pageable);
    }
}
