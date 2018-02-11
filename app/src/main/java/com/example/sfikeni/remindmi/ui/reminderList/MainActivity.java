package com.example.sfikeni.remindmi.ui.reminderList;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.sfikeni.remindmi.R;
import com.example.sfikeni.remindmi.database.entity.Reminder;
import com.example.sfikeni.remindmi.ui.setReminder.SetReminderActivity;
import com.example.sfikeni.remindmi.viewModel.ListRemindersViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private ListRemindersViewModel listRemindersViewModel;
    private ReminderAdapter reminderAdapter;
    @BindView(R.id.reminders_recyclerview)
    RecyclerView remindersRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        reminderAdapter = new ReminderAdapter();
        setupRemindersRecyclerView();

        setupViewModel();
        setReminderAdapterItems();
    }

    private void setupRemindersRecyclerView() {
        remindersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        remindersRecyclerView.setAdapter(reminderAdapter);
    }

    private void setupViewModel() {
        listRemindersViewModel = ViewModelProviders.of(this).get(ListRemindersViewModel.class);
    }

    private void setReminderAdapterItems(){
        listRemindersViewModel.getReminders().observe(this, new Observer<List<? extends Reminder>>() {
            @Override
            public void onChanged(@Nullable List<? extends Reminder> reminderEntities) {
                reminderAdapter.setReminderEntities(reminderEntities);
            }
        });
    }

    @OnClick(R.id.create_reminder_fab)
    public void addNewReminder(){
        Intent intent = new Intent(this, SetReminderActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(this, "Action Settings clicked", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
