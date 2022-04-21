package com.example.passwordproject.data.repository;

import com.example.passwordproject.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserPassWordRepository extends MongoRepository<User,String> {
User findUserByEmail(String email);
User findUserByLastName(String lastName);
User findUserByFirstName(String firstName);
User findUserByFirstNameAndLastName(String firstName, String lastName);


}
