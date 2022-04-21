package com.example.passwordproject.Services;



import com.example.passwordproject.data.models.User;
import com.example.passwordproject.dtos.request.SitePasswordSaved;
import com.example.passwordproject.dtos.request.UserRequest;

import com.example.passwordproject.dtos.response.SiteResponse;
import com.example.passwordproject.exception.InvalidPassWordException;
import com.example.passwordproject.exception.UserAlreadyExistException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.hamcrest.MatcherAssert.*;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
@DataMongoTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DirtiesContext(classMode =  DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PasswordManagerServiceImplTest {

    @Autowired
    private PasswordManagerService passwordManagerService;


    @Test
    void  testThatUserCanBeCreated(){
        //given
        UserRequest userRequest = new UserRequest();
        userRequest.setUniquePassword("Teast!2349");
        userRequest.setEmail("house345@gmail.com");
        userRequest.setFirstName("goat");
        userRequest.setLastName("dele");
        passwordManagerService.createAccount(userRequest);

        assertThat(userRequest.getUniquePassword(), is ("Teast!2349"));
        assertThat(userRequest.getEmail(), is ("house345@gmail.com"));
        assertThat(userRequest.getFirstName(), is("goat"));
        assertThat(userRequest.getLastName(), is("dele"));

        assertThat(passwordManagerService.getAllUsers().size(), is(1));

    }
    @Test
    void testThatRepositoryCanHaveMoreThanOneUsers(){
        UserRequest userRequest1 = getUserRequest();

        assertThat(userRequest1.getUniquePassword(), is ("cEase@2349"));
        assertThat(userRequest1.getEmail(), is ("zeus7865@yahoo.com"));
        assertThat(userRequest1.getFirstName(), is("fashola"));
        assertThat(userRequest1.getLastName(), is("folashile"));

        assertThat(passwordManagerService.getAllUsers().size(), is(2));
    }

    private UserRequest getUserRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUniquePassword("Teast!2349");
        userRequest.setEmail("house345@gmail.com");
        userRequest.setFirstName("goat");
        userRequest.setLastName("dele");
        passwordManagerService.createAccount(userRequest);

        assertThat(userRequest.getUniquePassword(), is ("Teast!2349"));
        assertThat(userRequest.getEmail(), is ("house345@gmail.com"));
        assertThat(userRequest.getFirstName(), is("goat"));
        assertThat(userRequest.getLastName(), is("dele"));


        UserRequest userRequest1 = new UserRequest();
        userRequest1.setUniquePassword("cEase@2349");
        userRequest1.setEmail("zeus7865@yahoo.com");
        userRequest1.setFirstName("fashola");
        userRequest1.setLastName("folashile");
        passwordManagerService.createAccount(userRequest1);
        return userRequest1;
    }

    @Test
    void testThatIfPassWordBeLessThan10Characters_throwsException(){
        UserRequest userRequest =new UserRequest();
        userRequest.setEmail("house345@gmail.com");
        userRequest.setUniquePassword("teast234");
        assertThrows(InvalidPassWordException.class,()-> passwordManagerService.createAccount(userRequest));
    }
    @Test
    void testThatUserPasswordCanHaveMorePasswordToListOfPassword(){
        //given we have a user that can be found by email;
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("house345@gmail.com");
        userRequest.setFirstName("goat");
        userRequest.setLastName("dele");
        userRequest.setUniquePassword("Teast!2349");
        passwordManagerService.createAccount(userRequest);
        //when the user log into a site
        SitePasswordSaved request = new SitePasswordSaved();
          request.setEmail("house345@gmail.com");
            request.setUrl("www.facebook.com");
            request.setUsername("Danielson45");
            request.setPassword("Deacrest567@");
        SiteResponse siteResponse = passwordManagerService.savedUserCanHaveDifferentPassword(request);
        //save password for this url site
        //assert that the password exist
        assertThat(request.getUrl(), is("www.facebook.com"));
        assertThat(request.getUsername(), is("Danielson45"));
        assertThat(request.getPassword(), is("Deacrest567@"));
      assertThat(siteResponse.getResponse(), is("your password was saved successfully") );





    }
    @Test
    void testThatTwoUsersCanNotHaveTheSameUserNameInSameSite(){
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("house345@gmail.com");
        userRequest.setFirstName("goat");
        userRequest.setLastName("dele");
        userRequest.setUniquePassword("Teast!2349");
        passwordManagerService.createAccount(userRequest);

        //when the user log into a site
        SitePasswordSaved request = new SitePasswordSaved();
        request.setEmail("house345@gmail.com");
        request.setUrl("www.facebook.com");
        request.setUsername("Danielson45");
        request.setPassword("Deacrest567@");
        SiteResponse user = passwordManagerService.twoUsersCanNotHaveSameUserNameAndPassword(request,"Danielson45","Deacrest567@");



        assertThrows(UserAlreadyExistException.class,()->passwordManagerService.twoUsersCanNotHaveSameUserNameAndPassword(request,"Danielson45","Deacrest567@"));



    }
    @Test
    void testThatUserCanLogToAnotherUrlInstagramWithAnotherPasswordAndUsername(){

        //given we have a user that can be found by email;
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("house345@gmail.com");
        userRequest.setFirstName("goat");
        userRequest.setLastName("dele");
        userRequest.setUniquePassword("Teast!2349");
        passwordManagerService.createAccount(userRequest);
        //when the user log into a site
        SitePasswordSaved request = new SitePasswordSaved();
        request.setEmail("house345@gmail.com");
        request.setUrl("www.instagram.com");
        request.setUsername("timothy@134");
        request.setPassword("Finished234!");
        SiteResponse siteResponse = passwordManagerService.savedUserCanLogInToInstagramWithAnotherPasswordAndUsername(request);
        //save password for this url site
        //assert that the password exist
        assertThat(request.getUrl(), is("www.instagram.com"));
        assertThat(request.getUsername(), is("timothy@134"));
        assertThat(request.getPassword(), is("Finished234!"));
        assertThat(siteResponse.getResponse(), is("your password was saved successfully") );

    }
    @Test
    void testThatRegisterUserCanLogInToWhatappsWithAnotherPasswordAndUserName(){
        UserRequest userRequest1 = new UserRequest();
        userRequest1.setUniquePassword("cEase@2349");
        userRequest1.setEmail("zeus7865@yahoo.com");
        userRequest1.setFirstName("fashola");
        userRequest1.setLastName("folashile");
        passwordManagerService.createAccount(userRequest1);

        SitePasswordSaved request = new SitePasswordSaved();
        request.setEmail("zeus7865@yahoo.com");
        request.setUrl("www.Whatapps.com");
        request.setUsername("killIt1234");
        request.setPassword("StormTheyard!123");
        SiteResponse siteResponse = passwordManagerService.savedUserCanLogInToWhatappsWithAnotherPasswordAndUsername(request);
        //save password for this url site
        //assert that the password exist
        assertThat(request.getUrl(), is("www.Whatapps.com"));
        assertThat(request.getUsername(), is("killIt1234"));
        assertThat(request.getPassword(), is("StormTheyard!123"));
        assertThat(siteResponse.getResponse(), is("your password was saved successfully"));
    }
    @Test
    void userCanUpdatePassword(){
        getUserRequest();
        User user =passwordManagerService.userCanUpdateTheirPassword("zeus7865@yahoo.com","deThrate345@");
    }

}