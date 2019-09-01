package com.colton.entity.repository;

import com.colton.entity.user.UserInfo;
import org.springframework.data.gemfire.mapping.annotation.Region;
import org.springframework.data.gemfire.repository.GemfireRepository;
import org.springframework.stereotype.Repository;

@Repository
@Region("/User")
public interface UserInfoRepository extends GemfireRepository<UserInfo,String> {
}
