package com.github.marinagubina.coffee.repository;

import com.github.marinagubina.coffee.entity.CoffeeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeRecordRepository extends JpaRepository<CoffeeRecord,Long> {

    void deleteAllByMachineId(Long machineId);
}
