package com.app.service.impl;

import com.app.dto.ResponseDTO;
import com.app.dto.S3Path;
import com.app.service.CommonServiceIF;
import com.common.util.aws.s3.S3Mo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonServiceIF {

    private final S3Mo awsS3;

    @Override
    public ResponseDTO imgTempSave(MultipartFile[] files) {
        ResponseDTO repl;
        List<S3Path> tempUrlList = new ArrayList<>(files.length);

        for (MultipartFile mpf : files) {
            S3Path url = awsS3.uploadFile(mpf, "temp/img");
            tempUrlList.add(url);
        }

        repl = new ResponseDTO("임시저장 성공", HttpStatus.OK, true);
        repl.addData("tempUrlList", tempUrlList);
        return repl;
    }
}
