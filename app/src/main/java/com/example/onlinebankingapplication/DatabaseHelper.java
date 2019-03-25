package com.example.onlinebankingapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME="bank.db";
    public static final String TABLE_NAME1="placesATM";
    public static final String COL_11="doorno";
    public static final String COL_12="circle";
    public static final String COL_13="place";
    public static final String COL_14="state";
    public static final String COL_15="pin";


    public static final String TABLE_NAME2="places";
    public static final String COL_21="CustomerID";
    public static final String COL_22="PIN";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
