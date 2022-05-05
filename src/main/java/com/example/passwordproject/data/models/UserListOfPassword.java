package com.example.passwordproject.data.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
public class UserListOfPassword {
    @Id
    private String id;
    private String url;
    private String username;
    private String email;
    private String password;

}
