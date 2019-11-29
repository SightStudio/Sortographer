package com.app.dto.photo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PhotoInfoDTO {

    private Long                id;
    private String           title;
    private String    absolutePath;
    private String[]    photoLabel;
    private LocalDateTime  regTime;

    public PhotoInfoDTO(Long id, String title, String absolutePath, String photoLabel, LocalDateTime regTime) {
        this.id = id;
        this.title = title;
        this.absolutePath = absolutePath;
        this.regTime = regTime;
        this.photoLabel = photoLabel.split(",");
    }
}
