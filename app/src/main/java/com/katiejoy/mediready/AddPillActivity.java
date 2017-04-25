package com.katiejoy.mediready;

import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.*;

public class AddPillActivity extends AppCompatActivity {

    private EditText title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pill);

        Button OKButton;
        OKButton = (Button) findViewById(R.id.InputOKButtonPill);
        OKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        title = (EditText) findViewById(R.id.titleInputPill);

        final TimePicker timePicker;
        timePicker = (TimePicker) findViewById(R.id.timePickerPill);
        timePicker.setIs24HourView(false);

        OKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = MainActivity.myDB.insertData(DateActivity.getDateText(), title.getText().toString(), timePicker.getHour(), timePicker.getMinute());
                if(isInserted == true) {
                    Toast.makeText(AddPillActivity.this, "Event Added", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(AddPillActivity.this, "Error: Event Not Added", Toast.LENGTH_LONG).show();
                }
                finish();
            }
        });
    }
}
