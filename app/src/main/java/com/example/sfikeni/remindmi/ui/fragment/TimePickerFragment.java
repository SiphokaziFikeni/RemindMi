package com.example.sfikeni.remindmi.ui.fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;

import com.example.sfikeni.remindmi.R;

import java.util.Calendar;

/**
 * Created by SFikeni on 2018/02/05.
 */

public class TimePickerFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);

        if (getActivity() != null)
            return new TimePickerDialog(getActivity(), R.style.PickerStyle, (TimePickerDialog.OnTimeSetListener) getActivity(), hourOfDay, currentMinute, DateFormat.is24HourFormat(getActivity()));
        else
            return super.onCreateDialog(savedInstanceState);
    }

}
