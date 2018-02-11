package com.example.sfikeni.remindmi.viewModel;

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

    //    private Realm realm;
//    private LiveData<List<? extends Reminder>> reminders;
//    private ReminderDao reminderDao;

    private ReminderRepository reminderRepository;

    public ListRemindersViewModel(@Nonnull Application application) {
        super(application);
        reminderRepository = new ReminderRepositoryImpl();
        reminderRepository.initializeDao();
        reminderRepository.getRealmConfiguration(application);
        reminderRepository.getRealmInstance();

//        reminders = Transformations.map(reminderDao.getAllReminders(), new Function<List<ReminderEntity>, List<? extends Reminder>>() {
//            @Override
//            public List<? extends Reminder> apply(List<ReminderEntity> input) {
//                return input;
//            }
//        });
    }

    public LiveData<List<? extends Reminder>> getReminders() {
//        return reminders;
        return Transformations.map(reminderRepository.getReminderList(), new Function<List<ReminderEntity>, List<? extends Reminder>>() {
            @Override
            public List<? extends Reminder> apply(List<ReminderEntity> input) {
                return input;
            }
        });
    }

    public void saveReminder(ReminderEntity reminderEntity) {
        // TODO: 2018/02/06 save reminderEntity to realm
    }

    @Override
    protected void onCleared() {
        reminderRepository.closeRealmInstance();
        super.onCleared();
    }

}
