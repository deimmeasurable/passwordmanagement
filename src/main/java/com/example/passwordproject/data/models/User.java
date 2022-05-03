package com.example.passwordproject.data.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@Data
@Getter
@Setter
@AllArgsConstructor
@Document("User")
public class User {
    @Id
    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
//    private String url;
    private boolean userStatus=false;

    @DBRef
   private List<UserListOfPassword> passwordToSave=new ArrayList<>();
}
