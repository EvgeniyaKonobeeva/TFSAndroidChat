package com.example.evgenia.tfsandroidchat.data.storio;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.evgenia.tfsandroidchat.data.storio.tables.DialogTable;
import com.example.evgenia.tfsandroidchat.data.storio.tables.MessageTable;

/**
 * Created by User on 28.05.2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "DBHelper";

    public static final String dbName = "CHAT_DATABASE";
    private static final int version = 1;

    public DBHelper(Context context){
        super(context, dbName, null, version);
        Log.d(TAG, "DBHelper: ");
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MessageTable.createTable());
        db.execSQL(DialogTable.createTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
