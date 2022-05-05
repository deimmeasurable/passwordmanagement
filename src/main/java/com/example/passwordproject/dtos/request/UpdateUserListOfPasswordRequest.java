package com.example.passwordproject.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserListOfPasswordRequest {
    private String url;
    private String username;
    private String newEmail;
    private String accountPassword;
    private String newPassword;
    private String oldEmail;
}
