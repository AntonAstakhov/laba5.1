// класс для выведения версии БД

package com.example.anton.laba5new;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.anton.laba5new.MainActivity.EXTRA_MESSAGE;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Intent intent = getIntent();
        String message = intent.getStringExtra(EXTRA_MESSAGE);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(message);
    }
}
