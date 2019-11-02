package com.alsjava.courses.servers.utils.security;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * Created by aluis on 11/2/19.
 */
public class IDGenerator {

    private IDGenerator() {
    }

    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
