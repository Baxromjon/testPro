package com.example.test2.entity;
/**
 * created by Baxromjon
 * 14.08.2021
 **/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthDate;
    private String phoneNumber;
    private String password;
    private String passportSerial;
    private String passportSerialNumber;
}
