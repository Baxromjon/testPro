package com.example.test2.repository;

import com.example.test2.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by Baxromjon
 * 15.08.2021
 **/


public interface EmployeeRepository extends JpaRepository< Employee, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
}
