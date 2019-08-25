package com.colton.controller;

import lombok.Data;

@Data
public class AwsRequestEntity {
    private String bucketName;
    private String fileName;
}
