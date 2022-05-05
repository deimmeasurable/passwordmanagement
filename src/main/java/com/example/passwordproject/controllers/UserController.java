package com.example.passwordproject.controllers;

import com.example.passwordproject.Services.PasswordManagerService;
import com.example.passwordproject.dtos.request.UserRequest;
import com.example.passwordproject.dtos.response.ApiResponse;
import com.example.passwordproject.dtos.response.ApiResponseUpdatePassword;
import com.example.passwordproject.exception.UserAlreadyExistException;
import com.example.passwordproject.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private PasswordManagerService passwordManagerService;

    public UserController(PasswordManagerService passwordManagerService) {
        this.passwordManagerService = passwordManagerService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody UserRequest userRequest) {
        try {
            ApiResponse response = ApiResponse.builder()
                    .payLoad(passwordManagerService.createAccount(userRequest))
                    .message("User created successfully")
                    .isSuccessful(true)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (UserAlreadyExistException e) {
            ApiResponse response = ApiResponse.builder()
                    .message(e.getMessage())
                    .isSuccessful(false)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }

    }

    @GetMapping("/getUser/{firstName}/{lastName}")
    public ResponseEntity<?> userCanBeFindByLastNameAndFirstName(@PathVariable String firstName, @PathVariable String lastName) {
        try {
            ApiResponse response = ApiResponse.builder()
                    .payLoad(passwordManagerService.userCanBeFindByLastNameAndFirstName(firstName,lastName))
                    .message("user is found")
                    .isSuccessful(true)
                    .build();
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (UserNotFoundException e){
            ApiResponse response = ApiResponse.builder()
                    .message(e.getMessage())
                    .isSuccessful(false)
                    .build();
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/logIn/{email}/{password}")
    public ResponseEntity<?> userMustLogInBeforeTheyCanSavePassword(@PathVariable String email, @PathVariable String password){
        try{
            ApiResponse response = ApiResponse.builder()
                    .payLoadLogin(passwordManagerService.userMustLogInBeforeTheyCanSavePassword(email,password))
                    .message("user logIn successfully")
                    .isSuccessful(true)
                    .build();
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (UserNotFoundException e){
            ApiResponse response = ApiResponse.builder()
                    .message(e.getMessage())
                    .isSuccessful(true)
                    .build();
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping("/updatePassword/request/{password}")
    public ResponseEntity<?> userCanUpdateTheirPassword(@RequestBody UserRequest request, @PathVariable String password){
        try {
            ApiResponseUpdatePassword response =  ApiResponseUpdatePassword.builder()
                    .payLoad(passwordManagerService.userCanUpdateTheirPassword(request, password))
                    .message("password update successfully")
                    .isSuccessful(true)
                    .build();
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (UserNotFoundException e){
            ApiResponse response = ApiResponse.builder()
                    .message(e.getMessage())
                    .isSuccessful(false)
                    .build();
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

}