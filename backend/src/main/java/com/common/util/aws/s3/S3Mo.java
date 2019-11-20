package com.common.util.aws.s3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.app.dto.S3Path;
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

    public String deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        s3client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
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