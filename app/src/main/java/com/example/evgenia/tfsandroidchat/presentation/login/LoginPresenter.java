package com.example.evgenia.tfsandroidchat.presentation.login;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.evgenia.tfsandroidchat.presentation.base_contract.IMainContract;

import java.lang.ref.WeakReference;

/**
 * Created by User on 17.05.2017.
 */

public class LoginPresenter implements IMainContract.ICachedPresenter, ILoginContract.ILoginPresenter{
    private static final String TAG = "LoginPresenter";

    public static final String key = "LoginPresenter";

    Bundle savedBundle = new Bundle();

    private WeakReference <IMainContract.IMainView> view;

    public LoginPresenter() {
        Log.d(TAG, "LoginPresenter: constructor");
    }

    @Override
    public void confirmUser(String login, String password) {
        savedBundle.putString("login", login);
        savedBundle.putString("password", password);
        Handler handler = new Handler();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: do some work in thread " + Thread.currentThread().getName());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Log.d(TAG, "run: done some work in thread " + Thread.currentThread().getName());

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(getView() != null){
                            ((ILoginContract.ILoginView)getView()).userConfirmed(true, login, password);
                            savedBundle.clear();
                        }else {
                            Log.d(TAG, "run: view detached");
                            savedBundle.putBoolean("result", true);
                        }
                    }
                });

            }
        }).start();
    }

    @Override
    public void restoreState() {

        if(!savedBundle.isEmpty()) {
            if (savedBundle.containsKey("result")) {
                if (getView() != null && getView() instanceof ILoginContract.ILoginView) {
                    Log.d(TAG, "restoreState: ");
                    ((ILoginContract.ILoginView) getView())
                            .userConfirmed(savedBundle.getBoolean("result"), savedBundle.getString("login"), savedBundle.getString("password"));
                    savedBundle.clear();
                }
            }
        }

    }


    @Override
    public <V extends IMainContract.IMainView> void attach(V viewRef) {
        Log.d(TAG, "attach: ");
        view = new WeakReference<IMainContract.IMainView>(viewRef);
    }

    @Override
    public void detach() {
        if(view != null){
            view.clear();
            view = null;
        }
    }

    @Override
    public <V extends IMainContract.IMainView> V getView() {
        if(view != null){
            return (V)view.get();
        }else
        return null;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void destroy() {
        savedBundle.clear();
    }

}
