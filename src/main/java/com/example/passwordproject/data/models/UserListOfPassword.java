package com.example.passwordproject.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListOfPassword {
    @Id
    private String id;
    private String website;
    private String username;
    private String email;
    private String password;
}