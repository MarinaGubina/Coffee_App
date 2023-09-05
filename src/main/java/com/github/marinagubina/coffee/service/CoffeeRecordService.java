package com.github.marinagubina.coffee.service;

import com.github.marinagubina.coffee.constant.CoffeeType;
import com.github.marinagubina.coffee.constant.Sugar;
import com.github.marinagubina.coffee.entity.CoffeeRecord;
import org.springframework.data.domain.Page;

public interface CoffeeRecordService {
    CoffeeRecord createRecord(Long idMachine, CoffeeType type, Sugar sugar);

    CoffeeRecord getRecordById(Long id);

    void deleteRecord(Long id);

    Page<CoffeeRecord> getAllRecords(int page,int pageSize);
}
