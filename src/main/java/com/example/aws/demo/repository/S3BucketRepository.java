package com.example.aws.demo.repository;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.example.aws.demo.repository.exception.ObjectNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public void uploadFilesFromDirectory(String fileLocation) {
        File folder = new File(fileLocation);
        List<File> filesToUpload = new ArrayList<>();
        if(folder.isDirectory()){
            for(File file :folder.listFiles()){
                log.info("adding file: "+file.getName());
                filesToUpload.add(file);
            }
        }
        log.info("files size: "+filesToUpload.size());
        for(File file : filesToUpload){
            uploadFile(file);
        }
    }

    private void uploadFile(File file) {
        amazonS3.putObject(s3BucketName, file.getName(),file);
        file.delete();
    }

    public void downloadFiles(String fileDownloadLocation, String fileDownloadObject) throws IOException {
        S3Object s3Object = amazonS3.getObject(s3BucketName,fileDownloadObject);
        S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();
        FileUtils.copyInputStreamToFile(s3ObjectInputStream, new File(fileDownloadLocation+File.separator+fileDownloadObject));
    }
}
