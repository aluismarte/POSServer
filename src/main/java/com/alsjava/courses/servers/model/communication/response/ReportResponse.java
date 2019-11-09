package com.alsjava.courses.servers.model.communication.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by aluis on 11/9/19.
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class ReportResponse extends Response {

    public ReportResponse(int code) {
        super(code);
    }
}
