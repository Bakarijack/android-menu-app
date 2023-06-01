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


    public boolean isTableNumberSet(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from restaurant",null);

        if (cursor.getCount() == 0){
            return false;
        }else {
            while (cursor.moveToNext()){
                if (cursor.getInt(4) > 0){
                    return true;
                }
            }
        }
        return false;
    }


    public boolean isRestaurantDataInserted(String restaurantName, String restaurantEmail, String restaurantPassword){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("restaurantName",restaurantName);
        contentValues.put("restaurantEmail",restaurantEmail);
        contentValues.put("restaurantPassword",restaurantPassword);

        long result = db.insert("restaurant",null,contentValues);

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }


    public boolean updateRestaurantData(String restaurantName, String restaurantEmail, String restaurantPassword){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("restaurantName",restaurantName);
        values.put("restaurantEmail",restaurantEmail);
        values.put("restaurantPassword",restaurantPassword);

        long result = db.update("restaurant",values," restaurantName = ?",new String[]{restaurantName});

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean updateTableNumber(String restaurantEmail, int tableNumber){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tableNumber",tableNumber);

        long result = db.update("restaurant",values,"restaurantEmail = ?", new String[]{restaurantEmail});

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }
}
