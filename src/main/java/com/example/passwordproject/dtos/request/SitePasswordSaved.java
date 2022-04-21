package com.example.passwordproject.dtos.request;
@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class SitePasswordSaved {
    private String username;
    private String password;
    private String url;
  private String email;
}
