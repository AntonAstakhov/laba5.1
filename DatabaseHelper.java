package com.example.anton.laba5new;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "people_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "time";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT, " + COL3 + " LONG)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);
        contentValues.put(COL3, time);

        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newName + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    public int getMaxId() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT MAX(ID) FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        int id = 0;
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(0);
            } while(cursor.moveToNext());
        }
        return id;
    }

    public String getItemName(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL2 + " FROM " + TABLE_NAME + " WHERE " + COL1 + " = " + id;
        Cursor data = db.rawQuery(query, null);
        data.moveToFirst();
        String a = "";
        if (data.moveToFirst()) {
            a = data.getString(data.getColumnIndex("name"));
        }
        return a;
    }

    public int getMiddleId() {
        String query = "SELECT COUNT(*) FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        int id = 0;
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(0);
            } while(cursor.moveToNext());
        }

        return getMaxId()-id/2;
    }

    int getdbver() {
        SQLiteDatabase db = this.getWritableDatabase();
        int a = db.getVersion();
        return a;
    }

    public void deleteDB() {
        String query = "DELETE FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
    }

    public void createDB() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL2, "aa");
        contentValues.put(COL3, new java.util.Date().getTime());
        db.insert (TABLE_NAME, null, contentValues);

        contentValues.put(COL2, "bb");
        contentValues.put(COL3, new java.util.Date().getTime());
        db.insert (TABLE_NAME, null, contentValues);

        contentValues.put(COL2, "cc");
        contentValues.put(COL3, new java.util.Date().getTime());
        db.insert (TABLE_NAME, null, contentValues);

        contentValues.put(COL2, "dd");
        contentValues.put(COL3, new java.util.Date().getTime());
        db.insert (TABLE_NAME, null, contentValues);

        contentValues.put(COL2, "ee");
        contentValues.put(COL3, new java.util.Date().getTime());
        db.insert (TABLE_NAME, null, contentValues);
    }
}
