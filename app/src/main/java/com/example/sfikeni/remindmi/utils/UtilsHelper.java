package com.example.sfikeni.remindmi.utils;

import java.util.UUID;

/**
 * Created by SFikeni on 2018/02/11.
 */

public class UtilsHelper {

    public static String createReminderId(){
        return UUID.randomUUID().toString();
    }

}
