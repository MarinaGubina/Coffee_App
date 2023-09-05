package com.github.marinagubina.coffee.controller;

import com.github.marinagubina.coffee.dto.CapacityContainerDto;
import com.github.marinagubina.coffee.dto.CoffeeMachineDto;
import com.github.marinagubina.coffee.entity.CoffeeMachine;
import com.github.marinagubina.coffee.service.CoffeeMachineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/coffee-machines")
@Tag(name = "Coffee Machine Operation")
public class CoffeeMachineController {

    private final CoffeeMachineService machineService;

    public CoffeeMachineController(CoffeeMachineService machineService) {
        this.machineService = machineService;
    }

    @Operation(
            summary = "Create new coffee machine",
            description = "Create coffee machine",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CoffeeMachineDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid dto"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PostMapping
    public CoffeeMachineDto createCoffeeMachine(@RequestBody CapacityContainerDto containerDto){
        return machineService.createMachine(containerDto);
    }

    @Operation(
            summary = "Turn on coffee machine by ID",
            description = "Coffee machine must exist",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "400", description = "Invalid ID"),
                    @ApiResponse(responseCode = "404", description = "coffee machine not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PatchMapping("/turn-on/{id}")
    public void turnOn(@PathVariable Long id){
        machineService.turnOn(id);
    }

    @Operation(
            summary = "Turn off coffee machine by ID",
            description = "Coffee machine must exist",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "400", description = "Invalid ID"),
                    @ApiResponse(responseCode = "404", description = "coffee machine not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PatchMapping("/turn-off/{id}")
    public void turnOff(@PathVariable Long id){
        machineService.turnOff(id);
    }

    @Operation(
            summary = "Updating the filling of coffee machine containers by ID",
            description = "Coffee machine must exist",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CoffeeMachineDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid ID"),
                    @ApiResponse(responseCode = "404", description = "coffee machine not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PatchMapping("/fill-machine/{id}")
    public CoffeeMachineDto fillContainersMachine(@PathVariable Long id,
                                               @RequestBody CapacityContainerDto dto){
        return machineService.updateMachine(id,dto);
    }

    @Operation(
            summary = "Get coffee machine by ID",
            description = "Coffee machine must exist",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CoffeeMachineDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid ID"),
                    @ApiResponse(responseCode = "404", description = "coffee machine not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/{id}")
    public CoffeeMachineDto getMachineById(@PathVariable Long id){
        return machineService.getMachineById(id);
    }

    @Operation(
            summary = "Delete coffee machine by ID",
            description = "Coffee machine must exist",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "400", description = "Invalid ID"),
                    @ApiResponse(responseCode = "404", description = "coffee machine not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @DeleteMapping("/{id}")
    public void deleteMachineById(@PathVariable Long id){
        machineService.deleteMachine(id);
    }

    @Operation(
            summary = "Get all coffee machines",
            description = "Coffee machines must exist",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "400", description = "Invalid"),
                    @ApiResponse(responseCode = "404", description = "Coffee machines not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping
    public Page<CoffeeMachineDto> getAllMachines(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                                 @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit){
        return machineService.getAllMachines(offset,limit);
    }
}
