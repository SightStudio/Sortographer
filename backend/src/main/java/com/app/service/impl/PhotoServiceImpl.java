package com.app.service.impl;

import com.amazonaws.services.rekognition.model.Label;
import com.app.dto.ResponseDTO;
import com.app.dto.S3Path;
import com.app.service.PhotoServiceIF;
import com.common.util.aws.rekognition.RekognitionMo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoServiceIF {

    private final RekognitionMo rekognitionMo;

    @Override
    public ResponseDTO getPhotoLabel(S3Path s3Path) {

        ResponseDTO repl;

        List<Label> labelList = rekognitionMo.getLabelDetectionFromS3(s3Path.getBucketPath());

        repl = new ResponseDTO("이미지 분석 완료", HttpStatus.OK, true);
        repl.addData("labelList", labelList);

        return repl;
    }
}
