package com.colton.batch.writer;

import com.colton.batch.domain.UserInfo;
import com.colton.batch.repository.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class UserInfoWriter implements ItemWriter<UserInfo> {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Override
    public void write(List<? extends UserInfo> userInfos) throws Exception {
      userInfos.stream().forEach(userInfo->{
          log.info(((UserInfo) userInfo).toString());
          userInfoRepository.save(userInfo);
      });
    }
}
