package com.katiejoy.mediready;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void setDate(View view) {
        PickerDialogs pickerDiaglogs = new PickerDialogs();
        pickerDiaglogs.show(getSupportFragmentManager(), "date_picker");
    }
}
