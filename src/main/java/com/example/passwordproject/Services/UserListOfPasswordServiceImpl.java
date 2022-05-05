package com.example.passwordproject.Services;

import com.example.passwordproject.data.models.User;
import com.example.passwordproject.data.models.UserListOfPassword;
import com.example.passwordproject.data.repository.ListOfUserPasswordRepository;
import com.example.passwordproject.data.repository.UserPassWordRepository;
import com.example.passwordproject.dtos.request.SitePasswordSaved;
import com.example.passwordproject.dtos.request.UpdateUserListOfPasswordRequest;
import com.example.passwordproject.dtos.response.FindUserListOfPasswordResponse;
import com.example.passwordproject.dtos.response.SiteResponse;
import com.example.passwordproject.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserListOfPasswordServiceImpl implements UserListOfPasswordService{
    @Autowired
    private UserPassWordRepository userPassWordRepository;
    @Autowired
    ListOfUserPasswordRepository listOfUserPasswordRepository;

    @Override
    public SiteResponse createPasswordAccount(SitePasswordSaved request) {
        SiteResponse siteResponse = new SiteResponse();
        User foundUser =userPassWordRepository.findUserByEmail(request.getEmail());
        foundUser.setUserStatus(true);

        List<UserListOfPassword> savedPassword = foundUser.getPasswordToSave();

        UserListOfPassword userListOfPassword = new UserListOfPassword();
        userListOfPassword.setPassword(request.getPassword());
        userListOfPassword.setUsername(request.getUsername());
        userListOfPassword.setUrl(request.getUrl());
        userListOfPassword.setEmail(request.getEmail());

       savedPassword.add(userListOfPassword);
        listOfUserPasswordRepository.save(userListOfPassword);
        userPassWordRepository.save(foundUser);




        siteResponse.setResponse("password created successfully");

        return siteResponse;


    }

    @Override
    public String findPasswordByUrl(SitePasswordSaved request) {
        User user =userPassWordRepository.findUserByEmail(request.getEmail());
        if(user == null) {throw new UserNotFoundException("user not found");}
        user.setUserStatus(true);
       UserListOfPassword userListOfPassword =listOfUserPasswordRepository.findUserListOfPasswordByUrl(request.getUrl());
       if(userListOfPassword==null){throw new IllegalArgumentException("url does not exist");}
       FindUserListOfPasswordResponse findPassword = new FindUserListOfPasswordResponse();
       findPassword.setPassword(request.getPassword());
       findPassword.setEmail(request.getEmail());
       findPassword.setUsername(request.getUsername());



       return findPassword.getPassword();

    }

    @Override
    public List<FindUserListOfPasswordResponse> updatePassword(UpdateUserListOfPasswordRequest updateForm) {
        User user = userPassWordRepository.findUserByEmail(updateForm.getOldEmail());
        if(user == null){throw new UserNotFoundException("user not found");
            List<UserListOfPassword> foundPassword = user.getPasswordToSave();
            List<UpdateUserListOfPasswordRequest>

        return null;
    }
}
