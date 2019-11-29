package com.app.repository.photoLabel;

import com.app.model.entity.PhotoLabel;
import com.app.model.entity.QPhotoLabel;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class PhotoLabelRepositoryImpl extends    QuerydslRepositorySupport
                                      implements PhotoLabelRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public PhotoLabelRepositoryImpl(JPAQueryFactory queryFactory) {
        super(PhotoLabel.class);
        this.queryFactory = queryFactory;
    }

    /**
     * 이미지 라벨 종류 조회 SQL
     * @author Dong-Min Seol
     * @since  2019.11.27
     */
    @Override
    public List<String> getDistinctLabelList() {
        QPhotoLabel photoLabel = QPhotoLabel.photoLabel;

        return queryFactory
                .selectDistinct(photoLabel.label)
                .from(photoLabel)
                .fetch();
    }
}
