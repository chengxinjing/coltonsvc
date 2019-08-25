package com.colton.batch.domain;

import lombok.Data;

import java.util.Date;
@Data
public class BatchResponse {
    private Status  status;
    private Date date;
}
