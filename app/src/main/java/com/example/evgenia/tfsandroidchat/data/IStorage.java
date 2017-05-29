package com.example.evgenia.tfsandroidchat.data;

import com.example.evgenia.tfsandroidchat.data.storio.dao.MessageDao;
import com.example.evgenia.tfsandroidchat.presentation.dialogs_list.models.MessageModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 18.05.2017.
 */

public interface IStorage {
    List<MessageDao> getMessages(long dialogId);

    String putMessageToDB(MessageDao messageDao);
}
