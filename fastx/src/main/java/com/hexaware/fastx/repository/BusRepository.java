package com.hexaware.fastx.repository;

import com.hexaware.fastx.entity.Bus;
import com.hexaware.fastx.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusRepository extends JpaRepository<Bus, Long> {

    List<Bus> findByOperator(User operator);

}
