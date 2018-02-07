package com.example.sfikeni.remindmi.viewModel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.sfikeni.remindmi.database.entity.Reminder;
import com.example.sfikeni.remindmi.database.dao.ReminderDao;
import com.example.sfikeni.remindmi.database.entity.ReminderEntity;

import java.util.List;

import io.realm.Realm;

/**
 * Created by SFikeni on 2018/02/06.
 */

public class ListRemindersViewModel extends ViewModel {

    private Realm realm;
    private LiveData<List<? extends Reminder>> reminders;
    private ReminderDao reminderDao;

    public ListRemindersViewModel() {
        realm = Realm.getDefaultInstance();
        reminderDao = new ReminderDao(realm);

        reminders = Transformations.map(reminderDao.getAllReminders(), new Function<List<ReminderEntity>, List<? extends Reminder>>() {
            @Override
            public List<? extends Reminder> apply(List<ReminderEntity> input) {
                return input;
            }
        });
    }

    public LiveData<List<? extends Reminder>> getReminders(){
        return reminders;
    }

    public void saveReminder(ReminderEntity reminderEntity){
        // TODO: 2018/02/06 save reminderEntity to realm
    }

    @Override
    protected void onCleared() {
        realm.close();
        super.onCleared();
    }

}
