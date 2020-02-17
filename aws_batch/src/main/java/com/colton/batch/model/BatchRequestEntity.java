package com.colton.batch.model;

import org.springframework.web.bind.annotation.RequestBody;

public class BatchRequestEntity {
    private String jobName;
    private String fileDate;
    private boolean forceDownload;
    private String bucketName;


    public String getJobName() {
        return jobName;
    }

    public void setJobName(@RequestBody String jobName) {
        this.jobName = jobName;
    }

    public String getFileDate() {
        return fileDate;
    }

    public void setFileDate(@RequestBody(required=false) String fileDate) {
        this.fileDate = fileDate;
    }

    public boolean isForceDownload() {
        return forceDownload;
    }

    public void setForceDownload(@RequestBody(required = false) boolean forceDownload) {
        this.forceDownload = forceDownload;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(@RequestBody String bucketName) {
        this.bucketName = bucketName;
    }
}
