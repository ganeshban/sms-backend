package com.ganeshban.smsserver.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConstantsTest {

    @Test
    void generatePasswordTest() {
        String password = Constants.generatePassword(5, Constants.IncludePatternStrategy.UPPERCASE);
        System.out.println(password);
        Assertions.assertEquals(5, password.length());
    }
}