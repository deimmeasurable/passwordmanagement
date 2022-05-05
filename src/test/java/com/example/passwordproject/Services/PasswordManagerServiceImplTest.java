package com.example.passwordproject.Services;



import com.example.passwordproject.data.models.User;
import com.example.passwordproject.data.repository.ListOfUserPasswordRepository;
import com.example.passwordproject.dtos.request.SitePasswordSaved;
import com.example.passwordproject.dtos.request.UserRequest;
import com.example.passwordproject.dtos.response.SiteResponse;
import com.example.passwordproject.dtos.response.UserResponse;
import com.example.passwordproject.exception.InvalidPassWordException;
import com.example.passwordproject.exception.UserIsNotLogInExeception;
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

//    @Autowired
//    private UserListOfPasswordService userListOfPasswordService;
//
//    @Autowired
//    private ListOfUserPasswordRepository listOfUserPasswordRepository;
    @Test
    void testThatUserCanBeCreated() {
        //given
        UserRequest userRequest = new UserRequest();
        userRequest.setUniquePassword("Teast!2349");
        userRequest.setEmail("house345@gmail.com");
        userRequest.setFirstName("goat");
        userRequest.setLastName("dele");
        passwordManagerService.createAccount(userRequest);

        assertThat(userRequest.getUniquePassword(), is("Teast!2349"));
        assertThat(userRequest.getEmail(), is("house345@gmail.com"));
        assertThat(userRequest.getFirstName(), is("goat"));
        assertThat(userRequest.getLastName(), is("dele"));

        assertThat(passwordManagerService.getAllUsers().size(), is(1));

    }

    @Test
    void testThatUserMustLogInBeforeTheyCanSavedPassword() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUniquePassword("Teast!2349");
        userRequest.setEmail("house345@gmail.com");
        userRequest.setFirstName("goat");
        userRequest.setLastName("dele");
        passwordManagerService.createAccount(userRequest);

        SiteResponse response = passwordManagerService.userMustLogInBeforeTheyCanSavePassword("house345@gmail.com", "Teast!2349");
        assertThat(response.getResponse(), is("user as successfully login"));
    }

    @Test
    void testThatRepositoryCanHaveMoreThanOneUsers() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUniquePassword("Teast!2349");
        userRequest.setEmail("house345@gmail.com");
        userRequest.setFirstName("goat");
        userRequest.setLastName("dele");
        passwordManagerService.createAccount(userRequest);

        assertThat(userRequest.getUniquePassword(), is("Teast!2349"));
        assertThat(userRequest.getEmail(), is("house345@gmail.com"));
        assertThat(userRequest.getFirstName(), is("goat"));
        assertThat(userRequest.getLastName(), is("dele"));


        UserRequest userRequest1 = new UserRequest();
        userRequest1.setUniquePassword("cEase@2349");
        userRequest1.setEmail("zeus7865@yahoo.com");
        userRequest1.setFirstName("fashola");
        userRequest1.setLastName("folashile");
        passwordManagerService.createAccount(userRequest1);

        assertThat(userRequest1.getUniquePassword(), is("cEase@2349"));
        assertThat(userRequest1.getEmail(), is("zeus7865@yahoo.com"));
        assertThat(userRequest1.getFirstName(), is("fashola"));
        assertThat(userRequest1.getLastName(), is("folashile"));

        assertThat(passwordManagerService.getAllUsers().size(), is(2));
    }

    @Test
    void testThatUserCanNotAddPasswordWithoutLogInThrowUserIsNotLogInException() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUniquePassword("Teast!2349");
        userRequest.setEmail("house345@gmail.com");
        userRequest.setFirstName("goat");
        userRequest.setLastName("dele");
        passwordManagerService.createAccount(userRequest);

        SitePasswordSaved request = new SitePasswordSaved();
        request.setEmail("house345@gmail.com");
        request.setUrl("www.facebook.com");
        request.setUsername("Danielson45");
        request.setPassword("Deacrest567@");

//       SiteResponse response= passwordManagerService.userAddDifferentPassword(request);

        assertThrows(UserIsNotLogInExeception.class, () -> passwordManagerService.userAddPasswordToListOfPassword(request));
