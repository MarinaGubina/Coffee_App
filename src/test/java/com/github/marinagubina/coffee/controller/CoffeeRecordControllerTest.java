package com.github.marinagubina.coffee.controller;

import com.github.marinagubina.coffee.constant.CoffeeType;
import com.github.marinagubina.coffee.constant.Sugar;
import com.github.marinagubina.coffee.entity.CoffeeMachine;
import com.github.marinagubina.coffee.entity.CoffeeRecord;
import com.github.marinagubina.coffee.service.impl.CoffeeRecordServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CoffeeRecordController.class)
public class CoffeeRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoffeeRecordServiceImpl coffeeRecordService;

    @InjectMocks
    private CoffeeRecordController coffeeRecordController;

    @Test
    public void testCoffeeRecord() throws Exception {
        Sugar sugar = Sugar.ONE_SUGAR;
        CoffeeType type = CoffeeType.CAPPUCCINO;

        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.setId(1L);
        coffeeMachine.setRemainingWater(900);
        coffeeMachine.setRemainingCoffee(900);
        coffeeMachine.setRemainingMilk(900);
        coffeeMachine.setRemainingSugar(900);
        coffeeMachine.setEnabled(true);

        CoffeeRecord coffeeRecord = new CoffeeRecord();
        coffeeRecord.setId(2L);
        coffeeRecord.setMachine(coffeeMachine);
        coffeeRecord.setType(type);
        coffeeRecord.setSugar(sugar);
        coffeeRecord.setCompletionStatus(true);
        coffeeRecord.setDateTime(LocalDateTime.now());

        when(coffeeRecordService.createRecord(1L, type, sugar)).thenReturn(coffeeRecord);

        mockMvc.perform(post("/records")
                        .param("id-machine", String.valueOf(1L))
                        .param("coffee", type.name())
                        .param("sugar", sugar.name())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.type").value(type.name()))
                .andExpect(jsonPath("$.sugar").value(sugar.name()));

        verify(coffeeRecordService, times(1)).createRecord(1L, type, sugar);

        when(coffeeRecordService.getRecordById(2L)).thenReturn(coffeeRecord);

        mockMvc.perform(get("/records/{id}", 2L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.type").value(type.name()))
                .andExpect(jsonPath("$.sugar").value(sugar.name()))
                .andExpect(jsonPath("$.completionStatus").value(true));

        mockMvc.perform(delete("/records/{id}", 2L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(coffeeRecordService, times(1)).deleteRecord(2L);
    }
}