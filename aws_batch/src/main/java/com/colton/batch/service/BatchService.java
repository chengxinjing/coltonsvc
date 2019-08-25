package com.colton.batch.service;

import com.colton.batch.domain.BatchResponse;
import com.colton.batch.domain.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class BatchService {

    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    JobLauncher jobLauncher;
    private Map<String, JobParameter> map = new LinkedHashMap<>();

    public BatchResponse doUserInfoBatch(String jobName) {
        Job job = applicationContext.getBean(jobName, Job.class);
        BatchResponse response = null;
        try {
            response = new BatchResponse();
            map.put(jobName, new JobParameter(UUID.randomUUID().toString()));
            JobParameters parameters = new JobParameters(map);
            JobExecution execution = jobLauncher.run(job, parameters);

            response.setDate(new Date());
            if (execution.getExitStatus() == ExitStatus.FAILED) {
                response.setStatus(Status.FAIL);
            } else if (execution.getExitStatus() == ExitStatus.EXECUTING) {
                response.setStatus(Status.RUNNING);
            } else {
                response.setStatus(Status.SUCCESS);
            }

        } catch (Exception e) {
            log.info(e.getMessage(), e);
        } finally {
            map.clear();
        }
        return response;
    }
}
