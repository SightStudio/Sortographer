package com.app.repository.photo;

import com.app.model.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long>, PhotoRepositoryCustom { }
