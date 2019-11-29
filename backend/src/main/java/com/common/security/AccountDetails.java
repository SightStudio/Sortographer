package com.common.security;

import com.app.model.entity.Account;
import com.app.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountDetails implements UserDetailsService {

    private final AccountRepository accountRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final Account account = accountRepo.findAccountByEmail(username);

        if(account == null)
            throw new UsernameNotFoundException("User Eamil : '" + username + "' not found");

        return new SecurityUser(account);
    }
}
