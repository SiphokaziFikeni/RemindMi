package com.example.sfikeni.remindmi.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.sfikeni.remindmi.database.entity.ReminderEntity;

import java.util.List;

/**
 * Created by SFikeni on 2018/02/06.
 */

public interface ReminderRepository {

    void initializeDao();

    LiveData<List<ReminderEntity>> getReminderList();

    void saveReminder(String id, String title, String description, String priority, String dateString, String timeString);

    ReminderEntity getReminderById(String reminderId);

    void getRealmConfiguration(Context context);

    void getRealmInstance();

    void closeRealmInstance();

}
