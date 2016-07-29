package com.healthyhooves.healthyhoovescalculator.databaseutils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class HHSQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_OFFERS = "offers";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DATE = "column_date";
    public static final String COLUMN_NUM_COWS = "column_num_cows";
    public static final String COLUMN_BATH_CAP = "column_bath_cap";
    public static final String COLUMN_NUM_BATHS = "column_num_baths";
    public static final String COLUMN_NUM_LTRS = "column_num_ltrs";
    public static final String COLUMN_CHANGE_BATHS = "column_change_baths";
    public static final String COLUMN_LTRS_HH = "column_ltrs_hh";
    public static final String COLUMN_KG_SUO4 = "column_kg_suo4";

    private static final String DATABASE_NAME = "offers.db";
    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_CREATE = "create table "
            + TABLE_OFFERS + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_DATE + " text, "
            + COLUMN_NUM_COWS + " integer, "
            + COLUMN_BATH_CAP + " integer, "
            + COLUMN_NUM_BATHS + " integer, "
            + COLUMN_NUM_LTRS + " real, "
            + COLUMN_CHANGE_BATHS + " real, "
            + COLUMN_LTRS_HH + " real, "
            + COLUMN_KG_SUO4 + " real);";

    public HHSQLiteHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(final SQLiteDatabase database,
                          final int oldVersion, final int newVersion) {

    }

    public void deleteDatabase(Context context) {
        context.deleteDatabase("offers.db");
    }
}
