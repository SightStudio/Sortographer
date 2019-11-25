package com.common.util.aws.s3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class S3Path {
    private String absolutePath;
    private String bucketPath;
    private String fileName;
}
