package com.katiejoy.mediready;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.*;

public class AddPeriodActivity extends AppCompatActivity {

    //private EditText title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_period);

        Button OKButton;
        OKButton = (Button) findViewById(R.id.InputOKButton);
        OKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //title = (EditText) findViewById(R.id.titleInput);

        final RatingBar ratingBar1;
        ratingBar1 = (RatingBar) findViewById(R.id.ratingBar1);

        final  RatingBar ratingBar2;
        ratingBar2 = (RatingBar) findViewById(R.id.ratingBar2);

        OKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = MainActivity.myDB.insertData(DateActivity.getDateText(), "Period: " + DateActivity.getDateText(), ratingBar1.getRating(), ratingBar2.getRating());
                if(isInserted == true) {
                    Toast.makeText(AddPeriodActivity.this, "Cycle Added", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(AddPeriodActivity.this, "Error: Cycle Not Added", Toast.LENGTH_LONG).show();
                }
                finish();
            }
        });
    }
}
