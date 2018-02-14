package com.example.sfikeni.remindmi.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.example.sfikeni.remindmi.Constants;
import com.example.sfikeni.remindmi.R;
import com.example.sfikeni.remindmi.utils.PreferencesHelper;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by SFikeni on 2018/02/13.
 */

public class NotificationScheduler {

    public static final int DAILY_REMINDER_REQUEST_CODE = 100;

    public static void setReminder(Context context, Class<?> cls, String reminderId, String reminderTitle, String reminderDescription, int hour, int min) {

        Calendar calendar = Calendar.getInstance();
        Calendar setcalendar = Calendar.getInstance();
        int requestCode = PreferencesHelper.getAlarmId(context);

        setcalendar.set(Calendar.HOUR_OF_DAY, hour);
        setcalendar.set(Calendar.MINUTE, min);
        setcalendar.set(Calendar.SECOND, 0);

        if (setcalendar.before(calendar))
            setcalendar.add(Calendar.DATE, 1);

        // Enable a receiver
        ComponentName receiver = new ComponentName(context, cls);
        PackageManager packageManager = context.getPackageManager();

        packageManager.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

        Intent intent = new Intent(context, cls);
        intent.putExtra(Constants.REMINDER_ID_TAG, reminderId);
        intent.putExtra(Constants.REMINDER_TITLE_TAG, reminderTitle);
        intent.putExtra(Constants.REMINDER_DESCRIPTION_TAG, reminderDescription);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        if (alarmManager != null)
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 0, pendingIntent);
    }

    public static void cancelReminder(Context context, Class<?> cls, int requestCode) {
        // Disable a receiver
        ComponentName receiver = new ComponentName(context, cls);
        PackageManager packageManager = context.getPackageManager();

        packageManager.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);

        Intent intent = new Intent(context, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        if (alarmManager != null)
            alarmManager.cancel(pendingIntent);
        pendingIntent.cancel();
    }

    public static void showNotification(Context context, Class<?> cls, String reminderId, String title, String description) {
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent notificationIntent = new Intent(context, cls);
        notificationIntent.putExtra(Constants.REMINDER_ID_TAG, reminderId);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(cls);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(DAILY_REMINDER_REQUEST_CODE, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        Notification notification = builder.setContentTitle(title)
                .setContentText(description)
                .setAutoCancel(true)
                .setSound(alarmSound)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentIntent(pendingIntent).build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager != null) {
            int notificationId = PreferencesHelper.getNotificationId(context);
            notificationManager.notify(notificationId, notification);
        }
    }

}
