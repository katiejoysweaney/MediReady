package com.katiejoy.mediready;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import java.util.Calendar;


public class DatePickerActivity extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog;
        dialog = new DatePickerDialog(getActivity(), this, year, month, day);

        return dialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {

        Intent intent = new Intent(getContext(), DateActivity.class);
        intent.putExtra("day", day);
        intent.putExtra("month", month);
        intent.putExtra("year", year);
        startActivity(intent);
    }
}