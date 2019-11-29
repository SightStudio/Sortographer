package com.app.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
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
