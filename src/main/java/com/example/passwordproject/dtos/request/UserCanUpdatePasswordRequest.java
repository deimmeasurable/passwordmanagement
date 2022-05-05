package com.example.passwordproject.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserCanUpdatePasswordRequest {
    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

}
