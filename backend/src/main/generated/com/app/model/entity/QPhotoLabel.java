package com.app.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPhotoLabel is a Querydsl query type for PhotoLabel
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPhotoLabel extends EntityPathBase<PhotoLabel> {

    private static final long serialVersionUID = 460868526L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPhotoLabel photoLabel = new QPhotoLabel("photoLabel");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath label = createString("label");

    public final QPhoto photo;

    public final DateTimePath<java.time.LocalDateTime> regTime = createDateTime("regTime", java.time.LocalDateTime.class);

    public QPhotoLabel(String variable) {
        this(PhotoLabel.class, forVariable(variable), INITS);
    }

    public QPhotoLabel(Path<? extends PhotoLabel> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPhotoLabel(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPhotoLabel(PathMetadata metadata, PathInits inits) {
        this(PhotoLabel.class, metadata, inits);
    }

    public QPhotoLabel(Class<? extends PhotoLabel> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.photo = inits.isInitialized("photo") ? new QPhoto(forProperty("photo"), inits.get("photo")) : null;
    }

}

