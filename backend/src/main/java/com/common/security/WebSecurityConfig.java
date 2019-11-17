package com.common.security;

import com.common.handler.AuthFailerHandler;
import com.common.handler.AuthSuccessHandler;
import com.common.handler.HttpLogoutSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthSuccessHandler       authSuccessHandler;
    private final AuthFailerHandler        authFailerHandler;
    private final HttpLogoutSuccessHandler httpLogoutSuccessHandler;
    private final JwtTokenProvider         jwtTokenProvider;

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .requestMatchers(new AntPathRequestMatcher("/**.html"))
                .requestMatchers(new AntPathRequestMatcher("/templates/**"))
                .requestMatchers(new AntPathRequestMatcher("/static/**"));
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // [1] X-Frame-Options 비활성화
        http.headers().frameOptions().disable();

        // [2] CSRF 방지 비활성화
        http.csrf().disable();

        // [3] Spring Security가 세션을 생성하지 않음
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // [4] entry point
        http.authorizeRequests()
                .antMatchers("/api/account/**").hasRole("USER")
                .antMatchers("/api/signin").permitAll()
                .antMatchers("/api/signup").permitAll()
                .anyRequest().permitAll()
            .and()
                .formLogin()
                .loginProcessingUrl("/api/signin").permitAll()
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler(authSuccessHandler)
                .failureHandler(authFailerHandler)
            .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/api/logout"))
                .logoutSuccessHandler(httpLogoutSuccessHandler);

        // Apply JWT
        http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
    }
}
