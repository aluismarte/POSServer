package com.alsjava.courses.servers.utils.configuration;

import com.alsjava.courses.servers.POSServer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.annotation.WebServlet;

/**
 * Created by aluis on 11/2/19.
 */
@Configuration
@WebServlet
@EnableTransactionManagement
@ComponentScan
public class SpringServletConfiguration extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(POSServer.class);
    }
}
