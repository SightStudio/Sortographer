package com.api.controller.photo;

import com.app.dto.ResponseDTO;
import com.app.dto.photo.PhotoForm;
import com.app.service.PhotoServiceIF;
import com.common.util.aws.s3.S3Path;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/external/photo")
@RequiredArgsConstructor
public class PhotoApiController {

    private final PhotoServiceIF photoService;

    @GetMapping("/list/{page}/{limit}")
    public ResponseEntity<ResponseDTO> getPhotoList(@PathVariable int page, @PathVariable int limit,
                                                    @RequestParam(value="label", required = false, defaultValue = "") String label) {

        ResponseDTO repl = photoService.getPhtotoList(page, limit, label);
        return new ResponseEntity<>(repl, repl.getHttpStatus());
    }

    @GetMapping("/label/all")
    public ResponseEntity<ResponseDTO> getDistinctPhotoLabelList() {
        ResponseDTO repl = photoService.getDistinctPhotoLabelList();
        return new ResponseEntity<>(repl, repl.getHttpStatus());
    }

    @PostMapping("/rekognition")
    public ResponseEntity<ResponseDTO> getPhotoRekognition(@RequestBody S3Path s3Path) {
        ResponseDTO repl = photoService.getPhotoLabel(s3Path);
        return new ResponseEntity<>(repl, repl.getHttpStatus());
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerPhoto(Authentication auth, @RequestBody PhotoForm photoForm) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        ResponseDTO repl = photoService.registerPhtoto(userDetails, photoForm);
        return new ResponseEntity<>(repl, repl.getHttpStatus());
    }
}
