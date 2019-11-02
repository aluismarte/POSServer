package com.alsjava.courses.servers;

import com.alsjava.courses.servers.utils.Constants;
import com.alsjava.courses.servers.utils.bootstrap.BootStrap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.annotation.WebServlet;

/**
 * Created by aluis on 11/2/19.
 */
@WebServlet
@EnableTransactionManagement
@SpringBootApplication
public class POSServer extends SpringBootServletInitializer {

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

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(POSServer.class);
    }
}
