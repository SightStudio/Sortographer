package com.app.repository.photo;

import com.app.dto.photo.PhotoInfoDTO;

import java.util.List;

public interface PhotoRepositoryCustom {
    List<PhotoInfoDTO> findWithPhotoLabel(int page, int limit);
}
