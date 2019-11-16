package com.alsjava.courses.servers.model.communication.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by aluis on 11/9/19.
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvoiceResponse extends Response {

    private String serial;
    private Date saleDateTime;

    public InvoiceResponse(int code) {
        super(code);
    }
}
