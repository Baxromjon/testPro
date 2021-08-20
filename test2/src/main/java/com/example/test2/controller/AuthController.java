package com.example.test2.controller;
/**
 * created by Baxromjon
 * 15.08.2021
 **/

import com.example.test2.Payload.ApiResponse;
import com.example.test2.Payload.LoginDTO;
import com.example.test2.Payload.RegisterDTO;
import com.example.test2.Payload.ResUser;
import com.example.test2.entity.User;
import com.example.test2.security.CurrentUser;
import com.example.test2.security.JwtTokenProvider;
import com.example.test2.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;



    @PostMapping("/register")
    HttpEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        ApiResponse register = authService.register(registerDTO);
        return ResponseEntity.status(register.isSuccess() ? 200 : 409).body(register);
    }

    @PostMapping("/login")
    HttpEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                            loginDTO.getPhoneNumber(),
                            loginDTO.getPassword()
                    ));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = (User) authentication.getPrincipal();
            String token = jwtTokenProvider.generateToken(user.getId());
            return ResponseEntity.status(200).body(new ApiResponse(true, "successfully", token));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(409).body(new ApiResponse(false, "Bad credentials"));
    }

}
