package com.example.sfikeni.remindmi.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.sfikeni.remindmi.repository.ReminderRepository;
import com.example.sfikeni.remindmi.repository.ReminderRepositoryImpl;

/**
 * Created by SFikeni on 2018/02/05.
 */

public class AddReminderViewModel extends AndroidViewModel {

    private ReminderRepository reminderRepository;

    public AddReminderViewModel(@NonNull Application application) {
        super(application);
        reminderRepository = new ReminderRepositoryImpl();
        reminderRepository.initializeDao();
        reminderRepository.getRealmConfiguration(application);
        reminderRepository.getRealmInstance();
    }

    public void saveReminder(String id, String title, String description, String priority, String dateString, String timeString) {

       reminderRepository.saveReminder(id, title, description, priority, dateString, timeString);
    }

    @Override
    protected void onCleared() {
        reminderRepository.closeRealmInstance();
        super.onCleared();
    }

}
