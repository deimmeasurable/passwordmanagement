package com.example.passwordproject.data.repository;

import com.example.passwordproject.data.models.UserListOfPassword;
import com.example.passwordproject.dtos.request.SitePasswordSaved;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public interface ListOfUserPasswordRepository extends MongoRepository<UserListOfPassword, Strings> {
//    UserListOfPassword findUserListOfPasswordByUrl(String website);
//
//    ListOfUserPasswordRepository createAccountPassword(SitePasswordSaved request);
}
