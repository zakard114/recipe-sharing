package com.heeju.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String password;
    private String email;
    private String fullName;

}
