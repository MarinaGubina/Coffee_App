package com.github.marinagubina.coffee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeRecordDto {
    private Long id;

    private String type;

    private String sugar;

    private Long dateTime;

    private boolean completionStatus;

    private Long machine;
}
