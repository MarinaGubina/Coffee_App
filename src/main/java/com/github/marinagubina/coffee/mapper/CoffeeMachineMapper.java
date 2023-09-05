package com.github.marinagubina.coffee.mapper;

import com.github.marinagubina.coffee.dto.CapacityContainerDto;
import com.github.marinagubina.coffee.dto.CoffeeMachineDto;
import com.github.marinagubina.coffee.entity.CoffeeMachine;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.data.domain.Page;

import java.util.function.Function;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CoffeeMachineMapper {

    CoffeeMachine toEntity(CapacityContainerDto dto);

    CoffeeMachineDto toDto(CoffeeMachine entity);

    default Page<CoffeeMachineDto> toPage(Page<CoffeeMachine> entities) {
        return entities.map(new Function<CoffeeMachine, CoffeeMachineDto>() {
            @Override
            public CoffeeMachineDto apply(CoffeeMachine entity) {
                return toDto(entity);
            }
        });
    }
}
