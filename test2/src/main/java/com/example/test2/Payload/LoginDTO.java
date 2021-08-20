package com.example.test2.Payload;
/**
 * created by Baxromjon
 * 15.08.2021
 **/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class LoginDTO {
    private String phoneNumber;
    private String password;
}
