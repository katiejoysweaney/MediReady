package com.katiejoy.mediready;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.katiejoy.mediready.MainActivity.myDB;

public class DateActivity extends AppCompatActivity {

    public static TextView dateText;
    public static TextView dateText2;
    //private boolean found;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        //<Object eventDatabase;
       // Event> eventDatabase;

        Button button;
        button = (Button) findViewById(R.id.goHomeButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button button2;
        button2 = (Button) findViewById(R.id.addEventButton);

        Button button3;
        button3 = (Button) findViewById(R.id.viewEventsButton);

        Button button4;
        button4 = (Button) findViewById(R.id.deleteButton);

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

        //found = false;

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
                                //if (getDateText() == data.getString(0)) {
                                    buffer.append(data.getString(1) + " " + data.getInt(2) + ":" + data.getInt(3) + "\n");
                                    //found = true;
                                //}
                            }
                            //output.append(buffer);
                           // if(found == true) {
                                showMessage("Events:", buffer.toString());
                           // }else {
                            //    showMessage("Events:", "No events today.");
                           // }
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
        dateText.setText("Date Chosen: "+ (month+1) + "/" + day + "/" + year);
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

