package com.colton.batch.loader;

import com.colton.entity.user.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class UserInfoLoader {

    private static final int NAME_INDEX = 0;
    private static final int ADDRESS_INDEX = 1;
    private static final int AGE_INDEX = 2;
    private static final int MAIL_INDEX = 3;
    private static final int NICKNAME_INDEX = 4;

    @Value("${batch.file.delimiter:,}")
    private String delimiter;

    public List<UserInfo> loadUserInfo(String path) {
        List<UserInfo> userInfos = new ArrayList<>();
        int lineCount = 0;
        BufferedReader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get(path));
            String header = reader.readLine();
            log.info("the file header is  {}", header);
            while (true) {
                String line = reader.readLine();
                lineCount++;
                if (StringUtils.isEmpty(line))
                    break;
                UserInfo userInfo = mapperLine(line);
                userInfos.add(userInfo);
                log.info("currently load {} userInfo",lineCount);
            }
        } catch (IOException e) {
            log.info("When load file :{}",path);
            log.warn(e.getMessage(),e);
        }

        return userInfos.isEmpty() ? Collections.emptyList() : userInfos;
    }

    private UserInfo mapperLine(String line) {
        String[] properties = line.split(delimiter);
        List<String> fields = trim(properties);
        UserInfo userInfo = new UserInfo();
        userInfo.setName(fields.get(NAME_INDEX));
        userInfo.setAddress(fields.get(ADDRESS_INDEX));
        userInfo.setAge(Integer.parseInt(fields.get(AGE_INDEX)));
        userInfo.setEmail(fields.get(MAIL_INDEX));
        userInfo.setNickName(fields.get(NICKNAME_INDEX));
        return userInfo;
    }

    private List<String> trim(String[] properties) {
        List<String> lines = new ArrayList<>();
        for (String property : properties) {
            lines.add(property.trim());
        }
        return lines.isEmpty() ? Collections.emptyList() : lines;
    }

}
