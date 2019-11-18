package com.app.controller.account;

import com.app.dto.ResponseDTO;
import com.app.dto.SignupForm;
import com.app.service.AccountServiceIF;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {

    private final AccountServiceIF accountService;

    @GetMapping("/me")
    public ResponseEntity<ResponseDTO> getUserProfole(Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        ResponseDTO repl = accountService.getAccountProfile(user);
        return new ResponseEntity<>(repl, repl.getHttpStatus());
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO> signup(@RequestBody SignupForm signupForm) {
        ResponseDTO repl = accountService.signup(signupForm);
        return new ResponseEntity<>(repl, repl.getHttpStatus());
    }
}
