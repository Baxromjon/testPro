package com.example.test2.controller;

import com.example.test2.Payload.ApiResponse;
import com.example.test2.Payload.EmployeeDTO;
import com.example.test2.Payload.ResUser;
import com.example.test2.Payload.RoleEmployeeDTO;
import com.example.test2.entity.Employee;
import com.example.test2.entity.RoleEmployee;
import com.example.test2.entity.User;
import com.example.test2.repository.RoleEmployeeRepository;
import com.example.test2.repository.UserRepository;
import com.example.test2.security.CurrentUser;
import com.example.test2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * created by Baxromjon
 * 15.08.2021
 **/
@RestController
@RequestMapping("/api/employee")
@CrossOrigin
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    RoleEmployeeRepository roleEmployeeRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/me")
    public HttpEntity<?> getUserMe(@CurrentUser User user){
        return ResponseEntity.status(user!=null?200:409).body(user!=null?employeeService.getUserDto(user):user);
    }

    @GetMapping("/getEmployee")
    List<Employee> getEmployee() {
        return employeeService.getAllEmployee();
    }

    @PostMapping("/addEmployee")
    HttpEntity<?> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        ApiResponse apiResponse = employeeService.addEmployee(employeeDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PostMapping("/addRoleEmployee")
    HttpEntity<?> addRoleEmployee(@RequestBody RoleEmployeeDTO roleEmployeeDTO) {
        ApiResponse apiResponse = employeeService.addRoleEmployee(roleEmployeeDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
    @GetMapping("/getUsers")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/getRoleEmployeeById")
    List<String> getRoleEmployee(@CurrentUser User user){
       return roleEmployeeRepository.getRoleEmployee(user.getId());
    }
}
