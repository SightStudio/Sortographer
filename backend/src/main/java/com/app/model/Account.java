package com.app.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private String name;
    private String password;
    private LocalDateTime regTime;

    @Builder
    public Account(String email, String name, String password) {
        this.email    = email;
        this.name     = name;
        this.password = password;
        this.regTime  = LocalDateTime.now();
    }
}
