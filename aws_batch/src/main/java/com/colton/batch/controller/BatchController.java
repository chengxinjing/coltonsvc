package com.colton.batch.controller;

import com.colton.batch.domain.BatchResponse;
import com.colton.batch.service.BatchService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("batch")
public class BatchController {
    @Autowired
    private BatchService batchService;

    @GetMapping("run")
    public BatchResponse doUserInfoBatch(@RequestParam("jobName") String jobName,@RequestParam(required = false,name = "fileDate")String fileDate,@RequestParam(required = false,name = "forceDownload") boolean forceDownload) {
        log.info("job {} is going to run", jobName);
        BatchResponse response = batchService.doUserInfoBatch(jobName,fileDate,forceDownload);

        return response;
    }
}
