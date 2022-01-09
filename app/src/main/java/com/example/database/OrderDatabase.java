package com.example.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class OrderDatabase extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "order_db.sqlite";
    public static final String TABLE_NAME = "OrderTable";
    public static final String COL_ORDER_ID = "OrderId";
    public static final String COL_ACCOUNT_ID = "AccountId";
    public static final String COL_MOBILE_ID = "MobileId";
    public static final String COL_MOBILE_NAME = "MobileName";
    public static final String COL_MOBILE_PRICE = "MobilePrice";
    public static final String COL_NUM = "Num";

    public OrderDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String script = "CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME + "("
                + COL_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_ACCOUNT_ID + " INTEGER, "
                + COL_MOBILE_ID + " INTEGER, "
                + COL_MOBILE_NAME + " VARCHAR(200), "
                + COL_MOBILE_PRICE + " REAL,"
                + COL_NUM + " INTEGER) ";
        sqLiteDatabase.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void queryData(String sql) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public Cursor getData(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);
    }

    public int getCountOfMobile() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }
}


