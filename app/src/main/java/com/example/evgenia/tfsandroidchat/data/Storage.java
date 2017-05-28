package com.example.evgenia.tfsandroidchat.data;

import android.util.Log;

import com.example.evgenia.tfsandroidchat.presentation.dialogs_list.models.MessageModel;

import java.util.ArrayList;

/**
 * Created by User on 18.05.2017.
 */

public class Storage implements IStorage {
    private static final String TAG = "Storage";
    @Override
    public ArrayList<MessageModel> getMessages(long dialogId) {
        Log.d(TAG, "getMessages: thred name = " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return generateMessages(dialogId);
    }

    private ArrayList<MessageModel> generateMessages(long did){
        ArrayList<MessageModel> list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            list.add(new MessageModel(did, 23, "some funny text " + i, "smart author " + i, System.currentTimeMillis()));
        }

        return list;
    }

    @Override
    public String putMessageToDB(MessageModel model) {
        Log.d(TAG, "putMessageToDB: thread = " + Thread.currentThread().getName());
        return "result OK";
    }
}
