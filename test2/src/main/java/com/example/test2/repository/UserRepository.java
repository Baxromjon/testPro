package com.example.test2.repository;

import com.example.test2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * created by Baxromjon
 * 15.08.2021
 **/

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByPhoneNumber(String s);
    boolean existsByPhoneNumber(String phoneNumber);

}
