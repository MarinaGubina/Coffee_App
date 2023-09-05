package com.github.marinagubina.coffee.dto;

import com.github.marinagubina.coffee.constant.CoffeeType;
import com.github.marinagubina.coffee.constant.Sugar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRecordDto {

    private CoffeeType type;

    private Sugar sugar;
}
