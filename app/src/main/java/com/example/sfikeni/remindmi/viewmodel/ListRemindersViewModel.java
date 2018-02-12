package com.example.sfikeni.remindmi.viewmodel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;

import com.example.sfikeni.remindmi.database.entity.Reminder;
import com.example.sfikeni.remindmi.database.entity.ReminderEntity;
import com.example.sfikeni.remindmi.repository.ReminderRepository;
import com.example.sfikeni.remindmi.repository.ReminderRepositoryImpl;

import java.util.List;

import javax.annotation.Nonnull;

/**
 * Created by SFikeni on 2018/02/06.
 */

public class ListRemindersViewModel extends AndroidViewModel {

    private ReminderRepository reminderRepository;

    public ListRemindersViewModel(@Nonnull Application application) {
        super(application);
        reminderRepository = new ReminderRepositoryImpl();
        reminderRepository.initializeDao();
        reminderRepository.getRealmConfiguration(application);
        reminderRepository.getRealmInstance();
    }

    public LiveData<List<? extends Reminder>> getReminders() {

        LiveData<List<? extends Reminder>> realmResult;
        realmResult = Transformations.map(reminderRepository.getReminderList(), new Function<List<ReminderEntity>, List<? extends Reminder>>() {
            @Override
            public List<? extends Reminder> apply(List<ReminderEntity> input) {
                return input;
            }
        });

        return realmResult;
    }

    @Override
    protected void onCleared() {
        reminderRepository.closeRealmInstance();
        super.onCleared();
    }

}
