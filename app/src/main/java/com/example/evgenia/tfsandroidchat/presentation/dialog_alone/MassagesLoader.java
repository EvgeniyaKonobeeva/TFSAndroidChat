package com.example.evgenia.tfsandroidchat.presentation.dialog_alone;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;

import com.example.evgenia.tfsandroidchat.data.Data;
import com.example.evgenia.tfsandroidchat.domain.MessageHistoryRx;
import com.example.evgenia.tfsandroidchat.presentation.dialogs_list.models.MessageModel;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;



/**
 * Created by User on 17.05.2017.
 */

public class MassagesLoader extends AsyncTaskLoader<ArrayList> {
    private static final String TAG = "MassagesLoader";

    private int dialogId;

    public MassagesLoader(Context context, Bundle bundle) {
        super(context);
        if(!bundle.isEmpty()){
            dialogId = bundle.getInt(DialogAloneFrg.DIALOG_ID);
        }

    }


    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
        Log.d(TAG, "onStartLoading: ");
    }

    @Override
    public void forceLoad() {
        super.forceLoad();
        Log.d(TAG, "forceLoad: ");
//        MessageHistoryRx.getMessages(dialogId)
//
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.newThread())
//
//                .doOnError(Throwable::printStackTrace)
//                .doOnNext(list -> {
//                    deliverResult(list);
//                })
//
//                .subscribe();
    }

    @Override
    public ArrayList loadInBackground() {
        return Data.getMessages().getMessages(dialogId);
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        Log.d(TAG, "onStopLoading: ");
    }

    @Override
    public void deliverResult(ArrayList data) {
        Log.d(TAG, "deliverResult: ");
        super.deliverResult(data);
    }

    @Override
    protected boolean onCancelLoad() {
        Log.d(TAG, "onCancelLoad: ");
        return super.onCancelLoad();
    }

    @Override
    protected void onAbandon() {
        Log.d(TAG, "onAbandon: ");
        super.onAbandon();
    }

    @Override
    protected void onReset() {
        Log.d(TAG, "onReset: ");
        super.onReset();
    }
}
