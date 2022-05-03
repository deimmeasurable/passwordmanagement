package com.example.passwordproject.Services;

import com.example.passwordproject.data.models.UserListOfPassword;
import com.example.passwordproject.dtos.request.SitePasswordSaved;
import org.springframework.stereotype.Service;

@Service
public interface UserListOfPasswordService {


    UserListOfPassword createPasswordAccount(SitePasswordSaved request);
}
