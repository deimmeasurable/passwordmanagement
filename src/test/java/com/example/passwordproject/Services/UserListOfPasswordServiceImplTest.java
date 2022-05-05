package com.example.passwordproject.Services;

import com.example.passwordproject.data.models.UserListOfPassword;
import com.example.passwordproject.dtos.request.SitePasswordSaved;
import com.example.passwordproject.dtos.request.UpdateUserListOfPasswordRequest;
import com.example.passwordproject.dtos.request.UserRequest;
import com.example.passwordproject.dtos.response.SiteResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
@DataMongoTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DirtiesContext(classMode =  DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserListOfPasswordServiceImplTest {
    @Autowired
    private UserListOfPasswordService userListOfPasswordService;
    @Autowired
    private PasswordManagerService passwordManagerService;

    @BeforeEach
    void setUp() {

    }
    @Test
    void testThatPasswordCanBeCreated(){
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

        SiteResponse siteResponse =userListOfPasswordService.createPasswordAccount(request);
        //save password for this url site
        //assert that the password exist
        assertThat(request.getUrl(), is("www.facebook.com"));
        assertThat(request.getUsername(), is("Danielson45"));
        assertThat(request.getPassword(), is("Deacrest567@"));
        assertThat(siteResponse.getResponse(), is("password created successfully"));
    }
    @Test
    void thatPasswordCanBeFoundedByUrl(){
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

        userListOfPasswordService.createPasswordAccount(request);

        String password = userListOfPasswordService.findPasswordByUrl(request);

        assertThat(password,is("Deacrest567@"));




    }
    @Test
    void testThatPasswordCanBeUpdatedInListOfPassword() {
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

        userListOfPasswordService.createPasswordAccount(request);
        UpdateUserListOfPasswordRequest updateForm = new UpdateUserListOfPasswordRequest();
        updateForm.setNewEmail("tayetaiwoA@gmail.com");
        updateForm.setOldEmail("house345@gmail.com");
        updateForm.setAccountPassword("Deacrest567@");
        updateForm.setUsername("ToshGirl2");
        updateForm.setUrl("www.facebook.com");
        updateForm.setNewPassword("tolaniBash987@");
        userListOfPasswordService.updatePassword(updateForm);



    }

}