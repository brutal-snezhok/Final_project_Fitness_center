package com.tsyrulik.dmitry.model.util;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Test {
    private static String MD_5 = "Encryptor";
    private static String UTF_8 = "utf-8";
    private static String ZERO = "0";
    @BeforeMethod
    public void setUp()  {
       String string = "password1";
    }

    @AfterMethod
    public void setDown(){

    }
    @Test
    public String encrypt(){
        String string = "password1";
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