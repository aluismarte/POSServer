package com.alsjava.courses.servers.model.security;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

/**
 * Created by aluis on 11/2/19.
 */
public class HashTools {

    private static final String ENCODE = "UTF-8";
    private static MessageDigest digest;

    public static MessageDigest getDigest() {
        return digest;
    }

    public static void setDigest(MessageDigest digest) {
        HashTools.digest = digest;
    }

    public static String encode(HashType hashType, String message) {
        if (message == null || message.equals("") || hashType == null) {
            return null;
        }
        hashString(hashType, message);
        return "";
    }

    public static String encode(HashType hashType, File file) {
        if (file == null || hashType == null) {
            return null;
        }
        hashFile(hashType, file);
        return "";
    }

    public static String encodeMD5(String message) {
        return hashString(HashType.MD5, message);
    }

    public static String encodeSHA1(String message) {
        return hashString(HashType.SHA1, message);
    }

    public static String encodeSHA256(String message) {
        return hashString(HashType.SHA256, message);
    }

    public static String encodeSHA512(String message) {
        return hashString(HashType.SHA512, message);
    }

    public static String encodeMD5(File file) {
        return hashFile(HashType.MD5, file);
    }

    public static String encodeSHA1(File file) {
        return hashFile(HashType.SHA1, file);
    }

    public static String encodeSHA256(File file) {
        return hashFile(HashType.SHA256, file);
    }

    public static String encodeSHA512(File file) {
        return hashFile(HashType.SHA512, file);
    }

    private static String hashString(HashType hashType, String message) {
        try {
            digest = MessageDigest.getInstance(HashType.getHashAlgorithm(hashType));
            byte[] hashedBytes = digest.digest(message.getBytes(ENCODE));
            return convertByteArrayToHexString(hashedBytes);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    private static String hashFile(HashType hashType, File file) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            MessageDigest digest = MessageDigest.getInstance(HashType.getHashAlgorithm(hashType));
            byte[] bytesBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(bytesBuffer)) != -1) {
                digest.update(bytesBuffer, 0, bytesRead);
            }
            byte[] hashedBytes = digest.digest();
            return convertByteArrayToHexString(hashedBytes);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    private static String convertByteArrayToHexString(byte[] arrayBytes) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            for (byte arrayByte : arrayBytes) {
                stringBuilder.append(Integer.toString((arrayByte & 0xff) + 0x100, 16).substring(1));
            }
            return stringBuilder.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
