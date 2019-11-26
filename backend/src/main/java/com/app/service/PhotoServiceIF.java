package com.app.service;

import com.app.dto.ResponseDTO;
import com.common.util.aws.s3.S3Path;
import com.app.dto.photo.PhotoForm;
import org.springframework.security.core.userdetails.UserDetails;

public interface PhotoServiceIF {

    ResponseDTO getPhtotoList(int page);

    ResponseDTO getPhotoLabel(S3Path s3Path);
    ResponseDTO registerPhtoto(UserDetails userDetails, PhotoForm photoForm);
}
