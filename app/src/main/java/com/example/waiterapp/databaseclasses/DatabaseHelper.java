package com.example.waiterapp.databaseclasses;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "waiter.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table restaurant(restaurantId INTEGER primary key AUTOINCREMENT,restaurantName TEXT, restaurantEmail TEXT NOT NULL, restaurantPassword TEXT, tableNumber INTEGER default null)");
        sqLiteDatabase.execSQL("create table foodcategory(categoryId INTEGER primary key AUTOINCREMENT,categoryName TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists restaurant");
        sqLiteDatabase.execSQL("drop table if exists foodcategory");
    }
}
