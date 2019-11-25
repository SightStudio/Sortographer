package com.app.repository;

import com.app.model.PhotoLabel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoLabelRepository extends JpaRepository<PhotoLabel, Long> { }
