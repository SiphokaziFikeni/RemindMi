package com.example.sfikeni.remindmi.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.sfikeni.remindmi.database.entity.ReminderEntity;
import com.example.sfikeni.remindmi.repository.ReminderRepository;
import com.example.sfikeni.remindmi.repository.ReminderRepositoryImpl;

/**
 * Created by SFikeni on 2018/02/14.
 */

public class DetailsViewModel extends AndroidViewModel {

    ReminderRepository reminderRepository;

    public DetailsViewModel(@NonNull Application application) {
        super(application);
        reminderRepository = new ReminderRepositoryImpl();
        reminderRepository.initializeDao();
        reminderRepository.getRealmConfiguration(application);
        reminderRepository.getRealmInstance();
    }

    public ReminderEntity getReminderById(String reminderId){
      return reminderRepository.getReminderById(reminderId);
    }

    @Override
    protected void onCleared() {
        reminderRepository.closeRealmInstance();
        super.onCleared();
    }
}
