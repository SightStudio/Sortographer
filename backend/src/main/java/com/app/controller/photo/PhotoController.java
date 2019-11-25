package com.app.controller.photo;

import com.app.dto.ResponseDTO;
import com.common.util.aws.s3.S3Path;
import com.app.dto.photo.PhotoForm;
import com.app.service.PhotoServiceIF;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/photo")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoServiceIF photoServiceIF;

    @PostMapping("/rekognition")
    public ResponseEntity<ResponseDTO> getPhotoRekognition(@RequestBody S3Path s3Path) {
        ResponseDTO repl = photoServiceIF.getPhotoLabel(s3Path);
        return new ResponseEntity<>(repl, repl.getHttpStatus());
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerPhoto(Authentication auth, @RequestBody PhotoForm photoForm) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        ResponseDTO repl = photoServiceIF.registerPhtoto(userDetails, photoForm);
        return new ResponseEntity<>(repl, repl.getHttpStatus());
    }
}
