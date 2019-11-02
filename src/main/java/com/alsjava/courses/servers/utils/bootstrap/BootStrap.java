package com.alsjava.courses.servers.utils.bootstrap;

import com.alsjava.courses.servers.utils.bootstrap.control.BasicProducts;
import com.alsjava.courses.servers.utils.bootstrap.security.BasicTerminals;
import com.alsjava.courses.servers.utils.bootstrap.security.BasicUsers;

/**
 * Created by aluis on 11/2/19.
 */
public class BootStrap {

    private BasicUsers basicUsers = new BasicUsers();
    private BasicTerminals basicTerminals = new BasicTerminals();
    private BasicProducts basicProducts = new BasicProducts();

    public BootStrap() {
    }

    public void init() {
        basicUsers.insert();
        basicTerminals.insert();
        basicProducts.insert();
        System.out.println("Finish Bootstrap load");
    }

    public void destroy() {
    }
}
