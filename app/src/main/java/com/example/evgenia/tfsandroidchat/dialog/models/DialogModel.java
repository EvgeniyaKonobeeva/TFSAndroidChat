package com.example.evgenia.tfsandroidchat.dialog.models;

import java.util.ArrayList;

/**
 * Created by Evgenia on 30.03.2017.
 */

public class DialogModel {
    private String[] participants; // if(participants.length > 2) => group chat
    private ArrayList<MessageModel> messagesList;

    public DialogModel(ArrayList<MessageModel> messagesList) {
        this.messagesList = messagesList;
    }

    public ArrayList<MessageModel> getMessagesList() {
        return messagesList;
    }

    public void setMessagesList(ArrayList<MessageModel> messagesList) {
        this.messagesList = messagesList;
    }
}
