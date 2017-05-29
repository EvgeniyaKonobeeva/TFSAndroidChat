package com.example.evgenia.tfsandroidchat.presentation.dialog_alone;

import com.example.evgenia.tfsandroidchat.data.storio.dao.MessageDao;
import com.example.evgenia.tfsandroidchat.presentation.base_contract.IMainContract;
import com.example.evgenia.tfsandroidchat.presentation.dialogs_list.models.MessageModel;

/**
 * Created by User on 28.05.2017.
 */

public interface IMessagesContract {

    interface IMessagesPresenter extends IMainContract.IMainPresenter{
        void sendMessage(MessageDao msg);
    }

    interface IMessagesView {
        void messageSendSuccess(String result);
    }
}
