package com.example.evgenia.tfsandroidchat.data.storio.tables;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.example.evgenia.tfsandroidchat.data.storio.tables.DialogTable.COL_DESCRIPTION;
import static com.example.evgenia.tfsandroidchat.data.storio.tables.DialogTable.COL_HEADER;
import static com.example.evgenia.tfsandroidchat.data.storio.tables.DialogTable.COL_ID;
import static com.example.evgenia.tfsandroidchat.data.storio.tables.DialogTable.COL_TIME;

/**
 * Created by User on 28.05.2017.
 */

public class DialogTable {

    public static final String D_NAME = "dialogs";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({COL_ID, COL_HEADER, COL_DESCRIPTION, COL_TIME})

    public @interface DialogColumns{}

    public static final String COL_ID = "_id";
    public static final String COL_HEADER = "header";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_TIME = "time";

    public static String columnFullName(@DialogColumns String col){
        return D_NAME + "." + col;
    }

    public static String createTable(){
        return "CREATE TABLE " + D_NAME + "("
                + COL_ID + "INTEGER NOT NULL PRIMARY KEY, "
                + COL_HEADER + " TEXT NOT NULL UNIQUE, "
                + COL_DESCRIPTION + " TEXT NOT NULL, "
                + COL_TIME + " INTEGER NOT NULL );";
    }


}
