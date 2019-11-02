package com.alsjava.courses.servers.model.security;

/**
 * Created by aluis on 11/2/19.
 */
public enum HashType {
    MD5, SHA1, SHA256, SHA512;

    public static String getHashAlgorithm(HashType hashType) {
        switch (hashType) {
            case MD5:
                return "MD5";
            case SHA1:
                return "SHA-1";
            case SHA256:
                return "SHA-256";
            case SHA512:
                return "SHA-512";
        }
        return "";
    }
}
