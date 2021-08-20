package com.example.test2.controller;
/**
 * created by Baxromjon
 * 15.08.2021
 **/

import com.example.test2.Payload.ApiResponse;
import com.example.test2.Payload.RoleDTO;
import com.example.test2.entity.Role;
import com.example.test2.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
@CrossOrigin
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("/getRole")
    List<Role> getRole() {
        return roleService.getAllRole();
    }

    @PostMapping("/addRole")
    HttpEntity<?> addRole(@RequestBody RoleDTO roleDTO) {
        ApiResponse apiResponse = roleService.saveRole(roleDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
