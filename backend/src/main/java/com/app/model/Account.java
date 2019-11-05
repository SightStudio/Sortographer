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
    private String pw;
    private LocalDateTime regTime;

    @Builder
    public Account(String email, String name, String pw) {
        this.email   = email;
        this.name    = name;
        this.pw      = pw;
        this.regTime = LocalDateTime.now();
    }
}
