package com.github.marinagubina.coffee.repository;

import com.github.marinagubina.coffee.entity.CoffeeRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeRecordRepository extends JpaRepository<CoffeeRecord,Long> {

    @Query(value = "SELECT * FROM coffee_records",nativeQuery = true)
    Page<CoffeeRecord> findAllRecords(Pageable pageable);
}
