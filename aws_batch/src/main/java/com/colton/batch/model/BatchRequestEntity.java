package com.colton.batch.model;

import lombok.Data;
@Data
public class BatchRequestEntity {
    private String jobName;
    private String fileDate;
    private boolean forceDownload;
    private String bucketName;
}
