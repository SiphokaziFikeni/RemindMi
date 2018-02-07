package com.example.sfikeni.remindmi.repository;

import android.arch.lifecycle.LiveData;

import com.example.sfikeni.remindmi.database.entity.ReminderEntity;

import java.util.List;

/**
 * Created by SFikeni on 2018/02/06.
 */

public interface ReminderRepository {

    LiveData<List<ReminderEntity>> getReminderList();

    void saveReminder(ReminderEntity reminderEntity);
}
