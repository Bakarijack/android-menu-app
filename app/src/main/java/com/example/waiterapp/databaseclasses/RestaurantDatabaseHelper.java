package com.example.waiterapp.databaseclasses;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RestaurantDatabaseHelper extends DatabaseHelper{
    public RestaurantDatabaseHelper(Context context) {
        super(context);
    }


    public boolean isAnyRestorantExist(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from restaurant",null);

        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}
