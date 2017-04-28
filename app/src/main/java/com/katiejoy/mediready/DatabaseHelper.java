package com.katiejoy.mediready;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by leahf on 4/15/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "calendar.db";
    public static final String TABLE_NAME = "event_table";
    public static final String COL_1 = "DATE";
    public static final String COL_2 = "TITLE";
    public static final String COL_3 = "HOUR";
    public static final String COL_4 = "MINUTE";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table " + TABLE_NAME + " (DATE STRING, TITLE STRING, HOUR FLOAT, MINUTE FLOAT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String date, String title, float hour, float minute) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, date);
        contentValues.put(COL_2, title);
        contentValues.put(COL_3, hour);
        contentValues.put(COL_4, minute);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1) {
            return false;
        }else {
            return true;
        }
    }




    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(" select * from "+ TABLE_NAME, null);
        //Cursor res = db.rawQuery(" select * from "+ TABLE_NAME + " where DATE= " + DateActivity.getDateText(), null);
        //Cursor res = db.rawQuery(" select * from "+ TABLE_NAME + " where DATE= " + DateActivity.getDateText() + "", null);

        return res;
    }

    public Integer deleteData(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, " TITLE = ? ", new String[] {title});
    }
}


