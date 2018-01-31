package com.tsyrulik.dmitry.model.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    private static String MD_5 = "MD5";
    private static String UTF_8 = "utf-8";
    private static String ZERO = "0";

    public String encrypt(String string) {
        StringBuilder result = new StringBuilder();
        byte[] digest = new byte[0];
        try {
            MessageDigest messageDigest;
            messageDigest = MessageDigest.getInstance(MD_5);
            messageDigest.reset();
            messageDigest.update(string.getBytes(UTF_8));
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException exception) {
            exception.printStackTrace();
        }
        BigInteger bigInteger = new BigInteger(1, digest);
        String temp = bigInteger.toString(16);
        int length = temp.length();
        while (length < 32) {
            result.append(ZERO);
            length++;
        }
        result.append(temp);
        return result.toString();
    }
}