package com.app.service;

import com.app.dto.ResponseDTO;
import com.app.dto.S3Path;

public interface PhotoServiceIF {
    ResponseDTO getPhotoLabel(S3Path s3Path);
}
