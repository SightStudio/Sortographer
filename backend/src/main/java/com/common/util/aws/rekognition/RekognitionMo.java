package com.common.util.aws.rekognition;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class RekognitionMo {

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Value("${aws.rekognition.accessKey}")
    private String accessKey;

    @Value("${aws.rekognition.secretAccessKey}")
    private String secretKey;

    private AmazonRekognition rClient;

    @PostConstruct
    private void initAmazonRekognition() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.rClient = AmazonRekognitionClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.AP_NORTHEAST_2)
                .build();
    }

    public List<Label> getLabelDetectionFromS3(String s3Path) {
        DetectLabelsRequest req = new DetectLabelsRequest()
                .withImage(new Image().withS3Object(new S3Object().withName(s3Path).withBucket(bucketName)))
                .withMaxLabels(6).withMinConfidence(75F);

        DetectLabelsResult result = rClient.detectLabels(req);
        return result.getLabels();
    }
}
