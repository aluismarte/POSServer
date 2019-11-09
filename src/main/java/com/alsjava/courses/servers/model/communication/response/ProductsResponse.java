package com.alsjava.courses.servers.model.communication.response;

import com.alsjava.courses.servers.domain.control.Product;
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
public class ProductsResponse extends Response {

    private List<Product> products;

    public ProductsResponse(int code) {
        super(code);
    }
}
