package com.alkemy.ong.integration.contact;

import com.alkemy.ong.integration.common.AbstractBaseIntegrationTest;
import java.util.Random;

public abstract class AbstractBaseContactIntegrationTest extends AbstractBaseIntegrationTest {

  protected static final String PATH = "/contacts";
  protected static final String NAME = "Friedrich";
  protected static final int PHONE = 406203234;
  protected static final String MESSAGE = "A winner is a dreamer who never gives up.";

  protected String generateRandomString(int size) {
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
