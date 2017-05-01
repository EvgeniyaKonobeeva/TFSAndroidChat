package com.example.evgenia.tfsandroidchat.dialog_alone;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.evgenia.tfsandroidchat.R;
import com.example.evgenia.tfsandroidchat.ui.custom.CountMessagesView;

/**
 * Created by User on 06.04.2017.
 */

public class DialogAloneFrg extends Fragment {
    public DialogAloneFrg() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dialog_alone, container, false);

        CountMessagesView view = (CountMessagesView)root.findViewById(R.id.view);

        view.setText("30");
        view.setPadding(20);
        view.setColorText(R.color.white);

        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
