package com.example.test2.repository;

import com.example.test2.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by Baxromjon
 * 15.08.2021
 **/

public interface RoleRepository extends JpaRepository<Role, Integer> {
    boolean existsByName(String name);
}
