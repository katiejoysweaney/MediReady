package com.katiejoy.mediready;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DateActivity extends AppCompatActivity {

    SQLiteDatabase eventsdb;
    String event;
    TextView eventTextView;
    Button addEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        Intent intent = getIntent();

        final int month = intent.getIntExtra("month", 0);
        final int day = intent.getIntExtra("day", 0);
        final int year = intent.getIntExtra("year", 0);

        final String key = Integer.toString(month) + Integer.toString(day) + Integer.toString(year);

        try {
            eventsdb = this.openOrCreateDatabase("EVENTSDB", MODE_PRIVATE, null);
            //eventsdb.delete("events", null, null);
            eventsdb.execSQL("CREATE TABLE IF NOT EXISTS events (key VARCHAR, month INT, day INT, year INT, title VARCHAR)");

            Cursor c = eventsdb.rawQuery("SELECT * FROM events WHERE key=" + key, null);

            //if(c.getCount() > 0){
                c.moveToFirst();
                event = c.getString(c.getColumnIndex("title"));
                eventTextView.setText("" + eventTextView);
            //}

        } catch (Exception e) {
            Log.i("error", e.toString());
        }

        Button button;
        button = (Button) findViewById(R.id.goHomeButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        eventTextView = (TextView) findViewById(R.id.eventTextView);


        addEvent = (Button) findViewById(R.id.addEvent);
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText input = new EditText(DateActivity.this);
                new AlertDialog.Builder(DateActivity.this)
                    .setTitle("Enter your event:")
                    .setView(input)
                    .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            event = input.getText().toString();
                            try{
                                eventsdb.execSQL("INSERT INTO events (key, month, day, year, title) " +
                                                 "VALUES ('" + key + "', " + month + ", " + day + ", "
                                                 + year + ", '" + event + "')");
                            } catch (Exception e){
                                e.printStackTrace();
                                Log.i("error adding event", e.toString());
                            }
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .show();

            }
        });




        TextView dateText = (TextView)findViewById(R.id.dateText);
        dateText.setText("Date: "+ (month+1) + "/" + day + "/" + year);






    }
}

