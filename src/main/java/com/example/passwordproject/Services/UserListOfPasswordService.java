package com.example.passwordproject.Services;

import com.example.passwordproject.data.models.UserListOfPassword;
import com.example.passwordproject.dtos.request.SitePasswordSaved;
import com.example.passwordproject.dtos.request.UpdateUserListOfPasswordRequest;
import com.example.passwordproject.dtos.response.SiteResponse;
import com.example.passwordproject.dtos.response.UpdateUserListOfPasswordResponse;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserListOfPasswordService {
   SiteResponse createPasswordAccount(SitePasswordSaved request);

   String findPasswordByUrl(SitePasswordSaved request);

   List<UpdateUserListOfPasswordResponse> updatePassword(UpdateUserListOfPasswordRequest updateForm);
}
