package com.example.passwordproject.data.repository;

import com.example.passwordproject.data.models.UserListOfPassword;

import org.apache.logging.log4j.util.Strings;
import org.springframework.data.mongodb.repository.MongoRepository;


import org.springframework.stereotype.Service;

@Service
public interface ListOfUserPasswordRepository extends MongoRepository<UserListOfPassword, Strings> {
    UserListOfPassword findUserListOfPasswordByUrl(String url);
//
//    ListOfUserPasswordRepository createAccountPassword(SitePasswordSaved request);
}
