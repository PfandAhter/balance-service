package com.hotelreservation.balanceservice.repository;

import com.hotelreservation.balanceservice.model.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance,Long> {
    Balance findByUserId(Long userid);


}
