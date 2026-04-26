package com.kiosk.cuss.util;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class RandomStringUtils {
    public static String getOneRandomString(int size) {
        byte[] array = new byte[size];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }

}
