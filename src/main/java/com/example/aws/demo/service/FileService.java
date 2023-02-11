package com.example.aws.demo.service;

import com.example.aws.demo.repository.S3BucketRepository;
import com.example.aws.demo.repository.exception.ObjectNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class FileService {

    @Value("${file.upload.location}")
    private String fileUploadLocation;
    @Value("${file.download.location}")
    private String fileDownloadLocation;
    @Value("${file.download.object}")
    private String fileDownloadObject;

    @Autowired
    private S3BucketRepository s3BucketRepository;

    public void healthCheck() throws ObjectNotExistException {
        s3BucketRepository.healthCheck();
    }

    public void uploadFile() {
        s3BucketRepository.uploadFilesFromDirectory(fileUploadLocation);
    }

    public void downloadFiles() throws IOException {
        s3BucketRepository.downloadFiles(fileDownloadLocation, fileDownloadObject);
    }
}
