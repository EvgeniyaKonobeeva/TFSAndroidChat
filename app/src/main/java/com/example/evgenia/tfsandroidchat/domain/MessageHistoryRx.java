package com.example.evgenia.tfsandroidchat.domain;

import com.example.evgenia.tfsandroidchat.data.Data;
import com.example.evgenia.tfsandroidchat.data.storio.dao.MessageDao;
import com.example.evgenia.tfsandroidchat.presentation.dialogs_list.models.MessageModel;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by User on 18.05.2017.
 */

public class MessageHistoryRx {

    public static Observable<List<MessageDao>> getMessages(int dialogId){
        return Observable.just(dialogId).map(Data.getMessages()::getMessages);
    }

}
