package com.hotelreservation.balanceservice.repository;

import com.hotelreservation.balanceservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findUserById(Long userid);

    User findByUsername(String username);
}