//        assertThat(response.getResponse(), is("user did not login"));

    }

    private void getUserRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUniquePassword("Teast!2349");
        userRequest.setEmail("house345@gmail.com");
        userRequest.setFirstName("goat");
        userRequest.setLastName("dele");
        passwordManagerService.createAccount(userRequest);

        assertThat(userRequest.getUniquePassword(), is("Teast!2349"));
        assertThat(userRequest.getEmail(), is("house345@gmail.com"));
        assertThat(userRequest.getFirstName(), is("goat"));
        assertThat(userRequest.getLastName(), is("dele"));


        UserRequest userRequest1 = new UserRequest();
        userRequest1.setUniquePassword("cEase@2349");
        userRequest1.setEmail("zeus7865@yahoo.com");
        userRequest1.setFirstName("fashola");
        userRequest1.setLastName("folashile");
        passwordManagerService.createAccount(userRequest1);

    }

    @Test
    void testThatIfPassWordBeLessThan10Characters_throwsException() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("house345@gmail.com");
        userRequest.setUniquePassword("teast234");
        assertThrows(InvalidPassWordException.class, () -> passwordManagerService.createAccount(userRequest));
    }

    @Test
    void testThatUserPasswordCanSavePasswordToListOfPassword() {
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

        SiteResponse siteResponse = passwordManagerService.savedUserCanSavePasswordOfUrl(request);
        //save password for this url site
        //assert that the password exist
        assertThat(request.getUrl(), is("www.facebook.com"));
        assertThat(request.getUsername(), is("Danielson45"));
        assertThat(request.getPassword(), is("Deacrest567@"));
        assertThat(siteResponse.getResponse(), is("your password was saved successfully"));


    }

//    @Test
//    void testThatRegisterUserCanLogInToWhatappsWithAnotherPasswordAndUserName() {
//        UserRequest userRequest1 = new UserRequest();
//        userRequest1.setUniquePassword("cEase@2349");
//        userRequest1.setEmail("zeus7865@yahoo.com");
//        userRequest1.setFirstName("fashola");
//        userRequest1.setLastName("folashile");
//        passwordManagerService.createAccount(userRequest1);
//
//        SitePasswordSaved request = new SitePasswordSaved();
//        request.setEmail("zeus7865@yahoo.com");
//        request.setUrl("www.Whatapps.com");
//        request.setUsername("killIt1234");
//        request.setPassword("StormTheyard!123");
//        SiteResponse siteResponse = passwordManagerService.savedUserCanLogInToWhatAppsWithAnotherPasswordAndUsername(request);
//        //save password for this url site
//        //assert that the password exist
//        assertThat(request.getUrl(), is("www.Whatapps.com"));
//        assertThat(request.getUsername(), is("killIt1234"));
//        assertThat(request.getPassword(), is("StormTheyard!123"));
//        assertThat(siteResponse.getResponse(), is("your password was saved successfully"));
//    }

    @Test
    void testUserCanUpdatePassword() {

        UserRequest userRequest1 = new UserRequest();
        userRequest1.setUniquePassword("cEase@2349");
        userRequest1.setEmail("zeus7865@yahoo.com");
        userRequest1.setFirstName("fashola");
        userRequest1.setLastName("folashile");
        passwordManagerService.createAccount(userRequest1);

        User user = passwordManagerService.userCanUpdateTheirPassword(userRequest1, "deThrate345@");
        assertThat(user.getPassword(), is("deThrate345@"));
        assertThat(user.getEmail(), is("zeus7865@yahoo.com"));
        assertThat(user.getFirstName(),is("fashola"));
        assertThat(user.getLastName(),is("folashile"));
    }

    @Test
    void testThatUserCanBeDeleted() {

        //given that we have a user
        getUserRequest();

        SiteResponse userDelete = passwordManagerService.DeleteAuser("zeus7865@yahoo.com");
        assertThat(userDelete.getResponse(),is("user deleted successfully"));

    }
    @Test
    void testThatUserCanBeFindFirstNameAndLastName() {
        getUserRequest();
        UserResponse userResponse = passwordManagerService.userCanBeFindByLastNameAndFirstName("goat","dele");

        assertThat(userResponse.getEmail(),is("house345@gmail.com"));
        assertThat(userResponse.getFullName(),is("goat"+ " "+ "dele"));
        assertThat(userResponse.getPassword(),is("Teast!2349"));
    }
    @Test
    void testThatUserPasswordCanUpdatePasswordToListOfPassword(){
        getUserRequest();

        SitePasswordSaved request = new SitePasswordSaved();
        request.setUsername("killIt1234");
        request.setEmail("zeus7865@yahoo.com");
        request.setUrl("www.Whatapps.com");
        request.setPassword("Rainbow5645!");
//        userListOfPasswordService.createPasswordAccount(request);
        SiteResponse siteResponse = passwordManagerService.userCanUpdatePasswordInListOfPassword(request,"Rainbow5645!");

        assertThat(siteResponse.getResponse(),is("your password list was successfully update"));

    }
}
