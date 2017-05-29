package com.example.evgenia.tfsandroidchat.data.storio.tables;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by User on 28.05.2017.
 */

public class MessageTable {

    public static final String D_NAME = "MESSAGES";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({COL_ID, COL_TEXT, COL_AUTHOR_ID, COL_TIME, COL_DIALOG_ID})

    public @interface DialogColumns{}

    public static final String COL_ID = "_id";
    public static final String COL_TEXT = "text";
    public static final String COL_AUTHOR_ID = "author_id";
    public static final String COL_TIME = "time";
    public static final String COL_DIALOG_ID = "dialog_id";

    public static String columnFullName(@DialogTable.DialogColumns String col){
        return D_NAME + "." + col;
    }

    public static String createTable(){
        return "CREATE TABLE " + D_NAME + "("
                + COL_ID + " INTEGER NOT NULL PRIMARY KEY, "
                + COL_TEXT + " TEXT NOT NULL, "
                + COL_AUTHOR_ID + " INTEGER NOT NULL, "
                + COL_DIALOG_ID + " INTEGER NOT NULL, "
                + COL_TIME + " INTEGER NOT NULL );";
    }


}
