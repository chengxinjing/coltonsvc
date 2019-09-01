package com.colton.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

@Service
@Slf4j
public class AwsService {

    @Autowired
    AmazonS3 amazonS3;

    @Value("${aws.bucket.url}")
    private String s3Url;

    @Value("${aws.region:ap-northeast-1}")
    private String region;

    @Value("${aws.download.home}")
    private String downloadHome;

    public Bucket createBucket(String bucketName) throws SdkClientException {
        return amazonS3.createBucket(bucketName);
    }

    public void deleteBucket(String bucketName) throws SdkClientException {
        amazonS3.deleteBucket(bucketName);
    }

    public PutObjectResult putObject(String bucketName, String key, InputStream inputStream, ObjectMetadata objectMetadata) {
        PutObjectRequest request = new PutObjectRequest(bucketName, key, inputStream, objectMetadata);
        request.withCannedAcl(CannedAccessControlList.PublicReadWrite);
        return amazonS3.putObject(request);
    }


    public byte[] download(String key, String bucketName) throws MalformedURLException {
        String actualUrl = formatUrl(key, bucketName);
        URL url = new URL(actualUrl + key);
        PresignedUrlDownloadRequest request = new PresignedUrlDownloadRequest(url);
        File destFile = new File(downloadHome + key);
        amazonS3.download(request, destFile);
        byte[] bytes = null;
        try {
            bytes = FileCopyUtils.copyToByteArray(destFile);
        } catch (IOException e) {
            log.warn(e.getMessage(),e);
        }
        return bytes;
    }

    private String formatUrl(String key, String bucketName) {
        s3Url = s3Url.replace("{bucketName}", bucketName);
        return s3Url.replace("{region}", region);
    }

    public PutObjectResult putObject(String bucketName, MultipartFile uploadFile) throws IOException {
        String fileName = uploadFile.getOriginalFilename();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(uploadFile.getBytes().length);
        objectMetadata.setContentType(uploadFile.getContentType());
        log.info("key is {}", fileName);
        return putObject(bucketName, fileName, uploadFile.getInputStream(), objectMetadata);
    }

    public ObjectListing listObjects(String bucketName) {
        ObjectListing objectListing = amazonS3.listObjects(bucketName);
        for (S3ObjectSummary summary : objectListing.getObjectSummaries()) {
            log.info(summary.getStorageClass());
        }
        return objectListing;
    }

    public void deleteObject(String bucketName, String key) {
        amazonS3.deleteObject(bucketName, key);
    }
}
