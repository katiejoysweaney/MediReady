package com.katiejoy.mediready;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteEventActivity extends AppCompatActivity {

    private boolean found;
    private EditText date;
    private EditText title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_event);

        date = (EditText) findViewById(R.id.deleteDate);
        title = (EditText) findViewById(R.id.deleteTitle);

        Button okButton = (Button) findViewById(R.id.confirmDelete);
        found = false;

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = MainActivity.myDB.getAllData();
                if (data.getCount() == 0) {
                    Toast.makeText(DeleteEventActivity.this, "No events to delete.", Toast.LENGTH_LONG).show();
                } else {
                    while (data.moveToNext()) {
                        if(date.getText().toString().equals(data.getString(0))) {
                            found = true;
                        }
                        if(found) {
                            if(title.getText().toString().equals(data.getString(1))) {
                                Integer deletedRows = MainActivity.myDB.deleteData(title.getText().toString());
                                if(deletedRows != 0) {
                                    Toast.makeText(DeleteEventActivity.this, "Event Deleted", Toast.LENGTH_LONG).show();
                                }
                            }else {
                                Toast.makeText(DeleteEventActivity.this, "No events for this date with given title.", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
                finish();
            }
        });
    }
}
