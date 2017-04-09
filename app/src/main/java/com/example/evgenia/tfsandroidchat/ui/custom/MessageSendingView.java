package com.example.evgenia.tfsandroidchat.ui.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.evgenia.tfsandroidchat.R;

/**
 * Created by User on 06.04.2017.
 */

public class MessageSendingView extends android.support.v7.widget.AppCompatTextView {

    public MessageSendingView(Context context) {
        super(context);
    }

    public MessageSendingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MessageSendingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs){
        LayoutInflater.from(getContext()).inflate(R.layout.custom_text_view_button,(RelativeLayout)getParent(), false);
        TextView textView = (TextView) findViewById(R.id.tv_custom);
        ImageButton button = (ImageButton) findViewById(R.id.btn_custom);

//        final TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ProgressButton);
//        if (typedArray != null) {
//            if (typedArray.getString(R.styleable.ProgressButton_text) != null) {
//                setText(typedArray.getString(R.styleable.ProgressButton_text));
//            }
//            setEnabled(typedArray.getBoolean(R.styleable.ProgressButton_enabled, true));
//            typedArray.recycle();
//        }


    }
}
