package com.app.repository.photo;

import com.app.model.Photo;
import com.app.model.QPhoto;
import com.app.model.QPhotoLabel;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.SimpleTemplate;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.sql.SQLExpressions;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PhotoRepositorySupport extends QuerydslRepositorySupport {

   private final JPAQueryFactory queryFactory;

    public PhotoRepositorySupport(JPAQueryFactory queryFactory) {
        super(Photo.class);
        this.queryFactory = queryFactory;
    }

    public List<Tuple> findWithPhotoLabel(int page, int limit) {
        QPhoto           photo = QPhoto.photo;
        QPhotoLabel photoLabel = QPhotoLabel.photoLabel;

        StringTemplate simpleTemplate = Expressions.stringTemplate("group_concat({0}, ' ')", photoLabel.label);

        return queryFactory
            .select(
                    photo.id,
                    photo.absolutePath,
                    simpleTemplate
            )
            .from(photo)
                .innerJoin(photoLabel)
                    .on(photoLabel.photo.eq(photo))
            .groupBy(photo.id)
            .limit(limit)
        .fetch();
    }
}
