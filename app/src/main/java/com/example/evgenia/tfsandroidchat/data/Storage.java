package com.example.evgenia.tfsandroidchat.data;

import android.util.Log;

import com.example.evgenia.tfsandroidchat.MyApp;
import com.example.evgenia.tfsandroidchat.data.storio.dao.MessageDao;
import com.example.evgenia.tfsandroidchat.data.storio.tables.MessageTable;
import com.example.evgenia.tfsandroidchat.presentation.dialogs_list.models.MessageModel;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.queries.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 18.05.2017.
 */

public class Storage implements IStorage {
    private static final String TAG = "Storage";
    private StorIOSQLite storIOSQLite = MyApp.provideStorIOSQLite();

    @Override
    public List<MessageDao> getMessages(long dialogId) {
        Log.d(TAG, "getMessages: thred name = " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<MessageDao> resultList = MyApp.provideStorIOSQLite().get()
                                            .listOfObjects(MessageDao.class)
                                            .withQuery(Query.builder()
                                                            .table(MessageTable.D_NAME)
                                                            .where(MessageTable.COL_DIALOG_ID + " = ? ")
                                                            .whereArgs(dialogId)
                                                            .orderBy(MessageTable.COL_TIME + " desc ")
                                                            .build())
                                            .prepare()
                                            .executeAsBlocking();

        return resultList;
    }

    private ArrayList<MessageModel> generateMessages(long did){
        ArrayList<MessageModel> list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            list.add(new MessageModel(did, 23, "some funny text " + i, "smart author " + i, System.currentTimeMillis()));
        }

        return list;
    }

    @Override
    public String putMessageToDB(MessageDao model) {
        Log.d(TAG, "putMessageToDB: thread = " + Thread.currentThread().getName());
        MyApp.provideStorIOSQLite().put()
                .object(model)
                .prepare()
                .executeAsBlocking();
        return "result OK";
    }
}
