package com.example.evgenia.tfsandroidchat.dialogs_list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.evgenia.tfsandroidchat.OnNavigationActionBar;
import com.example.evgenia.tfsandroidchat.R;
import com.example.evgenia.tfsandroidchat.dialog_alone.DialogAloneFrg;
import com.example.evgenia.tfsandroidchat.dialogs_list.models.DialogModel;
import com.example.evgenia.tfsandroidchat.dialogs_list.models.MessageModel;
import com.example.evgenia.tfsandroidchat.login.LoginActivity;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Evgenia on 30.03.2017.
 */

public class DialogsFrg extends Fragment implements DialogsAdapter.OnRecyclerViewClickListener{
    private static final String TAG = "DialogsFrg";
    private String userLogin;
    private RecyclerView recyclerView;
    public static String TITLE_KEY = "title";


    public static DialogsFrg newInstance(String title){
        Bundle bundle = new Bundle();
        bundle.putString(TITLE_KEY, title);
        DialogsFrg frg = new DialogsFrg();
        frg.setArguments(bundle);
        return frg;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach: ");
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);
        initRecyclerView(view);
        setTitle();
        return view;
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewStateRestored: ");
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: ");
//        outState.putString(TITLE_KEY, getArguments().getString(TITLE_KEY));
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach: ");
        super.onDetach();
    }

    public void setTitle(){
        if(getArguments() != null &&
                getActivity() instanceof AppCompatActivity &&
                ((AppCompatActivity) getActivity()).getSupportActionBar() != null) {


            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getArguments().getString(TITLE_KEY));


        }
    }

    public void initRecyclerView(View view){
        userLogin = getActivity().getIntent().getExtras().getString(LoginActivity.KEY_LOGIN);

        recyclerView = (RecyclerView)view.findViewById(R.id.rv_dialog);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
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
        Toast.makeText(getActivity(), "position = " + pos, Toast.LENGTH_SHORT).show();
        addFragment(new DialogAloneFrg(), true);
    }

    public void addFragment(Fragment fragment, boolean addToBackstack) {
        if(getActivity() instanceof OnNavigationActionBar){
            ((OnNavigationActionBar) getActivity()).addFragment(fragment, addToBackstack);
        }else {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction = fragmentTransaction.replace(R.id.fragment_container, fragment);
            if (addToBackstack) {
                fragmentTransaction.addToBackStack(null);
            }
            fragmentTransaction.commit();
        }
    }


}

