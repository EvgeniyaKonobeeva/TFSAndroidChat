package com.example.evgenia.tfsandroidchat.presentation.dialog_alone;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.evgenia.tfsandroidchat.R;
import com.example.evgenia.tfsandroidchat.presentation.NavigationActivity;
import com.example.evgenia.tfsandroidchat.presentation.dialog_alone.loader.MassagesLoader;
import com.example.evgenia.tfsandroidchat.presentation.dialog_alone.recyclerview_classes.RvAdapter;
import com.example.evgenia.tfsandroidchat.presentation.dialogs_list.models.MessageModel;
import com.example.evgenia.tfsandroidchat.ui.custom.MessageSendingView;

import java.util.ArrayList;

/**
 * Created by User on 06.04.2017.
 */

public class DialogAloneFrg extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList>, View.OnClickListener, IMessagesContract.IMessagesView {
    private static final String TAG = "DialogAloneFrg";

//    private static final int SOME_DIALOG_ID = 931;
    private static final int LOADER_ID = 337;
    public static final String DIALOG_ID = "dialog_id";
    public static final String KEY_LOADING = "loading";

    private RvAdapter adapter;
    private RecyclerView recyclerView;
    private MessageSendingView messageSendingView;
    private ProgressBar progressBar;
    private IMessagesContract.IMessagesPresenter presenter;

    public static DialogAloneFrg newInstance(long dialogId) {

        Bundle args = new Bundle();
        args.putLong(DIALOG_ID, dialogId);
        DialogAloneFrg fragment = new DialogAloneFrg();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dialog_alone, container, false);

        initRecyclerview(root);

        initToolBar();

        messageSendingView = (MessageSendingView) root.findViewById(R.id.msg_btn);
        messageSendingView.setOnSendingButtonClickListener(this);
        progressBar = (ProgressBar) root.findViewById(R.id.progressbar);

        if(savedInstanceState!= null) {
            if (savedInstanceState.getBoolean(KEY_LOADING)) {
                showLoading();
            } else {
                hideLoading();
            }
        }


        Loader loader = getLoaderManager().getLoader(LOADER_ID);
        if(loader == null) {
            showLoading();
        }

        Bundle bundle = new Bundle();
        bundle.putLong(DIALOG_ID, getArguments().getLong(DIALOG_ID));
        getLoaderManager().initLoader(LOADER_ID, bundle, this);

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new MessagesPresenter();
        presenter.attach(this);
    }

    private void initToolBar(){
        String title = "";
        ActionBar toolbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if(toolbar != null){
            if(getArguments() != null && getArguments().containsKey(DIALOG_ID)){
                title = getString(R.string.messages) + " " + getArguments().getLong(DIALOG_ID);
            }
            toolbar.setTitle(title);
        }

        if(getActivity() instanceof NavigationActivity){
            ((NavigationActivity)getActivity()).setDrawerToggleEnabled(false);
        }
    }

    private void initRecyclerview(View root){
        recyclerView = (RecyclerView)root.findViewById(R.id.recyclerview_dialog_alone);

        LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, true);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RvAdapter();
        recyclerView.setAdapter(adapter);

    }

    private void hideLoading(){
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void showLoading(){
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_LOADING, progressBar.getVisibility()==View.VISIBLE);
    }

    @Override
    public Loader<ArrayList> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader: ");
        return new MassagesLoader(getActivity().getBaseContext(), args);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList> loader, ArrayList data) {
        adapter.insertMessages(data);
        hideLoading();
        Log.d(TAG, "onLoadFinished: ");
    }

    @Override
    public void onLoaderReset(Loader<ArrayList> loader) {
        Log.d(TAG, "onLoaderReset: ");
    }

    @Override
    public void onClick(View v) {

        MessageModel model = new MessageModel();
        model.setAuthor("Author1" + getArguments().getLong(DIALOG_ID));
        model.setDate(System.currentTimeMillis());
        model.setDialogId(getArguments().getLong(DIALOG_ID));
        model.setText(messageSendingView.getText());
        model.setMsgId(123);

        presenter.sendMessage(model);
        adapter.addNewMessage(model);
    }

    @Override
    public void messageSendSuccess(String result) {
        Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "messageSendSuccess: " + result);
    }
}
