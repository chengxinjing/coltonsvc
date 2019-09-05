package com.colton.batch.writer;

import com.colton.entity.repository.UserInfoRepository;
import com.colton.entity.user.UserInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Log4j2
public class UserInfoWriter implements ItemWriter<UserInfo> {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Override
    public void write(List<? extends UserInfo> userInfos) throws Exception {
      userInfos.stream().forEach(userInfo->{
          log.info(((UserInfo) userInfo));
          userInfoRepository.save(userInfo);
      });
    }
}
