package com.healthyhooves.healthyhoovescalculator.databaseutils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.healthyhooves.healthyhoovescalculator.model.QuoteObj;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Graeme on 05/07/2016.
 */
public class QuotesDataSource {

    // Database fields
    private SQLiteDatabase database;
    private HHSQLiteHelper dbHelper;

    private String[] allColumns = {
            HHSQLiteHelper.COLUMN_ID,
            HHSQLiteHelper.COLUMN_DATE,
            HHSQLiteHelper.COLUMN_NUM_COWS,
            HHSQLiteHelper.COLUMN_BATH_CAP,
            HHSQLiteHelper.COLUMN_NUM_BATHS,
            HHSQLiteHelper.COLUMN_NUM_LTRS,
            HHSQLiteHelper.COLUMN_CHANGE_BATHS,
            HHSQLiteHelper.COLUMN_LTRS_HH,
            HHSQLiteHelper.COLUMN_KG_SUO4
    };

    public QuotesDataSource(final Context context) {
        dbHelper = new HHSQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public QuoteObj createQuote(final String date, final int numCows, final int bathCap, final int numBaths,
                                final float numLtrs, final float bathsBeforeChange,
                                final float ltrsHH, final float kgCuSO4) {
        ContentValues values = new ContentValues();
        values.put(HHSQLiteHelper.COLUMN_DATE, date);
        values.put(HHSQLiteHelper.COLUMN_NUM_COWS, numCows);
        values.put(HHSQLiteHelper.COLUMN_BATH_CAP, bathCap);
        values.put(HHSQLiteHelper.COLUMN_NUM_BATHS, numBaths);
        values.put(HHSQLiteHelper.COLUMN_NUM_LTRS, numLtrs);
        values.put(HHSQLiteHelper.COLUMN_CHANGE_BATHS, bathsBeforeChange);
        values.put(HHSQLiteHelper.COLUMN_LTRS_HH, ltrsHH);
        values.put(HHSQLiteHelper.COLUMN_KG_SUO4, kgCuSO4);
        long insertId = database.insert(HHSQLiteHelper.TABLE_OFFERS, null, values);
        Cursor cursor = database.query(HHSQLiteHelper.TABLE_OFFERS, allColumns,
                HHSQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        QuoteObj newQuote = cursorToQuote(cursor);
        cursor.close();
        return newQuote;
    }

    public void deleteQuote(final QuoteObj quote) {
        long id = quote.getId();
        database.delete(HHSQLiteHelper.TABLE_OFFERS, HHSQLiteHelper.COLUMN_ID + " = " + id, null);
    }

    public List<QuoteObj> getAllQuotes() {
        List<QuoteObj> quotes = new ArrayList<>();

        Cursor cursor = database.query(HHSQLiteHelper.TABLE_OFFERS, allColumns,
                null, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            QuoteObj quote = cursorToQuote(cursor);
            quotes.add(quote);
            cursor.moveToNext();
        }

        cursor.close();
        return quotes;
    }

    private QuoteObj cursorToQuote(final Cursor cursor) {

        return new QuoteObj.Builder()
                .setId(cursor.getLong(0))
                .setDate(cursor.getString(1))
                .setNumCows(cursor.getInt(2))
                .setBathCap(cursor.getInt(3))
                .setNumBaths(cursor.getInt(4))
                .setNumLtrs(cursor.getFloat(5))
                .setBathsBeforeChange(cursor.getFloat(6))
                .setLtrsHH(cursor.getFloat(7))
                .setKgCuSO4(cursor.getFloat(8))
                .build();
    }
}
