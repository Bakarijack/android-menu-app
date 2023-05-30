package com.example.waiterapp.databaseclasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class FoodCategoryDatabaseHelper extends DatabaseHelper{
    public FoodCategoryDatabaseHelper(Context context) {
        super(context);
    }

    public boolean isCategoryExist(String categoryN){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from foodcategory where categoryName = '"+categoryN+"'",null);

        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

    public boolean isCategoryInserted(String categoryN){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("categoryName",categoryN);

        long result = db.insert("foodcategory",null,contentValues);

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Cursor getCategoryId(String category){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from foodcategory where categoryName = '"+category+"'",null);

        return cursor;
    }

    public Cursor getFoodCategories(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from foodcategory ",null);
        return cursor;
    }
}
