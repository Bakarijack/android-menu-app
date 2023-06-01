package com.example.waiterapp.databaseclasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class CartDatabaseHelper extends DatabaseHelper{
    public CartDatabaseHelper(Context context) {
        super(context);
    }


    public boolean isCartContainAnyItem(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from cartItem",null);

        if (cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }


    private byte[] imageViewToByte(ImageView imageView) {
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,80,stream);
        byte[] bytes = stream.toByteArray();

        return bytes;
    }



    public Cursor getAllCartItems(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from cartItem",null);
        return cursor;
    }

    public boolean isItemInserted(String cartItemName, String cartItemPrice, int cartItemQuantity,String cartItemImage){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("cartItemName",cartItemName);
        contentValues.put("cartItemPrice",cartItemPrice);
        contentValues.put("cartItemQuantity",cartItemQuantity);
        contentValues.put("cartItemImage",cartItemImage);

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


    public int getCartItemsCount(){
        String countQuery = "select * from cartItem";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


    public boolean isItemExistInCart(String cartItemName){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from cartItem where cartItemName = ?", new String[] {cartItemName});

        if(cursor.getCount() > 0)
            return true;
        else
            return false;
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
