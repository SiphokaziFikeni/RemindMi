package com.example.sfikeni.remindmi.ui.reminderdetails;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.sfikeni.remindmi.Constants;
import com.example.sfikeni.remindmi.R;
import com.example.sfikeni.remindmi.database.entity.ReminderEntity;
import com.example.sfikeni.remindmi.ui.reminderlist.MainActivity;
import com.example.sfikeni.remindmi.viewmodel.DetailsViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReminderDetailsActivity extends AppCompatActivity {

    private DetailsViewModel detailsViewModel;

    @BindView(R.id.reminder_detail_title)
    TextView reminderTitle;

    @BindView(R.id.reminder_detail_description)
    TextView reminderDescription;

    @BindView(R.id.reminder_detail_priority)
    TextView reminderPriority;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_details);
        ButterKnife.bind(this);

        setupToolbar();
        detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);

        String reminderId;

        if (getIntent() != null) {
            reminderId = getIntent().getStringExtra(Constants.REMINDER_ID_TAG);
            setReminderDetails(reminderId);
        }
    }

    private void setReminderDetails(String reminderId) {

        showReminderDetails(detailsViewModel.getReminderById(reminderId));
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24px);
            toolbar.setContentInsetStartWithNavigation(0);
            getSupportActionBar().setTitle(getString(R.string.reminder_details_text));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showReminderDetails(ReminderEntity reminder) {
        if (reminder != null) {
            reminderTitle.setText(reminder.getTitle());
            reminderDescription.setText(reminder.getDescription());
            reminderPriority.setText(reminder.getPriorityLevel());
        }
    }
}
