package com.alsjava.courses.servers.utils.bootstrap.control;

import com.alsjava.courses.servers.repository.control.ProductRespository;
import com.alsjava.courses.servers.utils.Constants;
import com.alsjava.courses.servers.utils.bootstrap.BootStrapInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by aluis on 11/2/19.
 */
public class BasicProducts implements BootStrapInsert {

    @Autowired
    private ProductRespository productRespository;

    public BasicProducts() {
        Constants.get().autoWiredClass(this);
    }

    @Override
    public void insert() {
    }
}
