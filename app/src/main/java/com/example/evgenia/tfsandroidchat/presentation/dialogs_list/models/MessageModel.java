package com.example.evgenia.tfsandroidchat.presentation.dialogs_list.models;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Evgenia on 30.03.2017.
 */

public class MessageModel implements Parcelable {
    private long dialogId;
    private int msgId;
    private String text;
    private String author;
    private long date;

    public MessageModel() {
    }

    public MessageModel(long dialogId, int msgId, String text, String author, long date) {
        this.dialogId = dialogId;
        this.msgId = msgId;
        this.text = text;
        this.author = author;
        this.date = date;
    }

    public long getDialogId() {
        return dialogId;
    }

    public void setDialogId(long dialogId) {
        this.dialogId = dialogId;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    protected MessageModel(Parcel in) {
            dialogId = in.readLong();
            msgId = in.readInt();
            text = in.readString();
            author = in.readString();
            date = in.readLong();
    }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(dialogId);
            dest.writeInt(msgId);
            dest.writeString(text);
            dest.writeString(author);
            dest.writeLong(date);
        }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<MessageModel> CREATOR = new Parcelable.Creator<MessageModel>() {
            @Override
            public MessageModel createFromParcel(Parcel in) {
                return new MessageModel(in);
            }

            @Override
            public MessageModel[] newArray(int size) {
                return new MessageModel[size];
            }
        };
    }