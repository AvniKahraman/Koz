package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyDatabase.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE MyTable (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ad TEXT" +
                ");";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS MyTable");
        onCreate(db);
    }

    public void veriEkle(String ad) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ad", ad);
        db.insert("MyTable", null, values);
        db.close();
    }
    public void kayitSil(String ad) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete("MyTable", "ad=?", new String[]{ad});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }


    public ArrayList<String> getTumKayitlar() {
        ArrayList<String> kayitlar = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sorgu = "SELECT * FROM MyTable";
        Cursor cursor = db.rawQuery(sorgu, null);

        if (cursor.moveToFirst()) {
            do {
                String ad = cursor.getString(cursor.getColumnIndex("ad"));
                kayitlar.add(ad);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return kayitlar;
    }


}

