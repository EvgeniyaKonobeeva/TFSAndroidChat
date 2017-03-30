package com.example.evgenia.tfsandroidchat.dialog;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.evgenia.tfsandroidchat.R;
import com.example.evgenia.tfsandroidchat.dialog.models.DialogModel;
import com.example.evgenia.tfsandroidchat.dialog.models.MessageModel;
import com.example.evgenia.tfsandroidchat.login.LoginActivity;

import java.util.ArrayList;
import java.util.Calendar;

import tinkoff.androidcourse.MainActivity;

/**
 * Created by Evgenia on 30.03.2017.
 */

public class DialogsFrg extends Fragment implements DialogsAdapter.OnRecyclerViewClickListener{
    private String userLogin;

    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);
        initRecyclerView(view);
        return view;
    }

    public void initRecyclerView(View view){
        userLogin = getActivity().getIntent().getExtras().getString(LoginActivity.KEY_LOGIN);

        recyclerView = (RecyclerView)view.findViewById(R.id.rv_dialog);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        layoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(layoutManager);
        DialogsAdapter adapter = new DialogsAdapter(generateDialog(), this, userLogin);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount()-1);
    }

    /**
     * генерируем диалоги
     * диалог состоит из истории сообщений
     * каждое 4е сообщение - пользователя, остальные - сообщения его собеседников*/
    public ArrayList<DialogModel> generateDialog(){
        ArrayList<DialogModel> list = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            if(i%3 == 0)
                list.add(new DialogModel(generateMessages(0)));
            else
                list.add(new DialogModel(generateMessages(1)));
        }

        return list;
    }


    /** генерируем список сообщений*/
    public ArrayList<MessageModel> generateMessages(int k){
        ArrayList<MessageModel> list = new ArrayList<>();
        int countMsg = 1;
        for(int i = 0; i < countMsg; i++){
            long time = Calendar.getInstance().getTimeInMillis();
            list.add(new MessageModel((k==1? "author ": "evg"), time, "text " + time));
        }
        return list;
    }

    @Override
    public void onRecyclerViewClick(int pos) {
        Toast.makeText(getActivity().getApplicationContext(), "position = " + pos, Toast.LENGTH_SHORT).show();
    }
}

