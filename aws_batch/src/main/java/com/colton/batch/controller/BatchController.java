package com.colton.batch.controller;

import com.colton.batch.domain.BatchResponse;
import com.colton.batch.model.BatchRequestEntity;
import com.colton.batch.service.BatchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("Batch Controller")
@Log4j2
@RestController
@RequestMapping("batch")
public class BatchController {
    @Autowired
    private BatchService batchService;

    @ApiOperation("run batch")
    @GetMapping("run")
    public BatchResponse doUserInfoBatch(@RequestBody BatchRequestEntity requestEntity) {
        log.info("job {} is going to run", requestEntity.getJobName());
        BatchResponse response = batchService.doUserInfoBatch(requestEntity);

        return response;
    }
}
