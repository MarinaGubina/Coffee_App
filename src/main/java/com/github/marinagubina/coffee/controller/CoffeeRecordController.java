package com.github.marinagubina.coffee.controller;

import com.github.marinagubina.coffee.constant.CoffeeType;
import com.github.marinagubina.coffee.constant.Sugar;
import com.github.marinagubina.coffee.entity.CoffeeRecord;
import com.github.marinagubina.coffee.service.CoffeeRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/records")
@Tag(name = "Coffee Records Operation")
public class CoffeeRecordController {

    private final CoffeeRecordService recordService;

    public CoffeeRecordController(CoffeeRecordService recordService) {
        this.recordService = recordService;
    }

    @Operation(
            summary = "Make coffee",
            description = "Coffee machine must exist",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "400", description = "Invalid ID"),
                    @ApiResponse(responseCode = "404", description = "coffee machine not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PostMapping
    public CoffeeRecord brewCoffee(@RequestParam(value = "id-machine", defaultValue = "1") @Min(1) Long id,
                                   @RequestParam("coffee") CoffeeType type,
                                   @RequestParam("sugar")Sugar sugar){
        return recordService.createRecord(id,type,sugar);
    }

    @Operation(
            summary = "Get coffee record by ID",
            description = "Coffee record must exist",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "400", description = "Invalid ID"),
                    @ApiResponse(responseCode = "404", description = "Coffee record not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/{id}")
    public CoffeeRecord getRecord(@PathVariable Long id){
        return recordService.getRecordById(id);
    }

    @Operation(
            summary = "Delete coffee record by ID",
            description = "Coffee record must exist",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "400", description = "Invalid ID"),
                    @ApiResponse(responseCode = "404", description = "Coffee record not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Long id){
        recordService.deleteRecord(id);
    }

    @Operation(
            summary = "Get all coffee records",
            description = "Coffee records must exist",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "400", description = "Invalid ID"),
                    @ApiResponse(responseCode = "404", description = "Coffee records not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping
    public Page<CoffeeRecord> getAllOrders(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                           @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit){
        return recordService.getAllRecords(offset,limit);
    }
}
