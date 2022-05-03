package com.example.passwordproject.Services;

import com.example.passwordproject.data.models.User;
import com.example.passwordproject.data.models.UserListOfPassword;
import com.example.passwordproject.data.repository.ListOfUserPasswordRepository;
import com.example.passwordproject.data.repository.UserPassWordRepository;
import com.example.passwordproject.dtos.request.SitePasswordSaved;
import com.example.passwordproject.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserListOfPasswordServiceImpl implements UserListOfPasswordService{
    @Autowired
    private UserPassWordRepository userPassWordRepository;
    @Autowired
    ListOfUserPasswordRepository listOfUserPasswordRepository;

    @Override
    public UserListOfPassword createPasswordAccount(SitePasswordSaved request) {
        User foundUser = userPassWordRepository.findUserByEmail(request.getEmail());
        if (foundUser == null) throw new UserNotFoundException("user not found");
        List<UserListOfPassword> savedPassword=foundUser.getPasswordToSave();
        UserListOfPassword createPassword = new UserListOfPassword();
        createPassword.setPassword(request.getPassword());
        createPassword.setEmail(request.getEmail());
        createPassword.setUrl(request.getUrl());
        savedPassword.add(createPassword);
        listOfUserPasswordRepository.save(createPassword);
        return createPassword;


    }
}
