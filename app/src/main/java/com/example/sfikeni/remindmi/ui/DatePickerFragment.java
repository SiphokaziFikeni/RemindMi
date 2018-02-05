package com.example.sfikeni.remindmi.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.example.sfikeni.remindmi.R;

import java.util.Calendar;

/**
 * Created by SFikeni on 2018/02/05.
 */

public class DatePickerFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar calendar = Calendar.getInstance();
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        if (getActivity() != null)
            return new DatePickerDialog(getActivity(), R.style.PickerStyle, (DatePickerDialog.OnDateSetListener) getActivity(), year, month, dayOfMonth);
        else
            return super.onCreateDialog(savedInstanceState);
    }

}
