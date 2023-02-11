package com.example.aws.demo.repository;

import com.amazonaws.services.s3.AmazonS3;
import com.example.aws.demo.repository.exception.ObjectNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class S3BucketRepository {

    @Value("${cloud.aws.s3.bucket-name}")
    private String s3BucketName;

    @Autowired
    private AmazonS3 amazonS3;

    public void healthCheck() throws ObjectNotExistException {
        if(amazonS3.doesBucketExist(s3BucketName)){
            log.info("bucket exist true");
        }else{
            log.error("bucket not exists");
            throw new ObjectNotExistException("s3 bucket des not exist");
        }
    }
}
