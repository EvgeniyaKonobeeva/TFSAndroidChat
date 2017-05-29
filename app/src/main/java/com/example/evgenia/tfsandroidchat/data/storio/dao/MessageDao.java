package com.example.evgenia.tfsandroidchat.data.storio.dao;

import com.example.evgenia.tfsandroidchat.data.storio.tables.MessageTable;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * Created by User on 28.05.2017.
 */
@StorIOSQLiteType(table = MessageTable.D_NAME)
public class MessageDao {

    @StorIOSQLiteColumn(name = MessageTable.COL_ID, key = true)
    Long id;

    @StorIOSQLiteColumn(name = MessageTable.COL_TEXT)
    String text;

    @StorIOSQLiteColumn(name = MessageTable.COL_DIALOG_ID)
    Long dialogId;

    @StorIOSQLiteColumn(name = MessageTable.COL_AUTHOR_ID)
    Long authorId;

    @StorIOSQLiteColumn(name = MessageTable.COL_TIME)
    Long time;

    public MessageDao() {
    }

    public MessageDao(String text, Long dialogId, Long authorId, Long time) {
        this.text = text;
        this.dialogId = dialogId;
        this.authorId = authorId;
        this.time = time;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDialogId(Long dialogId) {
        this.dialogId = dialogId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long id(){return id;}

    public String text() {
        return text;
    }

    public Long dialogId() {
        return dialogId;
    }

    public Long authorId() {
        return authorId;
    }

    public Long time() {
        return time;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj instanceof MessageDao){
            MessageDao exter = (MessageDao) obj;
            if(exter.id == this.id){
                return true;
            }else return false;
        }else {
            return false;
        }
    }
}
