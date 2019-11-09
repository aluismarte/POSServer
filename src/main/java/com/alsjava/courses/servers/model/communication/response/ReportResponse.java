package com.alsjava.courses.servers.model.communication.response;

import com.alsjava.courses.servers.model.Report;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by aluis on 11/9/19.
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportResponse extends Response {

    private Report report;

    public ReportResponse(int code) {
        super(code);
    }
}
