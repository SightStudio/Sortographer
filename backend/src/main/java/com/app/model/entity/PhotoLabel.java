package com.app.model.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class PhotoLabel {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false)
    private String label;

    private LocalDateTime regTime;

    @ManyToOne
    @JoinColumn(name = "photo_id", foreignKey = @ForeignKey(name = "PHOTO_ID_FK"), nullable = false)
    private Photo photo;

    public PhotoLabel() {}

    public PhotoLabel (Photo photo, String label) {
        this.photo   = photo;
        this.regTime = LocalDateTime.now();
        this.label   = label;
    }
}
