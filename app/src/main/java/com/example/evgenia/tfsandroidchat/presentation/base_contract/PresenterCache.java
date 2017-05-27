package com.example.evgenia.tfsandroidchat.presentation.base_contract;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 17.05.2017.
 */

public class PresenterCache {
    private static PresenterCache ourInstance;

    private Map<String, IMainContract.ICachedPresenter> presenterMap = new HashMap<>();

    public static PresenterCache getInstance() {
        if (ourInstance == null) {
            return ourInstance = new PresenterCache();
        }else
            return ourInstance;
    }

    private PresenterCache() {
    }


    public void putPresenter(IMainContract.ICachedPresenter presenter){
        removePresenter(presenter.getKey());
        presenterMap.put(presenter.getKey(), presenter);
    }

    public IMainContract.ICachedPresenter getPresenter(String key){
        return presenterMap.get(key);
    }

    public void removePresenter(String key){
        IMainContract.ICachedPresenter presenter = presenterMap.get(key);
        if (presenter != null) {
            presenter.destroy();
        }

    }
}
