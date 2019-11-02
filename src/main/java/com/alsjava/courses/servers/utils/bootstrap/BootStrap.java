package com.alsjava.courses.servers.utils.bootstrap;

import com.alsjava.courses.servers.utils.bootstrap.security.BasicUsers;
import org.springframework.stereotype.Component;

/**
 * Created by aluis on 11/2/19.
 */
@Component
public class BootStrap {

    private BasicUsers basicUsers = new BasicUsers();

    public BootStrap() {
    }

    public void init() {
        basicUsers.insert();
        System.out.println("Finish Bootstrap load");
    }

    public void destroy() {
    }
}
