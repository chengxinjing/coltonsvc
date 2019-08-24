package com.colton.controller;

import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.colton.service.AwsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;

@Api("aws Controller")
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

    @ApiOperation("create bucket on S3")
    @ApiImplicitParam(name = "bucketName", value = "bucketName", paramType = "form", dataType = "String")
    @PostMapping(value = "createBucket")
    public String createBucket(@RequestParam("bucketName") String bucketName) {
        log.info(bucketName);
        awsService.createBucket(bucketName);
        return format("createBucket %s successfully", bucketName);
    }

    @ApiOperation("delete bucket on S3")
    @ApiImplicitParam(name = "bucketName", paramType = "form", value = "bucketName", dataType = "String")
    @DeleteMapping(value = "deleteBucket")
    public String deleteBucket(@RequestParam("bucketName") String bucketName) {
        log.info("delete the bucket:{}", bucketName);
        awsService.deleteBucket(bucketName);
        return format("delete %s successfully", bucketName);
    }

    @ApiOperation("download file from aws S3")
    @ApiImplicitParam(name = "request", value = "bucketName", required = true, dataType = "AwsRequestEntity")
    @PostMapping("download")
    public String download(@RequestBody AwsRequestEntity request) throws MalformedURLException {

        awsService.download(request.getFileName(), request.getBucketName());
        return format("download %s file successfully", request.getFileName());

    }

    @ApiOperation("list S3Object on S3")
    @ApiImplicitParam(name = "bucketName", value = "bucketName", paramType = "query", dataType = "String")
    @GetMapping(value = "files")
    public ObjectListing listObjects(@RequestParam("bucketName") String bucketName) {
        return awsService.listObjects(bucketName);
    }

    @ApiOperation("delete file from aws S3")
    @ApiImplicitParam(name = "request", dataType = "AwsRequestEntity", required = true)
    @DeleteMapping("deleteObject")
    public String deleteObject(@RequestBody AwsRequestEntity request) {
        awsService.deleteObject(request.getBucketName(), request.getFileName());
        return format("delete %s successfully in %s",  request.getFileName(),request.getBucketName());
    }

    private String format(String format, String... args) {
        return String.format(format, args);
    }
}

