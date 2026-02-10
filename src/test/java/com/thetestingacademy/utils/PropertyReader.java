package com.thetestingacademy.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    public static String readKey(String key) throws Exception {
        Properties p = new Properties();
        FileInputStream fileInputStream = new FileInputStream(new File(System.getProperty("user.dir") + "/src/main/resources/data.properties"));
        try {
            p.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (p.getProperty(key) == null) {
            throw new Exception(p.getProperty(key) + " not found!!");
        } else {
            return p.getProperty(key);
        }
    }
}
