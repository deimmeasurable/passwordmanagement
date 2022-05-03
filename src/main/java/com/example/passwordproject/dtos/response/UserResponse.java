package com.example.passwordproject.dtos.response;

import com.example.passwordproject.data.models.UserListOfPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String fullName;
    private String email;
    private String password;
    private String url;
    private UserListOfPassword listOfPassword;
}
