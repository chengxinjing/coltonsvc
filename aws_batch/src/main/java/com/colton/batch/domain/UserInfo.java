package com.colton.batch.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserInfo {
    private String name;
    private String address;
    private String email;
    private String nickName;
    private int age;
}
