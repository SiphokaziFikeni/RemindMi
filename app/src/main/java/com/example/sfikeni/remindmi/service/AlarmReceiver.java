package com.example.sfikeni.remindmi.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.sfikeni.remindmi.ui.reminderlist.MainActivity;
import com.example.sfikeni.remindmi.utils.PreferencesHelper;

/**
 * Created by SFikeni on 2018/02/13.
 */

public class AlarmReceiver extends BroadcastReceiver {

    static String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction() != null && context != null) {

            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                PreferencesHelper preferencesHelper = new PreferencesHelper(context);
                NotificationScheduler.setReminder(context, AlarmReceiver.class, preferencesHelper.getHour(), preferencesHelper.getMinute());
                return;
            }
        }

        //Trigger the notification todo class update to detail view

        NotificationScheduler.showNotification(context, MainActivity.class, "Reminder Title", "Reminder content description");
    }
}
