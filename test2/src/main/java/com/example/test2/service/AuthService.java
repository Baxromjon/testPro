package com.example.test2.service;
/**
 * created by Baxromjon
 * 15.08.2021
 **/

import com.example.test2.Payload.ApiResponse;
import com.example.test2.Payload.RegisterDTO;
import com.example.test2.entity.User;
import com.example.test2.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByPhoneNumber(s).orElseThrow(() -> new UsernameNotFoundException(s));

    }

    public UserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        return userRepository.findById(Integer.parseInt(userId)).orElseThrow(() -> new UsernameNotFoundException("userId"));
    }

    public ApiResponse register(RegisterDTO registerDTO) {
        boolean exists = userRepository.existsByPhoneNumber(registerDTO.getPhoneNumber());
        if (exists) {
            return new ApiResponse(false, "allready exists");
        }
        User user = new User();
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setMiddleName(registerDTO.getMiddleName());
        user.setPhoneNumber(registerDTO.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        userRepository.save(user);
        return new ApiResponse(true, "successfully saved");
    }
}
