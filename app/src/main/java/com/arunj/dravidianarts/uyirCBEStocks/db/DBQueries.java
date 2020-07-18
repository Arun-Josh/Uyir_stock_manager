package com.arunj.dravidianarts.uyirCBEStocks.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.arunj.dravidianarts.uyirCBEStocks.model.Users;

import java.util.ArrayList;

public class DBQueries {

    private Context context;
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public DBQueries(Context context) {
        this.context = context;
    }

    public DBQueries open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    // Users
    public boolean insertUser(Users users) {
        ContentValues values = new ContentValues();
        values.put(DBConstants.STOCK_DATE, users.getDate());
        values.put(DBConstants.STOCK_NAME, users.getName());
        values.put(DBConstants.STOCK_PRODUCT, users.getProduct());
        values.put(DBConstants.STOCK_CODE_NO, users.getCodeNo());
        values.put(DBConstants.STOCK_BATCH, users.getBatch());
        values.put(DBConstants.STOCK_TOTAL,users.getTotal());
        return database.insert(DBConstants.STOCK_TABLE, null, values) > -1;
    }

    public ArrayList<Users> readUsers() {
        ArrayList<Users> list = new ArrayList<>();
        try {
            Cursor cursor;
            database = dbHelper.getReadableDatabase();
            cursor = database.rawQuery(DBConstants.SELECT_QUERY, null);
            list.clear();
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        String date = cursor.getString(cursor.getColumnIndex(DBConstants.STOCK_DATE));
                        String name = cursor.getString(cursor.getColumnIndex(DBConstants.STOCK_NAME));
                        String product = cursor.getString(cursor.getColumnIndex(DBConstants.STOCK_PRODUCT));
                        String codeNo = cursor.getString(cursor.getColumnIndex(DBConstants.STOCK_CODE_NO));
                        String batch = cursor.getString(cursor.getColumnIndex(DBConstants.STOCK_BATCH));
                        String total = cursor.getString(cursor.getColumnIndex(DBConstants.STOCK_TOTAL));
                        Users users = new Users(date, name, product, codeNo, batch, total);
                        list.add(users);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
        } catch (Exception e) {
            Log.v("Exception", e.getMessage());
        }
        return list;
    }

}
