package com.ganeshban.smsserver.utils;

import com.ganeshban.smsserver.entity.BaseEntity;

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

    public static String generatePassword(int length, Boolean... includes) {


        String uCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lCase = uCase.toLowerCase();
        String num = "0123456789";
        String all = "";
        if (includes.length >= 1 && Boolean.TRUE.equals(includes[0])) {
            all = all + uCase;
        }
        if (includes.length >= 2 && Boolean.TRUE.equals(includes[1])) {
            all = all + lCase;
        }
        if (includes.length >= 3 && Boolean.TRUE.equals(includes[2])) {
            all = all + num;
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
