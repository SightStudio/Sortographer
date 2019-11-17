package com.app.service.impl;

import com.app.model.Account;
import com.app.dto.ResponseDTO;
import com.app.dto.SignupForm;
import com.app.repository.AccountRepository;
import com.app.service.AccountServiceIF;
import com.common.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountServiceIF {

    private final PasswordEncoder       passwordEncoder;
    private final AccountRepository     accountRepo;

    @Override
    public ResponseDTO signup(SignupForm form) {
        ResponseDTO response;
        Account account = Account.builder()
                             .email(form.getEmail())
                             .name(form.getUsername())
                             .password(passwordEncoder.encode(form.getPassword()))
                             .build();

        accountRepo.save(account);

         response = new ResponseDTO("회원가입에 성공하였습니다.", HttpStatus.OK, false);
         return response;
    }
}
