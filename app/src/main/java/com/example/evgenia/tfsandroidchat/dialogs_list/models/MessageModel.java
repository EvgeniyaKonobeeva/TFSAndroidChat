package com.example.evgenia.tfsandroidchat.dialogs_list.models;


/**
 * Created by Evgenia on 30.03.2017.
 */

public class MessageModel {
    private String authorLogin;
    private long date;
    private String text;
    private boolean isRead;

    public MessageModel(String authorLogin, long date, String text) {
        this.authorLogin = authorLogin;
        this.date = date;
        this.text = text;
    }

    public String getAuthorLogin() {
        return authorLogin;
    }

    public void setAuthorLogin(String authorLogin) {
        this.authorLogin = authorLogin;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
