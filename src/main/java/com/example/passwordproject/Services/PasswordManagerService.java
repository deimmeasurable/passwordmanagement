package com.example.passwordproject.Services;

import com.example.passwordproject.data.models.UserListOfPassword;
import com.example.passwordproject.data.models.User;
import com.example.passwordproject.dtos.request.SitePasswordSaved;
import com.example.passwordproject.dtos.request.UserRequest;
import com.example.passwordproject.dtos.response.SiteResponse;
import com.example.passwordproject.dtos.response.UserResponse;

import java.util.List;

public interface PasswordManagerService {
    UserResponse createAccount(UserRequest request);

    List<User> getAllUsers();


//    List<UserResponse> SavedUserCanHaveDifferentPassword(String email, String password);

    SiteResponse twoUsersCanNotHaveSameUserNameAndPassword(SitePasswordSaved sitePasswordSaved,String username, String password);

    SiteResponse savedUserCanHaveDifferentPassword(SitePasswordSaved request);

    SiteResponse savedUserCanLogInToInstagramWithAnotherPasswordAndUsername(SitePasswordSaved request);

    SiteResponse savedUserCanLogInToWhatappsWithAnotherPasswordAndUsername(SitePasswordSaved request);


   User userCanUpdateTheirPassword(String email,String password);

}
