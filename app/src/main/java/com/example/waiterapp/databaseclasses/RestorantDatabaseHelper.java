package com.example.waiterapp.databaseclasses;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RestorantDatabaseHelper extends DatabaseHelper{
    public RestorantDatabaseHelper(Context context) {
        super(context);
    }


    public boolean isAnyRestorantExist(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from restorant",null);

        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}
