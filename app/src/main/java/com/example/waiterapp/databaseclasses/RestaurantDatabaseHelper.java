package com.example.waiterapp.databaseclasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RestaurantDatabaseHelper extends DatabaseHelper{
    public RestaurantDatabaseHelper(Context context) {
        super(context);
    }


    public boolean isAnyRestaurantExist(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from restaurant",null);

        if (cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }

    public boolean isRestaurantDataInserted(String restaurantName, String restaurantEmail, String restaurantPassword, int tableNumber){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("restaurantName",restaurantName);
        contentValues.put("restaurantEmail",restaurantEmail);
        contentValues.put("restaurantPassword",restaurantPassword);
        contentValues.put("tableNumber", tableNumber);

        long result = db.insert("restaurant",null,contentValues);

        if (result == -1){
            return false;
        }else {
            return true;
        }

    }
}
