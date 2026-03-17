package com.ganeshban.smsserver.utils;

import com.ganeshban.smsserver.entity.BaseEntity;

import java.util.Arrays;
import java.util.Random;

import static com.ganeshban.smsserver.utils.Constants.Keyword.ACTIVE;

public interface Constants {
    Random rand = new Random();

    interface ErrorMessage {
        String USER_NOT_FOUND = "User Not found. Please try again.";
        String USER_NOT_FOUND_FOR = "User not found for ";
        String SENDER_NOT_REGISTER = "Sender is not register, Please re-register the sender or use correct one.";
        String INVALID_RECIPIENT = "Invalid recipient phone number : ";
        String INCORRECT_USERNAME_AND_PASSWORD = "username and password are incorrect.";
    }

    interface Keyword {

        String AUTO = "AUTO";
        String ACTIVE = "ACTIVE";
        String IN_ACTIVE = "IN_ACTIVE";
        String MAPPER_NAME = "spring";
        String AUTH_ERROR_KEY = "AUTH_ERROR";
    }


    static boolean isActive(BaseEntity entity) {
        return entity.getStatus().equalsIgnoreCase(ACTIVE);
    }

    enum IncludePatternStrategy {
        LOWERCASE,
        UPPERCASE,
        NUMBER
    }

    static String generatePassword(int length, IncludePatternStrategy... includes) {

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
        if (all.isEmpty()) {
            all = lCase + uCase + num;
        }
        StringBuilder sb = new StringBuilder(length);
        while (length - 1 >= sb.length()) {
            int random = (rand.nextInt() * 100 % all.length());
            sb.append(all.charAt(random));
        }
        return sb.toString();
    }


    enum Priority {
        VERY_HIGH,
        HIGH,
        MEDIUM,
        LOW,
        VERY_LOW
    }


    interface SqlQueries {
        String SMS_DATA_BY_ID_AND_CODE = "Select a from SMSDataEntity a where id in ( :ids ) and clientCode = :code";
        String NEW_SMS_DATA_BY_CODE = "Select a from SMSDataEntity a where clientCode = :code and status='ACTIVE' and sentDateTime is null order by priority ASC";
        String NEW_SMS_DATA_BY_CODE_AND_SENDER = "Select a from SMSDataEntity a where clientCode = :code and status='ACTIVE' and sentAfterDateTime <= CURRENT_TIMESTAMP and sender in  (:sender) and sentDateTime is null order by priority, sequenceNumber";
    }

    String[] whiteListURL = {"/login", "/logout", "/auth/**", "accessDenied", "swagger-ui/**", "/ping"};
}
