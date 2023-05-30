package com.example.waiterapp.databaseclasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductDatabaseHelper extends DatabaseHelper{
    public ProductDatabaseHelper(Context context) {
        super(context);
    }

    public boolean isProductInserted(String pName, String pPrice,int pQuantity,String pCategory,String pDescription, CircleImageView pImage){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("productName", pName);
        values.put("productPrice",pPrice);
        values.put("productQuantity",pQuantity);
        values.put("productCategoty",pCategory);
        values.put("productCategotyId",pDescription);
        values.put("productImage", circleImageViewToByte(pImage));

        long results = db.insert("products",null,values);

        if (results == -1){
            return false;
        }else {
            return true;
        }

    }

    private byte[] circleImageViewToByte(CircleImageView circleImageView) {
        Bitmap bitmap = ((BitmapDrawable)circleImageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,80,stream);
        byte[] bytes = stream.toByteArray();

        return bytes;
    }
}
