package com.ganeshban.smsserver.utils;

import com.ganeshban.smsserver.entity.BaseEntity;

import java.util.Arrays;
import java.util.Random;

public class Constants {
    private Constants() {
    }

    private static final Random rand = new Random();
    public static final String ACTIVE = "ACTIVE";
    public static final String IN_ACTIVE = "IN_ACTIVE";

    public static boolean isActive(BaseEntity entity) {
        return entity.getStatus().equalsIgnoreCase(ACTIVE);
    }

    public enum IncludePatternStrategy {
        LOWERCASE,
        UPPERCASE,
        NUMBER
    }

    public static String generatePassword(int length, IncludePatternStrategy... includes) {


        String uCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lCase = uCase.toLowerCase();
        String num = "0123456789";
        String all = "";
        if (includes.length > 0) {
            if (Arrays.asList(includes).contains(IncludePatternStrategy.UPPERCASE)) {
                all = all + uCase;
            }
            if (Arrays.asList(includes).contains(IncludePatternStrategy.LOWERCASE)) {
                all = all + lCase;
            }
            if (Arrays.asList(includes).contains(IncludePatternStrategy.NUMBER)) {
                all = all + num;
            }
        }
        if (all.equals("")) {
            all = lCase + uCase + num;
        }
        StringBuilder sb = new StringBuilder(length);
        while (length - 1 >= sb.length()) {
            int random = (rand.nextInt() * 100 % all.length());
            sb.append(all.charAt(random));
        }
        return sb.toString();
    }


    public enum Priority {
        VERY_HIGH,
        HIGH,
        MEDIUM,
        LOW,
        VERY_LOW;
    }
}
