package com.example.passwordproject.Services;

import com.example.passwordproject.Util.ModelMapper;
import com.example.passwordproject.data.models.User;
import com.example.passwordproject.data.models.UserListOfPassword;
import com.example.passwordproject.data.repository.ListOfUserPasswordRepository;
import com.example.passwordproject.data.repository.UserPassWordRepository;

import com.example.passwordproject.dtos.request.SitePasswordSaved;
import com.example.passwordproject.dtos.request.UserRequest;
import com.example.passwordproject.dtos.response.SiteResponse;
import com.example.passwordproject.dtos.response.UserResponse;
import com.example.passwordproject.exception.InvalidPassWordException;
import com.example.passwordproject.exception.UserAlreadyExistException;
import com.example.passwordproject.exception.UserIsNotLogInExeception;
import com.example.passwordproject.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PasswordManagerServiceImpl implements PasswordManagerService {


    @Autowired
    UserPassWordRepository userPassWordRepository;

    @Autowired
    ListOfUserPasswordRepository listOfUserPasswordRepository;


    @Override
    public UserResponse createAccount(UserRequest request) {
        User newUser = new User();
        newUser.setPassword(request.getUniquePassword());
        newUser.setEmail(request.getEmail());
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setPasswordToSave(new ArrayList<>());


        if (validatePassword(request.getUniquePassword())) throw new InvalidPassWordException("Invalid Password");
        if (emailAlreadyExist(request.getEmail())) throw new UserAlreadyExistException("user already exist");

        userPassWordRepository.save(newUser);
//        System.out.println(newUser);

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


    @Override
    public SiteResponse savedUserCanLogInToInstagramWithAnotherPasswordAndUsername(SitePasswordSaved request) {
        User foundUser = userPassWordRepository.findUserByEmail(request.getEmail());

        if (foundUser == null) throw new UserNotFoundException("user don't exist");
        if(!foundUser.isUserStatus()==false){throw new UserIsNotLogInExeception("user did not log in");}
        List<UserListOfPassword> userPassword = foundUser.getPasswordToSave();

        UserListOfPassword userListOfPassword = new UserListOfPassword();
        userListOfPassword.setPassword(request.getPassword());
        userListOfPassword.setUsername(request.getUsername());
        userListOfPassword.setUrl(request.getUrl());
        userPassword.add(userListOfPassword);

        userPassWordRepository.save(foundUser);

        SiteResponse siteResponse = new SiteResponse();
        siteResponse.setResponse("your password was saved successfully");
        return siteResponse;

    }

    @Override
    public SiteResponse savedUserCanLogInToWhatAppsWithAnotherPasswordAndUsername(SitePasswordSaved Whatapprequest) {
        User foundUser = userPassWordRepository.findUserByEmail(Whatapprequest.getEmail());

        if (foundUser == null) throw new UserNotFoundException("user don't exist");
        if (!foundUser.isUserStatus() == Boolean.parseBoolean(String.valueOf(false))) {
            throw new UserIsNotLogInExeception("user didn't login");
        }
        List<UserListOfPassword> listPasswordSaved = foundUser.getPasswordToSave();

        UserListOfPassword userListOfPassword = new UserListOfPassword();
        userListOfPassword.setPassword(Whatapprequest.getPassword());
        userListOfPassword.setUsername(Whatapprequest.getUsername());
        userListOfPassword.setUrl(Whatapprequest.getUrl());
        userListOfPassword.setEmail(Whatapprequest.getEmail());
        listPasswordSaved.add(userListOfPassword);

        listOfUserPasswordRepository.save(userListOfPassword);
        userPassWordRepository.save(foundUser);
        System.out.println(foundUser);


        SiteResponse siteResponse = new SiteResponse();
        siteResponse.setResponse("your password was saved successfully");
        return siteResponse;

    }

    @Override
    public User userCanUpdateTheirPassword(String email, String password) {
        User user = userPassWordRepository.findUserByEmail(email);

        if (user == null) throw new UserNotFoundException("user don't exist");
        if(!user.isUserStatus()==false){throw new UserIsNotLogInExeception("user did not log in");}
        userPassWordRepository.delete(user);
        user.setPassword(password);
        userPassWordRepository.save(user);


        ModelMapper.map(user);


        return user;

    }


    @Override
    public SiteResponse userMustLogInBeforeTheyCanSavePassword(String email, String password) {
       SiteResponse siteResponse = new SiteResponse();
        User foundUser= userPassWordRepository.findUserByEmail(email);
        if(foundUser==null){throw  new UserNotFoundException("user does not exist");}
        if(foundUser.getPassword().equals(password)){
            foundUser.setUserStatus(true);
            userPassWordRepository.save(foundUser);
           siteResponse.setResponse("user as successfully login");

        }

        return siteResponse;
    }

    @Override
    public SiteResponse userAddDifferentPassword(SitePasswordSaved request) {
       SiteResponse siteResponse = new SiteResponse();
        User foundUser =userPassWordRepository.findUserByEmail(request.getEmail());
        if(!foundUser.isUserStatus()){throw new UserIsNotLogInExeception("user did not login");}

        List<UserListOfPassword> ListOfPasswordsaved = foundUser.getPasswordToSave();

        UserListOfPassword userListOfPassword = new UserListOfPassword();
        userListOfPassword.setPassword(request.getPassword());
        userListOfPassword.setUsername(request.getUsername());
        userListOfPassword.setUrl(request.getUrl());
        ListOfPasswordsaved.add(userListOfPassword);
        listOfUserPasswordRepository.save(userListOfPassword);
        userPassWordRepository.save(foundUser);



            siteResponse.setResponse("user did not login");

            return siteResponse;

    }

    @Override
    public SiteResponse savedUserCanSavePasswordOfUrl(SitePasswordSaved request) {
        User foundUser = userPassWordRepository.findUserByEmail(request.getEmail());
        if (foundUser == null) {throw new UserNotFoundException("user not found");}

        if(!foundUser.isUserStatus() == Boolean.parseBoolean(String.valueOf(false))){ throw new UserIsNotLogInExeception("user didn't login");}
      List<UserListOfPassword> listOfPassword=foundUser.getPasswordToSave();
                    UserListOfPassword userListOfPassword = new UserListOfPassword();
                                    userListOfPassword.setPassword(request.getPassword());
                userListOfPassword.setUsername(request.getUsername());
                userListOfPassword.setUrl(request.getUrl());
                userListOfPassword.setEmail(request.getEmail());
                userListOfPassword.setPassword(request.getPassword());
                listOfPassword.add(userListOfPassword);

                listOfUserPasswordRepository.save(userListOfPassword);
//               System.out.println(userListOfPassword);
                userPassWordRepository.save(foundUser);
                System.out.println(foundUser);
                SiteResponse siteResponse = new SiteResponse();
                siteResponse.setResponse("your password was saved successfully");
                return siteResponse;
        }

    @Override
    public SiteResponse DeleteAuser(String email) {
        User user = userPassWordRepository.findUserByEmail(email);
        if (user == null) {throw new UserIsNotLogInExeception("user not found");}
        userPassWordRepository.delete(user);
        SiteResponse siteResponse = new SiteResponse();
        siteResponse.setResponse("user deleted successfully");
        return siteResponse;
    }

    @Override
    public UserResponse userCanBeFindByLastNameAndFirstName(String firstName, String lastName) {
        User user = userPassWordRepository.findUserByFirstNameAndLastName(firstName,lastName);
        if (user == null) {
            throw new UserNotFoundException("user doesn't exist");
        }
        System.out.println(user);
       UserResponse userResponse = new UserResponse();
       userResponse.setFullName(firstName+" "+lastName);
       userResponse.setPassword(user.getPassword());
       userResponse.setEmail(user.getEmail());
       return userResponse;

    }

    @Override
    public SiteResponse userCanUpdatePasswordInListOfPassword(SitePasswordSaved request,String password) {
        User user = userPassWordRepository.findUserByEmail(request.getEmail());
        if (user == null) {throw new UserNotFoundException("user not found");}
        if(!user.isUserStatus()==false){throw new UserIsNotLogInExeception("user did not login");}

        List<UserListOfPassword> saveUserListOfpassword = user.getPasswordToSave();

            UserListOfPassword userListOfPassword = new UserListOfPassword();
            userListOfPassword.setPassword(password);
            userListOfPassword.setUrl(request.getUrl());
            userListOfPassword.setEmail(request.getEmail());
            userListOfPassword.setUsername(request.getUsername());


            saveUserListOfpassword.add(userListOfPassword);
            listOfUserPasswordRepository.save(userListOfPassword);
            userPassWordRepository.save(user);
            System.out.println(user);
            SiteResponse  siteResponse = new SiteResponse();
            siteResponse.setResponse("your password list was successfully update");


            return siteResponse;
    }


    @Override
    public String toString() {
        return "PasswordManagerServiceImpl{" +
                "userPassWordRepository=" + userPassWordRepository +
                '}';
    }

}