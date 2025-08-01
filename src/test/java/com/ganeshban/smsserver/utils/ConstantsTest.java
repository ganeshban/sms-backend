package com.ganeshban.smsserver.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static com.ganeshban.smsserver.utils.Constants.generatePassword;

class ConstantsTest {
    @InjectMocks Constants constants;

    @Test
    void generatePasswordTest() {
        String password = constants.generatePassword(5, Constants.IncludePatternStrategy.UPPERCASE);
        System.out.println(password);
        Assertions.assertEquals(5, password.length());
    }
}