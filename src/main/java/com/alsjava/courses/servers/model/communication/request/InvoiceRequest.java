package com.alsjava.courses.servers.model.communication.request;

import com.alsjava.courses.servers.model.api.Buy;
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
public class InvoiceRequest extends Request {

    private List<Buy> buys;

    private double total;
}
