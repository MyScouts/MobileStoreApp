package com.example.database;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AccountDatabase extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "account_db.sqlite";
    public static final String TABLE_NAME = "Account";
    public static final String COL_ACCOUNT_ID = "AccountId";
    public static final String COL_EMAIL = "Email";
    public static final String COL_PASSWORD = "Password";
    public static final String COL_DISPLAY_NAME = "DisplayName";
    SQLiteDatabase db;

    public AccountDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String script = "CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME + "("
                + COL_ACCOUNT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_EMAIL + " VARCHAR(50), "
                + COL_PASSWORD + " VARCHAR(50), "
                + COL_DISPLAY_NAME + " VARCHAR(50))";
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

    public int getCountOfAccount() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


    public void createSomeDefaultAccount() {
        int count = this.getCountOfAccount();
        if (count == 0) {
            this.queryData("INSERT INTO " + TABLE_NAME + " VALUES(null, 'nhom3@gmail.com', '111111', 'Nhom 3')");
            this.queryData("INSERT INTO " + TABLE_NAME + " VALUES(null, '1', '1', '1')");
        }
    }
}