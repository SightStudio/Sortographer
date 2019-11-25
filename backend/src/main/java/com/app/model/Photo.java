package com.app.model;

import com.common.util.aws.s3.S3Path;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Photo {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 200)
    private String absolutePath;

    @Column(length = 200)
    private String bucketPath;

    @Column(length = 100)
    private String fileName;

    private LocalDateTime regTime;

    @ManyToOne
    @JoinColumn(name = "account_id", foreignKey = @ForeignKey(name = "Account_ID_FK"))
    private Account account;


    public Photo(Account account, String title) {
        this.title    = title;
        this.account  = account;
        this.regTime  = LocalDateTime.now();
    }

    public void setPath(S3Path s3Path) {
        this.fileName     = s3Path.getFileName();
        this.absolutePath = s3Path.getAbsolutePath();
        this.bucketPath   = s3Path.getBucketPath();
    }
}
