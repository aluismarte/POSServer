package com.alsjava.courses.servers.utils;

import com.alsjava.courses.servers.POSServer;
import com.alsjava.courses.servers.utils.security.LoginManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.stream.Collectors;

/**
 * Created by aluis on 11/2/19.
 */
@Component
public class Constants {

    private static volatile Constants instance = null;

    public static final LoginManager LOGIN_MANAGER = new LoginManager();

    public static final String SYSTEM = "system";
    public static final int USERNAME_LENGTH = 30;

    public ConfigurableApplicationContext applicationContext;

    public static final String LOG_FILE_EXT = ".log";
    public static final String LIMIT_FILE_EXT = ".limit";
    public static final String LOTTERY_CLOSE_EXT = ".lc";
    public static final String LIMIT_FILE_NAME_FORMAT = "yyyy_MM_dd";
    private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    public final String VERSION = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/version"))).lines().collect(Collectors.joining());

    @Value("${info.app.name}")
    public String APP_NAME;

    private Constants() {
    }

    public static Constants get() {
        Constants result = instance;
        if (result == null) {
            synchronized (Constants.class) {
                if (instance == null) {
                    instance = result = new Constants();
                }
            }
        }
        return result;
    }

    public void setApplicationContext(ConfigurableApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.applicationContext.addApplicationListener((ApplicationListener<ContextClosedEvent>) event -> POSServer.stop());
    }

    public <T> T convert(String data, Class<T> validClass) {
        if (data != null && !data.isEmpty()) {
            try {
                return this.gson.fromJson(data, validClass);
            } catch (Exception var4) {
                return null;
            }
        } else {
            return null;
        }
    }

    public <T> T convert(String data, Type type) {
        if (data != null && !data.isEmpty()) {
            try {
                return this.gson.fromJson(data, type);
            } catch (Exception var4) {
                return null;
            }
        } else {
            return null;
        }
    }
}
