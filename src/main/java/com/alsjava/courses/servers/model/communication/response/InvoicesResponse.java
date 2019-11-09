package com.alsjava.courses.servers.model.communication.response;

import com.alsjava.courses.servers.domain.control.Invoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by aluis on 11/9/19.
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvoicesResponse extends Response {

    private List<Invoice> invoices;

    public InvoicesResponse(int code) {
        super(code);
    }
}
