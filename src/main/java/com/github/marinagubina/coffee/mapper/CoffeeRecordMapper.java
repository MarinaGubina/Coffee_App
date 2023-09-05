package com.github.marinagubina.coffee.mapper;

import com.github.marinagubina.coffee.dto.CoffeeRecordDto;
import com.github.marinagubina.coffee.dto.CreateRecordDto;
import com.github.marinagubina.coffee.entity.CoffeeRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.function.Function;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CoffeeRecordMapper {

    @Mapping(target = "machine", source = "machine.id")
    CoffeeRecordDto toDto(CoffeeRecord entity);

    CoffeeRecord toEntity(CreateRecordDto dto);

    default Long dateTime(LocalDateTime value){
        return value.toInstant(ZoneOffset.ofHours(3)).toEpochMilli();
    }

    default Page<CoffeeRecordDto> toPage(Page<CoffeeRecord> entities) {
        return entities.map(new Function<CoffeeRecord, CoffeeRecordDto>() {
            @Override
            public CoffeeRecordDto apply(CoffeeRecord entity) {
                return toDto(entity);
            }
        });
    }
}
