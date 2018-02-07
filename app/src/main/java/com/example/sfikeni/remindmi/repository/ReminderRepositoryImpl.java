package com.example.sfikeni.remindmi.repository;

import android.arch.lifecycle.LiveData;

import com.example.sfikeni.remindmi.database.dao.ReminderDao;
import com.example.sfikeni.remindmi.database.entity.ReminderEntity;

import java.util.List;

/**
 * Created by SFikeni on 2018/02/06.
 */

public class ReminderRepositoryImpl implements ReminderRepository {

    private ReminderDao reminderDao;

    @Override
    public LiveData<List<ReminderEntity>> getReminderList() {



        return reminderDao.getAllReminders();
    }

    @Override
    public void saveReminder(ReminderEntity reminderEntity) {

    }
}
