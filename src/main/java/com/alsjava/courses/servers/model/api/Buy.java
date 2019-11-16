package com.alsjava.courses.servers.model.api;

import com.alsjava.courses.servers.domain.control.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by aluis on 11/9/19.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Buy {

    private Product product;
    private int quantity;
    private double price;
}
