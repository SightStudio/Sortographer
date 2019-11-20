package com.app.controller.photo;

import com.app.dto.ResponseDTO;
import com.app.dto.S3Path;
import com.app.dto.SignupForm;
import com.app.service.AccountServiceIF;
import com.app.service.PhotoServiceIF;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoServiceIF photoServiceIF;

    @PostMapping("/photo/rekognition")
    public ResponseEntity<ResponseDTO> getPhotoRekognition(@RequestBody S3Path s3Path) {
        ResponseDTO repl = photoServiceIF.getPhotoLabel(s3Path);
        return new ResponseEntity<>(repl, repl.getHttpStatus());
    }
}
