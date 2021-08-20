package com.example.test2.repository;

import com.example.test2.entity.RoleEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * created by Baxromjon
 * 15.08.2021
 **/

public interface RoleEmployeeRepository extends JpaRepository<RoleEmployee, Integer> {
    @Query(nativeQuery = true, value = "select field_name from role_employee\n" +
            "where role_id=:userId")
    List<String> getRoleEmployee(Integer userId);
}
