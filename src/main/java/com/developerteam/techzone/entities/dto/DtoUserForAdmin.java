package com.developerteam.techzone.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoUserForAdmin {

    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String phoneNumber;

}
