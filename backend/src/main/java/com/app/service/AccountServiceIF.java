package com.app.service;

import com.app.dto.ResponseDTO;
import com.app.dto.SignupForm;
import org.springframework.security.core.userdetails.UserDetails;


public interface AccountServiceIF {
    ResponseDTO getAccountProfile(UserDetails userDetails);
    ResponseDTO signup(SignupForm signupForm);
}
