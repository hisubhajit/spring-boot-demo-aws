package com.example.aws.demo.service;

import com.example.aws.demo.repository.S3BucketRepository;
import com.example.aws.demo.repository.exception.ObjectNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FileService {

    @Autowired
    private S3BucketRepository s3BucketRepository;

    public void healthCheck() throws ObjectNotExistException {
        s3BucketRepository.healthCheck();
    }
}
