package com.app.service.impl;

import com.amazonaws.services.rekognition.model.Label;
import com.app.dto.ResponseDTO;
import com.app.dto.photo.PhotoInfoDTO;
import com.app.model.entity.PhotoLabel;
import com.common.util.aws.s3.S3Mo;
import com.common.util.aws.s3.S3Path;
import com.app.dto.photo.PhotoForm;
import com.app.model.entity.Account;
import com.app.model.entity.Photo;
import com.app.repository.photoLabel.PhotoLabelRepository;
import com.app.repository.photo.PhotoRepository;
import com.app.service.PhotoServiceIF;
import com.common.security.SecurityUser;
import com.common.util.aws.rekognition.RekognitionMo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoServiceIF {

    private final S3Mo                 s3Mo;
    private final RekognitionMo        rekognitionMo;
    private final PhotoRepository      photoRepo;
    private final PhotoLabelRepository photoLabelRepo;

    @Override
    public ResponseDTO getPhtotoList(int page, int limit) {
        ResponseDTO repl;

        List<PhotoInfoDTO> PhotoList = photoRepo.findWithPhotoLabel(page, limit);
        repl = new ResponseDTO("이미지 조회 완료", HttpStatus.OK, true);
        repl.addData("photoList", PhotoList);
        return repl;
    }

    @Override
    public ResponseDTO getPhotoLabel(S3Path s3Path) {
        ResponseDTO repl;
        List<Label> labelList = rekognitionMo.getLabelDetectionFromS3(s3Path.getBucketPath());

        repl = new ResponseDTO("이미지 분석 완료", HttpStatus.OK, true);
        repl.addData("labelList", labelList);

        return repl;
    }

    @Override
    public ResponseDTO getDistinctPhotoLabelList() {
        ResponseDTO repl;
        List<String> distinctLabelList = photoLabelRepo.getDistinctLabelList();

        repl = new ResponseDTO("이미지 분석 완료", HttpStatus.OK, true);
        repl.addData("distinctLabelList", distinctLabelList);
        return repl;
    }

    @Override
    public ResponseDTO registerPhtoto(UserDetails userDetails, PhotoForm photoForm) {
        ResponseDTO   repl;
        SecurityUser  user;
        Account    account;
        S3Path      s3Path;
        Photo        photo;

        user    = (SecurityUser) userDetails;
        account = user.getAccount();
        photo   = new Photo(account, photoForm.getTitle());

        // [1] 사진 이미지 저장
        photoRepo.save(photo);
        String imgPathTo = String.format(
                "photo/%d/%s", photo.getId(), photoForm.getS3Path().getFileName()
        );

        s3Path = s3Mo.moveFile(photoForm.getS3Path(), imgPathTo);
        photo.setPath(s3Path);

        // [2] 사진 이미지 경로 저장(업데이트)
        photoRepo.save(photo);

        // [2] 사진 이미지 분석 결과 저장
        photoForm.getLabelList().forEach( label ->
            photoLabelRepo.save(new PhotoLabel(photo, label))
        );

        repl = new ResponseDTO("이미지 저장 완료", HttpStatus.OK, true);
        return repl;
    }
}
