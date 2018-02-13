package com.example.sfikeni.remindmi.ui.setreminder;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.sfikeni.remindmi.R;
import com.example.sfikeni.remindmi.service.AlarmReceiver;
import com.example.sfikeni.remindmi.service.NotificationScheduler;
import com.example.sfikeni.remindmi.ui.reminderlist.MainActivity;
import com.example.sfikeni.remindmi.utils.PreferencesHelper;
import com.example.sfikeni.remindmi.utils.UtilsHelper;
import com.example.sfikeni.remindmi.viewmodel.AddReminderViewModel;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetReminderActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener
        , AdapterView.OnItemSelectedListener {

    @BindView(R.id.reminder_title_edit)
    TextInputEditText titleEditText;

    @BindView(R.id.reminder_description_text)
    EditText descriptionEditText;

    @BindView(R.id.reminder_set_date_text)
    Button setDateEditText;

    @BindView(R.id.reminder_time_text)
    Button setTimeText;

    @BindView(R.id.reminder_priority_spinner)
    Spinner prioritySpinner;

    private AddReminderViewModel addReminderViewModel;
    String timeString;
    String dateString;
    String priority;
    PreferencesHelper preferencesHelper;

    @OnClick(R.id.reminder_set_button)
    void setUserReminder() {

        setReminderTime();
        addReminderViewModel.saveReminder(UtilsHelper.createReminderId(), titleEditText.getText().toString(), descriptionEditText.getText().toString(), priority, dateString, timeString);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_reminder);
        ButterKnife.bind(this);

        preferencesHelper = new PreferencesHelper(getApplicationContext());
        setupToolbar();
        setupPrioritySpinner();

        addReminderViewModel = ViewModelProviders.of(this).get(AddReminderViewModel.class);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24px);
            toolbar.setContentInsetStartWithNavigation(0);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void setupPrioritySpinner() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.reminder_priority_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_item_layout);
        prioritySpinner.setAdapter(adapter);
        prioritySpinner.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_set_reminder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                goBackToMainActivity();
                return true;

            case R.id.action_close:
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle(getString(R.string.dialog_title))
                        .setMessage(getString(R.string.dialog_message))
                        .setPositiveButton(getString(R.string.dialog_positive_text), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                goBackToMainActivity();
                            }
                        })
                        .setNegativeButton(getString(R.string.dialog_negative_text), null)
                        .create();

                dialog.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goBackToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, month, dayOfMonth);
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

        dateString = String.valueOf(dateFormat.format(calendar.getTime()));

        setDateEditText.setText(String.format(getString(R.string.reminder_date_text), dateFormat.format(calendar.getTime())));
    }

    public void getDate(View view) {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getSupportFragmentManager(), "DatePicker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        timeString = String.valueOf(hourOfDay + ":" + minute);
        setTimeText.setText(String.format(getString(R.string.reminder_time_text), hourOfDay, minute));
        saveTimeToPreferences(hourOfDay, minute);
    }

    private void setReminderTime() {
        NotificationScheduler.setReminder(SetReminderActivity.this, AlarmReceiver.class,
                preferencesHelper.getHour(), preferencesHelper.getMinute());
    }

    private void saveTimeToPreferences(int hourOfDay, int minute) {
        preferencesHelper.setHour(hourOfDay);
        preferencesHelper.setMinute(minute);
    }

    public void getTime(View view) {
        DialogFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.show(getSupportFragmentManager(), "TimePicker");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        priority = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        priority = parent.getItemAtPosition(0).toString();
    }
}
