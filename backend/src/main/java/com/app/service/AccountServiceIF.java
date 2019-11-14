package com.app.service;

import com.app.dto.ResponseDTO;
import com.app.dto.SigninForm;
import com.app.dto.SignupForm;

public interface AccountServiceIF {
    ResponseDTO signup(SignupForm signupForm);
}
