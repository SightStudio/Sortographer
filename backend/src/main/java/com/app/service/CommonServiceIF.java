package com.app.service;

import com.app.dto.ResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface CommonServiceIF {
    ResponseDTO imgTempSave(MultipartFile[] files);
}
