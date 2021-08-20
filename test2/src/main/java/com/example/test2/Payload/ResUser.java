package com.example.test2.Payload;
/**
 * created by Baxromjon
 * 18.08.2021
 **/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class ResUser {
    private Integer id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String phoneNumber;
}
