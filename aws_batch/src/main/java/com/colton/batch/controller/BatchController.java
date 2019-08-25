package com.colton.batch.controller;

import com.colton.batch.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("batch")
public class BatchController {
    @Autowired
    private BatchService batchService;
    public String doUserInfoBatch(String jobName){
        batchService.doUserInfoBatch(jobName);
        return "batch successfully";
    }
}
