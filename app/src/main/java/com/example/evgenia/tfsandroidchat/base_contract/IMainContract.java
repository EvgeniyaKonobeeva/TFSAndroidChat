package com.example.evgenia.tfsandroidchat.base_contract;

import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by User on 17.05.2017.
 */

public interface IMainContract {
    interface IMainPresenter {
        <V extends IMainView> void attach(V viewRef);
        void detach();
        <V extends  IMainView> V getView();
    }

    interface ICachedPresenter {
        String getKey();
        void destroy();
        void restoreState();
    }

    interface IMainView {
    }
}
