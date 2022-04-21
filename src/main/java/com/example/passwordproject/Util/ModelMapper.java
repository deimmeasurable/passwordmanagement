package com.example.passwordproject.Util;

import com.example.passwordproject.data.models.User;
import com.example.passwordproject.data.models.UserListOfPassword;
import com.example.passwordproject.dtos.request.SitePasswordSaved;
import com.example.passwordproject.dtos.response.SiteResponse;
import com.example.passwordproject.dtos.response.UserResponse;

public class ModelMapper {
    public  static  UserResponse map(User newUser){
        UserResponse userResponse = new UserResponse();
        userResponse.setPassword(newUser.getPassword());
        userResponse.setEmail(newUser.getEmail());
        userResponse.setFullName(newUser.getFirstName() + " " + newUser.getLastName());
        SiteResponse siteResponse = new SiteResponse();
        siteResponse.setResponse("registeration successful");
        return userResponse;
    }
    public static  SiteResponse map_siteResponse(SiteResponse siteResponse){
//        SiteResponse siteResponse = new SiteResponse();
        siteResponse.setResponse("your password was save successfully");
        return siteResponse;
    }
}
