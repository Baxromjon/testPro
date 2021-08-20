package com.example.test2.component;

import com.example.test2.entity.Role;
import com.example.test2.entity.RoleEmployee;
import com.example.test2.entity.User;
import com.example.test2.repository.RoleEmployeeRepository;
import com.example.test2.repository.RoleRepository;
import com.example.test2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * created by Baxromjon
 * 15.08.2021
 **/

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    RoleEmployeeRepository roleEmployeeRepository;

    @Value(value = "${spring.datasource.initialization-mode}")
    private String initialMode;
    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")){

            Role roleAdmin=new Role();
            roleAdmin.setName("ROLE_ADMIN");
            Role save = roleRepository.save(roleAdmin);

            User admin=new User();
            admin.setFirstName("Admin");
            admin.setLastName("Adminov");
            admin.setPhoneNumber("+998990068005");
            admin.setPassword(encoder.encode("root123"));
            admin.setRoles(Collections.singletonList(roleAdmin));
            userRepository.save(admin);

            RoleEmployee roleEmployee=new RoleEmployee();
            roleEmployee.setRole(save);
            roleEmployee.setFieldName("first_name");
            roleEmployeeRepository.save(roleEmployee);

            RoleEmployee roleEmployee1=new RoleEmployee();
            roleEmployee1.setRole(save);
            roleEmployee1.setFieldName("last_name");
            roleEmployeeRepository.save(roleEmployee1);

            RoleEmployee roleEmployee2 = new RoleEmployee();
            roleEmployee2.setRole(save);
            roleEmployee2.setFieldName("middle_name");
            roleEmployeeRepository.save(roleEmployee2);

            RoleEmployee roleEmployee3 = new RoleEmployee();
            roleEmployee3.setRole(save);
            roleEmployee3.setFieldName("birth_date");
            roleEmployeeRepository.save(roleEmployee3);

            RoleEmployee roleEmployee4 = new RoleEmployee();
            roleEmployee4.setRole(save);
            roleEmployee4.setFieldName("passport_serial");
            roleEmployeeRepository.save(roleEmployee4);

            RoleEmployee roleEmployee5 = new RoleEmployee();
            roleEmployee5.setRole(save);
            roleEmployee5.setFieldName("passport_serial_number");
            roleEmployeeRepository.save(roleEmployee5);

            RoleEmployee roleEmployee6 = new RoleEmployee();
            roleEmployee6.setRole(save);
            roleEmployee6.setFieldName("phone_number");
            roleEmployeeRepository.save(roleEmployee6);

            RoleEmployee roleEmployee7 = new RoleEmployee();
            roleEmployee7.setRole(save);
            roleEmployee7.setFieldName("password");
            roleEmployeeRepository.save(roleEmployee7);
        }

    }
}
