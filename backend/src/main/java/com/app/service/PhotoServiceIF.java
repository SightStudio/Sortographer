package com.app.service;

import com.app.dto.ResponseDTO;
import com.common.util.aws.s3.S3Path;
import com.app.dto.photo.PhotoForm;
import org.springframework.security.core.userdetails.UserDetails;

public interface PhotoServiceIF {

    // 사진 목록 조회
    ResponseDTO getPhtotoList(int page, int limit);

    // 사진 목록 조회
    ResponseDTO getPhotoLabel(S3Path s3Path);

    // 사진 분류 목록 전체 조회
    ResponseDTO getDistinctPhotoLabelList();

    // 사진 등록
    ResponseDTO registerPhtoto(UserDetails userDetails, PhotoForm photoForm);
}
