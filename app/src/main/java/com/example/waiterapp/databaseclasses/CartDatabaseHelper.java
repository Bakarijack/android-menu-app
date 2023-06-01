package com.example.waiterapp.databaseclasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class CartDatabaseHelper extends DatabaseHelper{
    public CartDatabaseHelper(Context context) {
        super(context);
    }

    public boolean isItemInserted(String cartItemName, String cartItemPrice, int cartItemQuantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("cartItemName",cartItemName);
        contentValues.put("cartItemPrice",cartItemPrice);
        contentValues.put("cartItemQuantity",cartItemQuantity);

        long result = db.insert("cartItem",null,contentValues);

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }


    public boolean deleteCartItem(String cartItemName){
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete("cartItem","cartItemName = ?",new String[]{cartItemName});

        db.close();
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean deleteAllCartItems(){
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete("cartItem",null,null);

        db.close();
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean updateCartItem(String cartItemName, String cartItemPrice, int cartItemQuantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("cartItemPrice",cartItemPrice);
        values.put("cartItemQuantity",cartItemQuantity);

        long result = db.update("cartItem",values," cartItemName = ?",new String[]{cartItemName});

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }
}
