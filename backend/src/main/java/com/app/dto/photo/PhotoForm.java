package com.app.dto.photo;

import com.common.util.aws.s3.S3Path;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PhotoForm {
    private String  title;
    private S3Path  s3Path;
    private String  isPrivate;
    private List<String> labelList;
}
