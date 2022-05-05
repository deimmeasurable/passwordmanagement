package com.example.passwordproject.Services;

import com.example.passwordproject.data.models.User;
import com.example.passwordproject.dtos.request.SitePasswordSaved;
import com.example.passwordproject.dtos.request.UserRequest;
import com.example.passwordproject.dtos.response.SiteResponse;
import com.example.passwordproject.dtos.response.UserResponse;

import java.util.List;
//@Service
public interface PasswordManagerService {
    UserResponse createAccount(UserRequest request);

    List<User> getAllUsers();




    SiteResponse savedUserCanLogInToWhatAppsWithAnotherPasswordAndUsername(SitePasswordSaved request);


   User userCanUpdateTheirPassword(UserRequest request,String password);



   SiteResponse userMustLogInBeforeTheyCanSavePassword(String email, String password);

    SiteResponse userAddPasswordToListOfPassword(SitePasswordSaved request);

    SiteResponse savedUserCanSavePasswordOfUrl(SitePasswordSaved request);

    SiteResponse DeleteAuser(String email);

    UserResponse userCanBeFindByLastNameAndFirstName(String goat, String dele);

    SiteResponse userCanUpdatePasswordInListOfPassword(SitePasswordSaved request,String password);
}
