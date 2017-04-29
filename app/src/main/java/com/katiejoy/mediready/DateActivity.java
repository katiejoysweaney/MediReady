package com.katiejoy.mediready;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import static com.katiejoy.mediready.MainActivity.myDB;

public class DateActivity extends AppCompatActivity {

    public static TextView dateText;
    public static TextView dateText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);


        Typeface comfortaaFont = Typeface.createFromAsset(getAssets(), "fonts/Comfortaa-Regular.ttf");



        ImageButton button;
        button = (ImageButton) findViewById(R.id.goHomeButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageButton button2;
        button2 = (ImageButton) findViewById(R.id.addEventButton);

        ImageButton button3;
        button3 = (ImageButton) findViewById(R.id.viewEventsButton);

        ImageButton button4;
        button4 = (ImageButton) findViewById(R.id.deleteButton);

        ImageButton button5;
        button5 = (ImageButton) findViewById(R.id.addPeriodButton);

        ImageButton button6;
        button6 = (ImageButton) findViewById(R.id.addPillButton);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent("com.katiejoy.mediready.AddEventActivity");
                startActivity(intent2);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent("com.katiejoy.mediready.AddPeriodActivity");
                startActivity(intent4);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent("com.katiejoy.mediready.AddPillActivity");
                startActivity(intent3);
            }
        });

        button3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor data = myDB.getAllData();
                        if (data.getCount() == 0) {
                            showMessage("Events:", "No events.");
                            return;
                        } else {
                            StringBuffer buffer = new StringBuffer();
                            while (data.moveToNext()) {
                                    buffer.append(data.getString(1) + " " + data.getInt(2) + ":" + data.getInt(3) + "\n");
                            }
                                showMessage("Events:", buffer.toString());
                        }
                    }
                });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent("com.katiejoy.mediready.DeleteEventActivity");
                startActivity(intent3);
            }
        });



        Intent intent = getIntent();

        int month = intent.getIntExtra("month", 0);
        int day = intent.getIntExtra("day", 0);
        int year = intent.getIntExtra("year", 0);

        dateText = (TextView)findViewById(R.id.dateText);
        dateText.setTypeface(comfortaaFont);
        dateText.setText("DATE: "+ (month+1) + "/" + day + "/" + year);
        dateText2 = new TextView(this);
        dateText2.setText((month+1) + "/" + day + "/" + year);

    }

    public static String getDateText() {
        return dateText2.getText().toString();
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}

