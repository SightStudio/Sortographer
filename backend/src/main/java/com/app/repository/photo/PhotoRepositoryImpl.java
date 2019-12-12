package com.app.repository.photo;

import com.app.dto.photo.PhotoInfoDTO;
import com.app.model.entity.Photo;
import com.app.model.entity.QAccount;
import com.app.model.entity.QPhoto;
import com.app.model.entity.QPhotoLabel;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PhotoRepositoryImpl extends    QuerydslRepositorySupport
                                 implements PhotoRepositoryCustom    {

    private final JPAQueryFactory queryFactory;

    public PhotoRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Photo.class);
        this.queryFactory = queryFactory;
    }

    /**
     * 이미지, 이미지 라벨 조회 SQL
     * @author Dong-Min Seol
     * @since  2019.11.27
     */
    public List<PhotoInfoDTO> findWithPhotoLabel(int page, int limit) {
        QPhoto photo = QPhoto.photo;
        QPhotoLabel photoLabel = QPhotoLabel.photoLabel;

        StringTemplate groupConcatStr = Expressions.stringTemplate("group_concat({0}, ' ')", photoLabel.label);

        return queryFactory
                .select(
                        Projections.constructor(PhotoInfoDTO.class,
                                photo.id,  photo.title, photo.absolutePath, groupConcatStr, photo.regTime)
                )
                .from(photo)
                    .innerJoin(photoLabel)
                        .on(photoLabel.photo.eq(photo))
                .where(photo.isPrivate.eq(false))
                .groupBy(photo.id)
                .offset((page-1)*limit)
                .limit(page*limit)
                .fetch();
    }

    /**
     * 해당 이미지 라벨을 가지고 있는 이미지 조회
     * @author Dong-Min Seol
     * @since  2019.12.07
     */
    public List<PhotoInfoDTO> findWithPhotoLabel(int page, int limit, String label) {

        QPhoto photo = QPhoto.photo;
        QPhotoLabel photoLabel = QPhotoLabel.photoLabel;

        StringTemplate groupConcatStr = Expressions.stringTemplate("group_concat({0}, ' ')", photoLabel.label);
        return queryFactory
                .select(
                        Projections.constructor(PhotoInfoDTO.class,
                                photo.id,  photo.title, photo.absolutePath, groupConcatStr, photo.regTime)
                )
                .from(photo)
                    .innerJoin(photoLabel)
                        .on(photoLabel.photo.eq(photo))
                .where(photo.isPrivate.eq(false))
                .groupBy(photo.id)
                .having(groupConcatStr.like("%"+label+"%"))
                .offset((page-1)*limit)
                .limit(page*limit)
                .fetch();
    }

    @Override
    public List<PhotoInfoDTO> findWithUserPhotoLabel(int page, int limit, String email) {
        QAccount      account  = QAccount.account;
        QPhoto          photo  = QPhoto.photo;
        QPhotoLabel photoLabel = QPhotoLabel.photoLabel;

        StringTemplate groupConcatStr = Expressions.stringTemplate("group_concat({0}, ' ')", photoLabel.label);
        return queryFactory
                .select(
                        Projections.constructor(PhotoInfoDTO.class,
                                photo.id,  photo.title, photo.absolutePath, groupConcatStr, photo.regTime)
                )
                .from(photo)
                    .innerJoin(photoLabel)
                        .on(photoLabel.photo.eq(photo))
                    .innerJoin(account)
                        .on(photo.account.eq(account))
                .where(account.email.eq(email))
                .groupBy(photo.id)
                .offset((page-1)*limit)
                .limit(page*limit)
                .fetch();
    }
}
