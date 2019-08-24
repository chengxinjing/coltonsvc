package com.colton.controller;

import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.colton.service.AwsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;

@RestController
@RequestMapping("/aws")
@Slf4j
public class AwsController {

    @Autowired
    private AwsService awsService;

    @PostMapping("upload")
    public PutObjectResult upload(@RequestParam("uploadFile") MultipartFile uploadFile, @RequestParam("bucketName") String bucketName) throws IOException {
        return awsService.putObject(bucketName, uploadFile);
    }

    @GetMapping(value = "createBucket")
    public String createBucket(@RequestParam("bucketName") String bucketName) {
        log.info(bucketName);
        awsService.createBucket(bucketName);
        return format("createBucket %s successfully", bucketName);
    }

    @GetMapping(value = "deleteBucket")
    public String deleteBucket(@RequestParam("bucketName") String bucketName) {
        log.info("delete the bucket:{}", bucketName);
        awsService.deleteBucket(bucketName);
        return format("delete %s successfully", bucketName);
    }

    @GetMapping("download")
    public String download(@RequestParam("fileName") String key, @RequestParam("bucketName") String bucketName) throws MalformedURLException {
        awsService.download(key, bucketName);
        return format("download %s file successfully", key);

    }

    @GetMapping("files")
    public ObjectListing listObjects(@RequestParam("bucketName") String bucketName) {
        return awsService.listObjects(bucketName);
    }

    @GetMapping("deleteObject")
    public String deleteObject(@RequestParam("bucketName") String bucketName, @RequestParam("fileName") String fileName) {
        awsService.deleteObject(bucketName, fileName);
        return format("delete %s successfully in %s", fileName, bucketName);
    }

    private String format(String format, String... args) {
        return String.format(format, args);
    }
}

