package com.example.sfikeni.remindmi.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.sfikeni.remindmi.database.dao.ReminderDao;
import com.example.sfikeni.remindmi.database.entity.ReminderEntity;

import java.util.List;

import javax.annotation.Nonnull;

/**
 * Created by SFikeni on 2018/02/06.
 */

public class ReminderRepositoryImpl implements ReminderRepository {

    private ReminderDao reminderDao;


    @Override
    public void initializeDao() {
       reminderDao = new ReminderDao();
    }

    @Override
    public LiveData<List<ReminderEntity>> getReminderList() {

        return reminderDao.getAllReminders();
    }

    @Override
    public void saveReminder(String id, String title, String description, String priority, String dateString, String timeString) {

        ReminderEntity reminder = new ReminderEntity(id, title, description, priority, dateString, timeString, null);

        reminderDao.saveReminder(reminder);
    }

    @Override
    public ReminderEntity getReminderById(String reminderId) {
        return reminderDao.getReminderById(reminderId);
    }

    @Override
    public void getRealmConfiguration(@Nonnull Context context) {
        reminderDao.getRealmConfiguration(context);
    }

    @Override
    public void getRealmInstance() {
        reminderDao.getRealm();
    }

    @Override
    public void closeRealmInstance() {
        reminderDao.closeRealmInstance();
    }
}
