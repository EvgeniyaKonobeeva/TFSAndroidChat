package com.example.evgenia.tfsandroidchat;

import android.app.Application;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.evgenia.tfsandroidchat.data.storio.DBHelper;
import com.example.evgenia.tfsandroidchat.data.storio.dao.DialogDao;
import com.example.evgenia.tfsandroidchat.data.storio.dao.DialogDaoSQLiteTypeMapping;
import com.example.evgenia.tfsandroidchat.data.storio.dao.MessageDao;
import com.example.evgenia.tfsandroidchat.data.storio.dao.MessageDaoSQLiteTypeMapping;
import com.facebook.stetho.Stetho;
import com.pushtorefresh.storio.sqlite.SQLiteTypeMapping;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;

/**
 * Created by User on 28.05.2017.
 */

public class MyApp extends Application {
    private DBHelper dbHelper;
    private static StorIOSQLite storIOSQLite;




    public static StorIOSQLite provideStorIOSQLite() {
        return storIOSQLite;
    }

    public DBHelper getDbHelper(){
        return dbHelper;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        dbHelper = new DBHelper(getApplicationContext());

        storIOSQLite = DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(dbHelper)
                .addTypeMapping(MessageDao.class, new MessageDaoSQLiteTypeMapping())
                .addTypeMapping(DialogDao.class, new DialogDaoSQLiteTypeMapping())
                .build();

        Stetho.initializeWithDefaults(this);
    }
}

