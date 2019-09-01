package com.colton.batch.service;

import com.colton.batch.domain.BatchResponse;
import com.colton.batch.domain.Status;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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


    RestTemplate restTemplate = new RestTemplate();

    @Value("${source.download.home}")
    private String downloadDest;
    @Value("${source.download.url}")
    private String dowloadUrl;

    @Value("${source.file}")
    private String fileName;


    private Map<String, JobParameter> map = new LinkedHashMap<>();

    public BatchResponse doUserInfoBatch(String jobName) {
        String feedName = findFeedFile(jobName);
        Job job = applicationContext.getBean(jobName, Job.class);
        BatchResponse response = null;
        try {
            response = new BatchResponse();
            map.put(jobName, new JobParameter(UUID.randomUUID().toString()));
            map.put("filePath",new JobParameter(feedName));
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

    private String findFeedFile(String jobName) {
        //TODO in future  finding the file based on jobName.
        String filePath = getFilePath(downloadDest, fileName);
        if (isExsit(filePath)) {
            return filePath;
        }
        downloadFile(filePath, dowloadUrl);
        return filePath;

    }

    private void downloadFile(String filePath, String dowloadUrl) {
        String extension = Files.getFileExtension(filePath);
        String nameWitoutExtension = Files.getNameWithoutExtension(filePath);
        String filename = nameWitoutExtension+"."+extension;
        String url = getUrl(filename, dowloadUrl);
       log.info("Create Url -> {}",url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity httpEntity = new HttpEntity(headers);
        try {
            ResponseEntity<byte[]> responseEntity = restTemplate.exchange(url,HttpMethod.POST,httpEntity,byte[].class);
            Files.write(responseEntity.getBody(),new File(filePath));
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }

    }

    private String getUrl(String filename, String dowloadUrl) {
        String token = Base64Utils.encodeToString(filename.getBytes());
        return dowloadUrl + "?fileName=" + filename + "&token=" + token;
    }

    private boolean isExsit(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return true;
        }
        return false;
    }

    private String getFilePath(String downloadDest, String fileName) {
        return downloadDest + File.separator + fileName.replace("{latestDate}", LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE));
    }
}
