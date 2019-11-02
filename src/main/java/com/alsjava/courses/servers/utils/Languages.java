package com.alsjava.courses.servers.utils;

import com.vaadin.flow.server.VaadinSession;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Created by aluis on 11/2/19.
 */
public class Languages {

    public static final Locale ENGLISH = Locale.ENGLISH;

    private static volatile Languages instance = null;

    private static PropertyResourceBundle PRB_EN;

    private Languages() {
        try {
            PRB_EN = new PropertyResourceBundle(new InputStreamReader(getClass().getResourceAsStream("/posserver.properties"), StandardCharsets.UTF_8));
        } catch (Exception ignored) {
            System.out.println("Problem loading Language Pack");
        }
    }

    public static Languages get() {
        Languages result = instance;
        if (result == null) {
            synchronized (Languages.class) {
                if (instance == null) {
                    instance = result = new Languages();
                }
            }
        }
        return result;
    }

    public static List<Locale> values() {
        List<Locale> locales = new ArrayList<>();
        locales.add(ENGLISH);
        return locales;
    }

    private ResourceBundle getI18N() {
        return PRB_EN;
    }

    public String i18n(String key) {
        try {
            ResourceBundle resourceBundle = getI18N();
            if (resourceBundle != null) {
                return resourceBundle.getString(key);
            }
        } catch (Exception ignored) {
        }
        return "[" + key + "]";
    }

    public Locale getDefault() {
        return ENGLISH;
    }

    public static void setDefault(String language) {
        setDefault(ENGLISH);
    }

    public static void setDefault(Locale locale) {
        if (VaadinSession.getCurrent() != null) {
            VaadinSession.getCurrent().setLocale(locale);
        }
    }

    public Locale getLocale(String language) {
        return ENGLISH;
    }

    public String getMapLanguage(Locale language) {
        return i18n("language.english");
    }
}
