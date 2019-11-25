package com.common.util.aws.s3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class S3Mo {

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Value("${aws.s3.endpointUrl}")
    private String endpointUrl;

    @Value("${aws.s3.accessKey}")
    private String accessKey;

    @Value("${aws.s3.secretAccessKey}")
    private String secretKey;

    private AmazonS3 s3client;

    @PostConstruct
    private void initAmazonS3() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = AmazonS3ClientBuilder
                            .standard()
                            .withCredentials(new AWSStaticCredentialsProvider(credentials))
                            .withRegion(Regions.AP_NORTHEAST_2)
                            .build();
    }

    /**
     * AWS S3 파일 업로드 함수
     * @author DongMin-Seol
     * @since  2019.11.24
     */
    public S3Path uploadFile(MultipartFile multipartFile, String dirPath) {
        String fileUrl     = "";
        String abolutePath = "";
        String fileName    = "";

        try {
            File file = convertMultiPartToFile(multipartFile);
            fileName  = generateFileName(multipartFile);
            fileUrl = dirPath + "/" + fileName;
            uploadFileTos3bucket(fileUrl, file);

            abolutePath = endpointUrl + "/" + bucketName + "/" +fileUrl;
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new S3Path( abolutePath, fileUrl, fileName);
    }

    /**
     * AWS S3 동일 버킷 내 이동 함수
     * @author DongMin-Seol
     * @since  2019.11.24
     */
    public S3Path moveFile(S3Path s3PathFrom, String s3PathTo) {
        S3Path s3Path = new S3Path();

        CopyObjectRequest copyObjRequest = new CopyObjectRequest(bucketName, s3PathFrom.getBucketPath(), bucketName, s3PathTo)
                .withCannedAccessControlList(CannedAccessControlList.PublicRead);

        s3client.copyObject(copyObjRequest);
        String path = s3client.getUrl(bucketName, s3PathTo).getPath();

        s3Path.setFileName(s3PathFrom.getFileName());
        s3Path.setBucketPath(path);
        s3Path.setAbsolutePath(endpointUrl+path);

        this.deleteFileFromS3Bucket(s3PathFrom.getBucketPath());
        return s3Path;
    }


    /**
     * AWS S3 파일 삭제 함수
     * @author DongMin-Seol
     * @since  2019.11.24
     */
    public String deleteFileFromS3Bucket(String bucketPath) {
        s3client.deleteObject(new DeleteObjectRequest(bucketName, bucketPath));
        return "Successfully deleted";
    }

    // ------------------------------------------------------------------------
    //                       Private Operations
    // ------------------------------------------------------------------------
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return String.format("%s_%s", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_hh_mm_ss_SSSSS")),
                                      multiPart.getOriginalFilename().replaceAll(" ", "_"));
    }

    private void uploadFileTos3bucket(String filePath, File file) {
        s3client.putObject(new PutObjectRequest(bucketName, filePath, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }
}