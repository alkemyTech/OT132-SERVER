package com.alkemy.ong.integration.common;

import java.util.Random;

public class TestUtils {

  public static String generateRandomString(int size) {
    int leftLimit = 48;
    int rightLimit = 122;
    Random random = new Random();

    return random.ints(leftLimit, rightLimit + 1)
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        .limit(size)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }
}
