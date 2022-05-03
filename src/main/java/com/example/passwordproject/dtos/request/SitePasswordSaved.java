package com.example.passwordproject.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SitePasswordSaved {
    private String username;
    private String password;
    private String url;
  private String email;
}
