package com.colton.batch.repository;

import com.colton.batch.domain.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends BatchRepository<String, UserInfo> {
}
