package com.example.passwordproject.dtos.response;

import com.example.passwordproject.data.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ApiResponse {
    private UserResponse payLoad;
    private SiteResponse payLoadLogin;
    private String message;
    private boolean isSuccessful;
}
