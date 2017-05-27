package com.example.evgenia.tfsandroidchat.data;

import com.example.evgenia.tfsandroidchat.presentation.dialogs_list.models.MessageModel;

import java.util.ArrayList;

/**
 * Created by User on 18.05.2017.
 */

public interface IStorage {
    ArrayList<MessageModel> getMessages(int dialogId);
}
