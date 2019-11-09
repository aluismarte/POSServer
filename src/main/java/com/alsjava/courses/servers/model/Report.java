package com.alsjava.courses.servers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by aluis on 11/9/19.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Report {

    private int invoiceQuantity;
    private double totalSales;
}
