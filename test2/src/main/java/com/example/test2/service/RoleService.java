package com.example.test2.service;
/**
 * created by Baxromjon
 * 15.08.2021
 **/

import com.example.test2.Payload.ApiResponse;
import com.example.test2.Payload.RoleDTO;
import com.example.test2.entity.Role;
import com.example.test2.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    public ApiResponse saveRole(RoleDTO roleDTO) {
        boolean exists = roleRepository.existsByName(roleDTO.getName());
        if (exists){
            return new ApiResponse(false,"allready exists");
        }
        Role role=new Role();
        role.setName(roleDTO.getName());
        roleRepository.save(role);
        return new ApiResponse(true,"successfully added");
    }
}
