package com.example.aws.demo.controller;

import com.example.aws.demo.repository.exception.ObjectNotExistException;
import com.example.aws.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/bucket/health/status")
    public String checkHealth(){
        try{
            fileService.healthCheck();
            return "UP";
        } catch (ObjectNotExistException e) {
            return "Object Not Found";
        }
    }

    @GetMapping("/bucket/upload/files")
    public String uploadFiles(){
        fileService.uploadFile();
        return "Done";
    }

    @GetMapping("/bucket/download/files")
    public String downloadFiles(){
        try{
            fileService.downloadFiles();
            return "Done";
        }catch (IOException e){
            return "Exception";
        }
    }
}
