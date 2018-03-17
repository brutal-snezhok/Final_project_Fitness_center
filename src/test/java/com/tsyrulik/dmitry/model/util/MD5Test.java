package com.tsyrulik.dmitry.model.util;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class MD5Test {
    private MD5 encryptor;
    private static final String expectedPassword = "5f4dcc3b5aa765d61d8327deb882cf99";

    @BeforeMethod
    public void setUp() {
        encryptor = new MD5();
    }

    @Test
    public void shouldEncryptString() {
        Assert.assertEquals(encryptor.encrypt("password"), expectedPassword);
    }
}