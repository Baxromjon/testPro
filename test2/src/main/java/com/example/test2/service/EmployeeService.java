package com.example.test2.service;
/**
 * created by Baxromjon
 * 15.08.2021
 **/

import com.example.test2.Payload.ApiResponse;
import com.example.test2.Payload.EmployeeDTO;
import com.example.test2.Payload.ResUser;
import com.example.test2.Payload.RoleEmployeeDTO;
import com.example.test2.entity.Employee;
import com.example.test2.entity.Role;
import com.example.test2.entity.RoleEmployee;
import com.example.test2.entity.User;
import com.example.test2.repository.EmployeeRepository;
import com.example.test2.repository.RoleEmployeeRepository;
import com.example.test2.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleEmployeeRepository roleEmployeeRepository;
    @Autowired
    RoleRepository roleRepository;

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public ApiResponse addEmployee(EmployeeDTO employeeDTO) {
        boolean exists = employeeRepository.existsByPhoneNumber(employeeDTO.getPhoneNumber());
        if (exists) {
            return new ApiResponse(false, "allready exists");
        }
        Employee employee = new Employee();
        employee.setBirthDate(employeeDTO.getBirthDate());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setMiddleName(employeeDTO.getMiddleName());
        employee.setPassportSerial(employeeDTO.getPassportSerial());
        employee.setPassportSerialNumber(employeeDTO.getPassportSerialNumber());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setPassword(passwordEncoder.encode(employeeDTO.getPassword()));
        employeeRepository.save(employee);
        return new ApiResponse(true, "successfully added");
    }

    public ApiResponse addRoleEmployee(RoleEmployeeDTO roleEmployeeDTO) {
        List<RoleEmployee> roleEmployees = new ArrayList<>();
        Optional<Role> roleOptional = roleRepository.findById(roleEmployeeDTO.getRoleId());
        for (String field : roleEmployeeDTO.getFields()) {
            RoleEmployee roleEmployee = new RoleEmployee();
            roleEmployee.setFieldName(field);
            roleEmployee.setRole(roleOptional.get());
            roleEmployees.add(roleEmployee);
            roleEmployeeRepository.saveAll(roleEmployees);
        }
        return new ApiResponse(true, "successfully");
    }

    public ResUser getUserDto(User user) {
        ResUser resUser=new ResUser(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getMiddleName(),
                user.getPhoneNumber());
        return resUser;
    }
}
