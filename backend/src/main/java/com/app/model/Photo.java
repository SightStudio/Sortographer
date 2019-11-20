package com.app.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Photo {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime regTime;

    @ManyToOne
    @JoinColumn(name = "account_id", foreignKey = @ForeignKey(name = "Account_ID_FK"))
    private Account account;
}
