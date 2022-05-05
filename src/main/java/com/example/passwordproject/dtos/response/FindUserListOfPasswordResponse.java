package com.example.passwordproject.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindUserListOfPasswordResponse {
    private String username;
    private  String url;
    private String password;
    private String email;

}
