package com.example.anton.laba5new;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";

    DatabaseHelper mDatabaseHelper;

    private ListView mListView, mListView1, mListView2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        mListView = (ListView) findViewById(R.id.listView);
        mListView1 = (ListView) findViewById(R.id.listView1);
        mListView2 = (ListView) findViewById(R.id.listView2);
        mDatabaseHelper = new DatabaseHelper(this);

        populateListView();
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get the data and append to a list
        Cursor data = mDatabaseHelper.getData();

        ArrayList<Integer> listDataID = new ArrayList<>();
        ArrayList<String> listData = new ArrayList<>();

        ArrayList<Long> listDataTime = new ArrayList<>();
        ArrayList<String> listDataTimeS = new ArrayList<>();

        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            listDataID.add(data.getInt(0));
            listData.add(data.getString(1));
            listDataTime.add(data.getLong(2));
        }

        int i = 0;
        while (i < listDataTime.size()) {
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            Date t1 = new java.sql.Date(listDataTime.get(i));
            listDataTimeS.add(i, dateFormat.format(t1));
            i++;
        }

        //create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        ListAdapter adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listDataID);
        ListAdapter adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listDataTimeS);
        mListView.setAdapter(adapter);
        mListView1.setAdapter(adapter1);
        mListView2.setAdapter(adapter2);
    }
}
