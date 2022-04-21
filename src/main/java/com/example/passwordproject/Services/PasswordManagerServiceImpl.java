package com.example.passwordproject.Services;

import com.example.passwordproject.Util.ModelMapper;
import com.example.passwordproject.data.models.User;
import com.example.passwordproject.data.models.UserListOfPassword;
import com.example.passwordproject.data.repository.UserPassWordRepository;

import com.example.passwordproject.dtos.request.SitePasswordSaved;
import com.example.passwordproject.dtos.request.UserRequest;
import com.example.passwordproject.dtos.response.SiteResponse;
import com.example.passwordproject.dtos.response.UserResponse;
import com.example.passwordproject.exception.InvalidPassWordException;
import com.example.passwordproject.exception.UserAlreadyExistException;
import com.example.passwordproject.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class PasswordManagerServiceImpl implements PasswordManagerService {

//    Autowired

    @Autowired
    UserPassWordRepository userPassWordRepository;


    @Override
    public UserResponse createAccount(UserRequest request) {
        User newUser = new User();
        newUser.setPassword(request.getUniquePassword());
        newUser.setEmail(request.getEmail());
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        if (validatePassword(request.getUniquePassword())) throw new InvalidPassWordException("Invalid Password");
        if (emailAlreadyExist(request.getEmail())) throw new UserAlreadyExistException("user already exist");

        userPassWordRepository.save(newUser);
        System.out.println(userPassWordRepository);

        return ModelMapper.map(newUser);

    }

    private boolean emailAlreadyExist(String email) {
        if (userPassWordRepository.findUserByEmail(email) != null) {
            return true;
        }
        return false;

    }

    private boolean validatePassword(String password) {
        return ((password.length() < 10
                && !password.matches("[?./,<>!@#$%^&*_+-=|]")
                && !password.matches("[A-Z]")
                && !password.matches("[0-9]")
                && !password.matches("[a-z]")

        ));
    }

    @Override
    public List<User> getAllUsers() {
        return userPassWordRepository.findAll();

    }

    public SiteResponse savedUserCanHaveDifferentPassword(SitePasswordSaved sitePasswordSaved) {
        User foundUser = userPassWordRepository.findUserByEmail(sitePasswordSaved.getEmail());
//        if(checkIfUserIsNull(foundUser));
        if (foundUser == null) throw new UserNotFoundException("user don't exist");

        List<UserListOfPassword> userPassword = foundUser.getPasswordToSave();

        UserListOfPassword userListOfPassword = new UserListOfPassword();
        userListOfPassword.setPassword(sitePasswordSaved.getPassword());
        userListOfPassword.setUsername(sitePasswordSaved.getUsername());
        userListOfPassword.setWebsite(sitePasswordSaved.getUrl());
        userPassword.add(userListOfPassword);
//        userPassWordRepository.save(foundUser);

        SiteResponse siteResponse = new SiteResponse();
        siteResponse.setResponse("your password was saved successfully");
        return siteResponse;

    }

    @Override
    public SiteResponse savedUserCanLogInToInstagramWithAnotherPasswordAndUsername(SitePasswordSaved request) {
        User foundUser = userPassWordRepository.findUserByEmail(request.getEmail());
//        if(checkIfUserIsNull(foundUser));
        if (foundUser == null) throw new UserNotFoundException("user don't exist");

        List<UserListOfPassword> userPassword = foundUser.getPasswordToSave();

        UserListOfPassword userListOfPassword = new UserListOfPassword();
        userListOfPassword.setPassword(request.getPassword());
        userListOfPassword.setUsername(request.getUsername());
        userListOfPassword.setWebsite(request.getUrl());
        userPassword.add(userListOfPassword);
//        userPassWordRepository.save(foundUser);

        SiteResponse siteResponse = new SiteResponse();
        siteResponse.setResponse("your password was saved successfully");
        return siteResponse;

    }

    @Override
    public SiteResponse savedUserCanLogInToWhatappsWithAnotherPasswordAndUsername(SitePasswordSaved Whatapprequest) {
        User foundUser = userPassWordRepository.findUserByEmail(Whatapprequest.getEmail());
//        if(checkIfUserIsNull(foundUser));
        if (foundUser == null) throw new UserNotFoundException("user don't exist");

        List<UserListOfPassword> userPassword = foundUser.getPasswordToSave();

        UserListOfPassword userListOfPassword = new UserListOfPassword();
        userListOfPassword.setPassword(Whatapprequest.getPassword());
        userListOfPassword.setUsername(Whatapprequest.getUsername());
        userListOfPassword.setWebsite(Whatapprequest.getUrl());
        userPassword.add(userListOfPassword);
//        userPassWordRepository.save(foundUser);

        SiteResponse siteResponse = new SiteResponse();
        siteResponse.setResponse("your password was saved successfully");
        return siteResponse;

    }

    @Override
    public User userCanUpdateTheirPassword(String email, String password) {
      User user=  userPassWordRepository.findUserByEmail(email);
        if(user==null) throw  new UserNotFoundException("user don't exist");
        

        return null;
    }


    @Override
    public SiteResponse twoUsersCanNotHaveSameUserNameAndPassword(SitePasswordSaved sitePasswordSaved,String username, String password) {
        SiteResponse siteResponse = new SiteResponse();

         User user = userPassWordRepository.findUserByEmail(sitePasswordSaved.getEmail());
        List<UserListOfPassword> userPassword = user.getPasswordToSave();
        UserListOfPassword userListOfPassword=new UserListOfPassword();
        userListOfPassword.setPassword(sitePasswordSaved.getPassword());
        userListOfPassword.setUsername(sitePasswordSaved.getUsername());
        userListOfPassword.setWebsite(sitePasswordSaved.getUrl());
        userListOfPassword.setPassword(sitePasswordSaved.getPassword());
        if (sitePasswordSaved.getUsername().equals(username) && sitePasswordSaved.getPassword().equals(password)) throw new UserAlreadyExistException("username and password already exist");
        userPassword.add(userListOfPassword);
            siteResponse.setResponse(siteResponse.getResponse());


        return siteResponse;
    }

    private boolean checkIfUserIsNull(User user) {
        return user.getEmail() != null;
    }

}