package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class S3Path {
    private String absolutePath;
    private String bucketPath;
    private String fileName;
}
