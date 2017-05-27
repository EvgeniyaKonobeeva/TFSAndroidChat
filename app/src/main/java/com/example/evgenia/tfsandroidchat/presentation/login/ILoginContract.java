package com.example.evgenia.tfsandroidchat.presentation.login;

import com.example.evgenia.tfsandroidchat.presentation.base_contract.IMainContract;

/**
 * Created by User on 17.05.2017.
 */

public interface ILoginContract {

    interface ILoginPresenter extends IMainContract.IMainPresenter{
        void confirmUser(String login, String password);
    }

    interface ILoginView extends IMainContract.IMainView{
        void userConfirmed(boolean isCorrect, String login, String password);
    }
}
