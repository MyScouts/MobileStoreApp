package com.example.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.doanandroid.R;

public class MobileDatabase extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Mobile_db.sqlite";
    public static final String TABLE_NAME = "Mobile";
    public static final String COL_MOBILE_ID = "MobileId";
    public static final String COL_MOBILE_NAME = "MobileName";
    public static final String COL_MOBILE_BRAND = "MobileBrand";
    public static final String COL_MOBILE_IMAGE = "MobileImage";
    public static final String COL_MOBILE_SCREEN_SIZE = "MobileScreenSize";
    public static final String COL_MOBILE_CATEGORY = "MobileCategory";
    public static final String COL_MOBILE_CPU = "MobileCPU";
    public static final String COL_MOBILE_STORAGE = "MobileStorage";
    public static final String COL_MOBILE_RAM = "MobileRam";
    public static final String COL_MOBILE_PIN = "MobilePin";
    public static final String COL_MOBILE_PRICE = "MobilePrice";


    public MobileDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String script = "CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME + " ("
                + COL_MOBILE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_MOBILE_NAME + " VARCHAR(200), "
                + COL_MOBILE_BRAND + " VARCHAR(200), "
                + COL_MOBILE_IMAGE + " INTEGER, "
                + COL_MOBILE_SCREEN_SIZE + " VARCHAR(200), "
                + COL_MOBILE_CATEGORY + " VARCHAR(200), "
                + COL_MOBILE_CPU + " VARCHAR(200), "
                + COL_MOBILE_STORAGE + " VARCHAR(200), "
                + COL_MOBILE_RAM + " VARCHAR(200), "
                + COL_MOBILE_PIN + " VARCHAR(200), "
                + COL_MOBILE_PRICE + " REAL)";
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

    public void createSomeDefaultProduct() {
        int iphone_13_promax = R.drawable.iphone_13_promax;
        int iphone_13_pro = R.drawable.iphone_13_pro;
        int iphone_13 = R.drawable.iphone_13;
        int iphone_13_mni = R.drawable.iphone_13_mni;

        int samsung_galaxy_note20_ultra = R.drawable.samsung_galaxy_note20_ultra;
        int samsung_galaxy_a52s_5g = R.drawable.samsung_galaxy_a52s_5g;
        int samsung_galaxy_a52 = R.drawable.samsung_galaxy_a52;

        int oppo_reno6_z = R.drawable.oppo_reno6_z;
        int oppo_a94 = R.drawable.oppo_a94;
        int oppo_a16k = R.drawable.oppo_a16k;
        int oppo_find_x3_pro_12 = R.drawable.oppo_find_x3_pro_12;

        int xiaomi_11_lite_5g_ne = R.drawable.xiaomi_11_lite_5g_ne;
        int xiaomi_11t_7 = R.drawable.xiaomi_11t_7;
        int poco_x3_pro_4 = R.drawable.poco_x3_pro_4;
        int xiaomi_redmi_note_10_pro = R.drawable.xiaomi_redmi_note_10_pro;

        // init data
        int count = this.getCountOfMobile();
        if (count == 0) {
            this.queryData("INSERT INTO " + TABLE_NAME + " VALUES(null, 'Iphone 13 Pro Max', 'IPhone', " + iphone_13_promax + ", '6.7 Inch', 'Mobile', 'A15 Bionic', '256 GB', '6 GB', '4352 mAh', 34490000)");
            this.queryData("INSERT INTO " + TABLE_NAME + " VALUES(null, 'Iphone 13 Pro', 'IPhone', " + iphone_13_pro + ", '6.1 Inch', 'Mobile', 'A15 Bionic', '128 GB', '6 GB', '3095  mAh', 29490000)");
            this.queryData("INSERT INTO " + TABLE_NAME + " VALUES(null, 'Iphone 13', 'IPhone', " + iphone_13 + ", '6.1 Inch', 'Mobile', 'A15 Bionic', '256 GB', '4 GB', '3225 mAh', 23490000)");
            this.queryData("INSERT INTO " + TABLE_NAME + " VALUES(null, 'Iphone 13 Mini', 'IPhone', " + iphone_13_mni + ", '5.4 Inch', 'Mobile', 'A15 Bionic', '256 GB', '4 GB', '2406 mAh', 18990000)");

            this.queryData("INSERT INTO " + TABLE_NAME + " VALUES(null, 'Samsung Galaxy Note 20 Ultra ', 'Samsung', " + samsung_galaxy_note20_ultra + ", '6.9 Inch', 'Mobile', 'Exynos 990', '256 GB', '8 GB', '4500 mAh', 18990000)");
            this.queryData("INSERT INTO " + TABLE_NAME + " VALUES(null, 'Samsung Galaxy A52s 5G', 'Samsung', " + samsung_galaxy_a52 + ", '6.5 Inch', 'Mobile', 'Snapdragon 778G', '128 GB', '8 GB', '4500 mAh', 10190000)");
            this.queryData("INSERT INTO " + TABLE_NAME + " VALUES(null, 'Samsung Galaxy A12 4GB-128GB', 'Samsung', " + samsung_galaxy_a52s_5g + ", '16 Inch', 'Mobile', 'Helio G35/Exynos 850', '128 GB', '4 GB', '5000 mAh', 3890000)");
            this.queryData("INSERT INTO " + TABLE_NAME + " VALUES(null, 'Samsung Galaxy A52', 'Samsung', " + samsung_galaxy_a52 + ", '6.5 Inch', 'Mobile', 'Snapdragon 720G', '128 GB', '8 GB', '4500 mAh', 8590000)");

            this.queryData("INSERT INTO " + TABLE_NAME + " VALUES(null, 'OPPO Reno6 Z 5G', 'Oppo', " + oppo_reno6_z + ", '6.43 Inch', 'Mobile', 'MediaTek Dimensity 800U', '128 GB', '8 GB', '4310 mAh', 9490000)");
            this.queryData("INSERT INTO " + TABLE_NAME + " VALUES(null, 'OPPO A94 8GB-128GB', 'Oppo', " + oppo_a94 + ", '9.43 Inch', 'Mobile', 'Helio P95', '256 GB', '8 GB', '4310 mAh', 6590000)");
            this.queryData("INSERT INTO " + TABLE_NAME + " VALUES(null, 'OPPO A16k 3GB-32GB', 'Oppo', " + oppo_a16k + ", '6.52 Inch', 'Mobile', 'Mediatek Helio G35', '256 GB', '8 GB', '4230 mAh', 3690000)");
            this.queryData("INSERT INTO " + TABLE_NAME + " VALUES(null, 'OPPO Find X3 Pro 5G', 'Oppo', " + oppo_find_x3_pro_12 + ", '6.7 Inch', 'Mobile', 'Snapdragon 888', '256 GB', '12 GB', '4500 mAh', 21990000)");

            this.queryData("INSERT INTO " + TABLE_NAME + " VALUES(null, 'Xiaomi 11 Lite 5G NE 8GB - 256GB', 'Xiaomi', " + xiaomi_11_lite_5g_ne + ", '6.55 Inch', 'Mobile', 'Snapdragon 778G', '256 GB', '8 GB', '4250 mAh', 8490000)");
            this.queryData("INSERT INTO " + TABLE_NAME + " VALUES(null, 'Xiaomi 11T 8GB - 128GB', 'Xiaomi', " + xiaomi_11t_7 + ", '6.67 Inch', 'Mobile', 'MediaTek Dimensity 1200U', '128 GB', '8 GB', '5000 mAh', 9990000)");
            this.queryData("INSERT INTO " + TABLE_NAME + " VALUES(null, 'Xiaomi POCO X3 Pro NFC 8GB-256GB', 'Xiaomi', " + poco_x3_pro_4 + ", '6.67 Inch', 'Mobile', 'Adreno 640', '256 GB', '8 GB', '5160 mAh', 7290000)");
            this.queryData("INSERT INTO " + TABLE_NAME + " VALUES(null, 'Xiaomi Redmi Note 10 Pro 8GB-128GB', 'Xiaomi', " + xiaomi_redmi_note_10_pro + ", '6.67 Inch', 'Mobile', 'Adreno 618', '128 GB', '8 GB', '5020 mAh', 7490000)");
        }
    }
}
