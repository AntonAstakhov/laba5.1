package com.example.anton.laba5new;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        DatabaseHelper mDatabaseHelper;
        mDatabaseHelper = new DatabaseHelper(this);

        int a = mDatabaseHelper.getMiddleId();
        String b = mDatabaseHelper.getItemName(a);

        TextView textView = (TextView) findViewById(R.id.textView3);
        textView.setText(a + " " + b);
    }
}
