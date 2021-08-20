package com.example.test2.Payload;
/**
 * created by Baxromjon
 * 15.08.2021
 **/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthDate;
    private String phoneNumber;
    private String password;
    private String passportSerial;
    private String passportSerialNumber;
}
