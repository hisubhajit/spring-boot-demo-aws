package com.example.aws.demo.controller;

import com.example.aws.demo.repository.exception.ObjectNotExistException;
import com.example.aws.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("bucket/health/status")
    public String checkHealth(){
        try{
            fileService.healthCheck();
            return "UP";
        } catch (ObjectNotExistException e) {
            return "Object Not Found";
        }
    }
}
