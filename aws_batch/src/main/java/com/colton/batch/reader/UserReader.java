package com.colton.batch.reader;

import com.colton.batch.domain.UserInfo;
import com.colton.batch.loader.UserInfoLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
@Slf4j
public class UserReader implements ItemReader<UserInfo> {

    private ReentrantLock lock = new ReentrantLock();
    private Iterator<UserInfo> userInfoIterator ;
    @Autowired
    private UserInfoLoader userInfoLoader;

    @BeforeStep
    public void before(StepExecution stepExecution){
        String path = stepExecution.getJobExecution().getExecutionContext().getString("filePath");
        List<UserInfo> userInfos = userInfoLoader.loadUserInfo(path);
        userInfoIterator=userInfos.iterator();
    }

    @Override
    public UserInfo read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        try {
            lock.lock();
            if (userInfoIterator.hasNext())
                return userInfoIterator.next();
        }catch (Exception e){
            log.info("");
        }finally {
            lock.unlock();
        }

        return null;

    }
}
