package com.example.evgenia.tfsandroidchat.login;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import static com.example.evgenia.tfsandroidchat.login.LoginActivity.KEY_LOGIN;
import static com.example.evgenia.tfsandroidchat.login.LoginActivity.KEY_PASSWORD;

/**
 * Created by User on 01.05.2017.
 */

public class VerifyLoginLoader extends AsyncTaskLoader<Boolean> {

    String password;
    String login;

    private static final String TAG = "VerifyLoginLoader";

    public VerifyLoginLoader(Context context, Bundle bundle) {
        super(context);

        password = bundle.getString(KEY_PASSWORD);
        login = bundle.getString(KEY_LOGIN);

        Log.d(TAG, "VerifyLoginLoader: ");
    }

    @Override
    public Boolean loadInBackground() {
        Log.d(TAG, "loadInBackground: ");

        return true;
    }

    @Override
    protected boolean onCancelLoad() {
        Log.d(TAG, "onCancelLoad: ");

        return super.onCancelLoad();
    }

    @Override
    public void onCanceled(Boolean data) {
        Log.d(TAG, "onCanceled: ");
        super.onCanceled(data);
    }


    @Override
    public void cancelLoadInBackground() {
        Log.d(TAG, "cancelLoadInBackground: ");
        super.cancelLoadInBackground();
    }

    @Override
    public void deliverResult(Boolean data) {
        Log.d(TAG, "deliverResult: ");
        super.deliverResult(data);
    }

    @Override
    public void deliverCancellation() {
        Log.d(TAG, "deliverCancellation: ");
        super.deliverCancellation();
    }

    @Override
    public void registerListener(int id, OnLoadCompleteListener listener) {
        Log.d(TAG, "registerListener: ");
        super.registerListener(id, listener);
    }

    @Override
    protected void onStartLoading() {
        Log.d(TAG, "onStartLoading: ");
        try {
            TimeUnit.SECONDS.sleep(10);
            Log.d(TAG, "onStartLoading: login=" + login);
            Log.d(TAG, "onStartLoading: password=" + password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.onStartLoading();
    }

    @Override
    public void stopLoading() {
        Log.d(TAG, "stopLoading: ");
        super.stopLoading();
    }

    @Override
    protected void onStopLoading() {
        Log.d(TAG, "onStopLoading: ");
        super.onStopLoading();
    }

    @Override
    public void abandon() {
        Log.d(TAG, "abandon: ");
        super.abandon();
    }

    @Override
    protected void onAbandon() {
        Log.d(TAG, "onAbandon: ");
        super.onAbandon();
    }

    @Override
    public void reset() {
        Log.d(TAG, "reset: ");
        super.reset();
    }

    @Override
    protected void onReset() {
        Log.d(TAG, "onReset: ");
        super.onReset();
    }
}
