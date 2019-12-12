package com.app.controller;

import com.app.dto.ResponseDTO;
import com.app.service.CommonServiceIF;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommonController {

    private final CommonServiceIF  commonService;

    @PostMapping("/img/temp")
    public ResponseDTO handleFileUpload(@RequestParam("file") MultipartFile[] uploadingFiles) throws IOException {
        return commonService.imgTempSave(uploadingFiles);
    }
}
