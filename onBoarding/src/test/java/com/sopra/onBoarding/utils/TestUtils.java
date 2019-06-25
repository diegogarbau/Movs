package com.sopra.onBoarding.utils;

import java.util.Random;
import java.util.UUID;

public class TestUtils {
    public static Random random = new Random();

    public static String randomStringGenerator() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 7);
    }
}
