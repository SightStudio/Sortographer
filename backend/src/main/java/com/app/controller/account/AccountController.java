package com.app.controller.account;

import com.app.dto.ResponseDTO;
import com.app.dto.SigninForm;
import com.app.dto.SignupForm;
import com.app.service.AccountServiceIF;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {

    private final AccountServiceIF accountService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO> signup(@RequestBody SignupForm signupForm) {
        ResponseDTO repl = accountService.signup(signupForm);
        return new ResponseEntity<>(repl, repl.getHttpStatus());
    }

//    @PostMapping("/signin")
////    public ResponseEntity<ResponseDTO> signin(@RequestParam String email, @RequestParam String pw) {
////        ResponseDTO repl = accountService.signin(new SigninForm(email, pw));
////        return new ResponseEntity<>(repl, repl.getHttpStatus());
////    }
}
