package com.example.evgenia.tfsandroidchat.data.storio.dao;

import com.example.evgenia.tfsandroidchat.data.storio.tables.DialogTable;
import com.example.evgenia.tfsandroidchat.data.storio.tables.MessageTable;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * Created by User on 28.05.2017.
 */
@StorIOSQLiteType(table = DialogTable.D_NAME)
public class DialogDao {

    @StorIOSQLiteColumn(name = DialogTable.COL_ID, key = true)
    Long id;

    @StorIOSQLiteColumn(name = DialogTable.COL_HEADER)
    String header;

    @StorIOSQLiteColumn(name = DialogTable.COL_DESCRIPTION)
    String description;

    @StorIOSQLiteColumn(name = DialogTable.COL_TIME)
    Long time;

    public DialogDao() {
    }

    public DialogDao(String header, String description, Long time) {
        this.header = header;
        this.description = description;
        this.time = time;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long id() {
        return id;
    }

    public String header() {
        return header;
    }

    public String description() {
        return description;
    }

    public Long time() {
        return time;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof DialogDao){
            DialogDao exter = (DialogDao) obj;
            if(exter.id == this.id){
                return true;
            }else return false;
        }else {
            return false;
        }
    }
}
