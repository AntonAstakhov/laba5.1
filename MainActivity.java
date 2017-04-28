package com.example.anton.laba5new;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper db = new DatabaseHelper(this);
        db.deleteDB();
        db.createDB();
    }

    public void OnClick1 (View view) {
        Intent intent = new Intent(this, ListDataActivity.class);
        startActivity(intent);
    }

    public void OnClick2 (View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    DatabaseHelper mDatabaseHelper;
    public void OnClick3 (View view) { // замінити ПIБ в останньому внесеному записі на "Василенко Василь Васильович"
        mDatabaseHelper = new DatabaseHelper(this);

        int a = mDatabaseHelper.getMaxId();
        String b = mDatabaseHelper.getItemName(a);

        mDatabaseHelper.updateName("Василенко В. В.",a,b);

        Intent intent = new Intent(this, ListDataActivity.class);
        startActivity(intent);
    }

    public void OnClick4 (View view) {
        Intent intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
    }


    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public void OnClick5 (View view) { // вивести версію БД
        mDatabaseHelper = new DatabaseHelper(this);
        Intent intent = new Intent(this, Main4Activity.class);
        String message = String.valueOf(mDatabaseHelper.getdbver());
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
