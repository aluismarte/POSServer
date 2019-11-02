package com.alsjava.courses.servers;

import com.alsjava.courses.servers.utils.Constants;
import com.alsjava.courses.servers.utils.bootstrap.BootStrap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by aluis on 11/2/19.
 */
@SpringBootApplication
public class POSServer {

    private static BootStrap bootStrap;

    public static void main(String[] args) {
        startSpring(args);
    }

    public static void startSpring(String[] args) {
        Constants.get().setApplicationContext(SpringApplication.run(POSServer.class, args));
        prepareSingleton();
        bootStrap = new BootStrap();
        bootStrap.init();
    }

    private static void prepareSingleton() {
        Constants.get();
    }

    public static void stop() {
        if (bootStrap != null) {
            bootStrap.destroy();
        }
    }
}
