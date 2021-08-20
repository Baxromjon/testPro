package com.example.test2.Payload;
/**
 * created by Baxromjon
 * 15.08.2021
 **/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class RoleEmployeeDTO {
    private Integer roleId;
    private List<String> fields;
}
