package com.example.evgenia.tfsandroidchat.presentation.dialog_alone;

import android.util.Log;
import android.widget.ImageView;

import com.example.evgenia.tfsandroidchat.data.Data;
import com.example.evgenia.tfsandroidchat.presentation.base_contract.IMainContract;
import com.example.evgenia.tfsandroidchat.presentation.dialogs_list.models.MessageModel;
import com.example.evgenia.tfsandroidchat.presentation.login.ILoginContract;

import java.lang.ref.WeakReference;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Created by User on 28.05.2017.
 */

public class MessagesPresenter implements IMessagesContract.IMessagesPresenter{
    private static final String TAG = "MessagesPresenter";

    private WeakReference<IMessagesContract.IMessagesView> viewRef;
    private PublishSubject<MessageModel> sendMsgSubject;

    public MessagesPresenter() {

        sendMsgSubject = PublishSubject.create();

        sendMsgSubject
                .observeOn(Schedulers.newThread())
                .map(Data.getMessages()::putMessageToDB)

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: thread = " + Thread.currentThread().getName());

                        if(getView() != null){
                            ((IMessagesContract.IMessagesView)getView()).messageSendSuccess(s);
                        }else {
                            Log.d(TAG, "onNext: view suddenly detached");
                        }
                    }
                });
    }

    @Override
    public void sendMessage(MessageModel msg) {

        sendMsgSubject.onNext(msg);
    }


    @Override
    public <V> void attach(V viewRef) {
        this.viewRef = new WeakReference<>((IMessagesContract.IMessagesView) viewRef);
    }

    @Override
    public void detach() {
        if(viewRef != null){
            viewRef.clear();
            viewRef = null;
        }

    }

    @Override
    public <V> V getView() {
        if(viewRef != null){
            return (V)viewRef.get();
        }else
            return null;
    }
}
