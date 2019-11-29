package com.app.repository.photoLabel;

import com.app.model.entity.PhotoLabel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoLabelRepository extends JpaRepository<PhotoLabel, Long>,
                                              PhotoLabelRepositoryCustom {
}
