package com.github.marinagubina.coffee.controller;

import com.github.marinagubina.coffee.dto.CapacityContainerDto;
import com.github.marinagubina.coffee.entity.CoffeeMachine;
import com.github.marinagubina.coffee.service.impl.CoffeeMachineServiceImpl;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CoffeeMachineController.class)
public class CoffeeMachineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoffeeMachineServiceImpl coffeeMachineService;

    @InjectMocks
    private CoffeeMachineController coffeeMachineController;

    @Test
    public void testCoffeeMachine() throws Exception {
        int water = 900;
        int coffee = 900;
        int milk = 900;
        int sugar = 900;

        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.setId(1L);
        coffeeMachine.setRemainingWater(water);
        coffeeMachine.setRemainingCoffee(coffee);
        coffeeMachine.setRemainingMilk(milk);
        coffeeMachine.setRemainingSugar(sugar);
        coffeeMachine.setEnabled(true);

        CapacityContainerDto dto = new CapacityContainerDto();
        JSONObject dtoJson = new JSONObject();
        dtoJson.put("remainingWater",water);
        dtoJson.put("remainingCoffee",coffee);
        dtoJson.put("remainingMilk",milk);
        dtoJson.put("remainingSugar",sugar);

        when(coffeeMachineService.createMachine(any(CapacityContainerDto.class))).thenReturn(coffeeMachine);

        mockMvc.perform(post("/coffee-machines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(dtoJson)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.remainingWater").value(water))
                .andExpect(jsonPath("$.remainingCoffee").value(coffee))
                .andExpect(jsonPath("$.remainingMilk").value(milk))
                .andExpect(jsonPath("$.remainingSugar").value(sugar));

        verify(coffeeMachineService, times(1)).createMachine(any(CapacityContainerDto.class));

        when(coffeeMachineService.getMachineById(1L)).thenReturn(coffeeMachine);

        mockMvc.perform(get("/coffee-machines/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.remainingWater").value(water))
                .andExpect(jsonPath("$.remainingCoffee").value(coffee))
                .andExpect(jsonPath("$.remainingMilk").value(milk))
                .andExpect(jsonPath("$.remainingSugar").value(sugar))
                .andExpect(jsonPath("$.enabled").value(true));

        verify(coffeeMachineService, times(1)).getMachineById(1L);

        mockMvc.perform(patch("/coffee-machines/turn-on/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(coffeeMachineService, times(1)).turnOn(1L);

        mockMvc.perform(patch("/coffee-machines/turn-off/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(coffeeMachineService, times(1)).turnOff(1L);

        mockMvc.perform(delete("/coffee-machines/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(coffeeMachineService, times(1)).deleteMachine(1L);
    }
}
