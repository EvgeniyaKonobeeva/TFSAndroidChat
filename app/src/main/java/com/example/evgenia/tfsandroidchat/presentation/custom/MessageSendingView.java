package com.example.evgenia.tfsandroidchat.presentation.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.evgenia.tfsandroidchat.R;

/**
 * Created by User on 06.04.2017.
 */

public class MessageSendingView extends RelativeLayout implements TextWatcher{

    private static final String TAG = "MessageSendingView";
    private EditText editText;
    private ImageButton button;

    public MessageSendingView(Context context) {
        super(context);
        init(null);
    }

    public MessageSendingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MessageSendingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs){
        LayoutInflater.from(getContext()).inflate(R.layout.custom_message_sending_view, this);
        editText = (EditText) findViewById(R.id.tv_custom);
        button = (ImageButton) findViewById(R.id.btn_custom);

        obtainTypedArray(attrs);

        editText.addTextChangedListener(this);


    }

    private void obtainTypedArray(AttributeSet attrs){

        final TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.message_sending);
        if(typedArray != null){
            try{
                attrsToEditText(typedArray);

                attrsToButton(typedArray);

            }finally {

                typedArray.recycle();
            }
        }
    }

    private void attrsToEditText(TypedArray attrs){

        if(attrs.getString(R.styleable.message_sending_text) != null){
            editText.setText(attrs.getString(R.styleable.message_sending_text));
        }
        if(attrs.getString(R.styleable.message_sending_hint) != null){
            editText.setHint(attrs.getString(R.styleable.message_sending_hint));
        }
        if(attrs.getDrawable(R.styleable.message_sending_et_background) != null){
            editText.setBackgroundDrawable(attrs.getDrawable(R.styleable.message_sending_et_background));
        }
    }

    private void attrsToButton(TypedArray attrs){

        button.setBackgroundDrawable(attrs.getDrawable(R.styleable.message_sending_btn_background));

        int height = (int)attrs.getDimension(R.styleable.message_sending_btn_height, 0);
        int width = (int)attrs.getDimension(R.styleable.message_sending_btn_width, 0);
        ViewGroup.LayoutParams params = button.getLayoutParams();
        if(height > 0 && width > 0){
            params.height = height;
            params.width = width;
            button.setLayoutParams(params);
        }else {
            if(height > 0){
                params.height = height;
                button.setLayoutParams(params);
            }
            if(width > 0){
                params.width = width;
                button.setLayoutParams(params);
            }
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        RelativeLayout relativeLayout = null;

        if(getChildCount()>0) {
            relativeLayout = (RelativeLayout) getChildAt(0);
        }

        if(relativeLayout != null && relativeLayout.getChildCount()>1){

            int heightEt = relativeLayout.getChildAt(0).getBottom()-relativeLayout.getChildAt(0).getTop();

            int heightBtn = relativeLayout.getChildAt(1).getBottom()-relativeLayout.getChildAt(1).getTop();

            if(heightEt >= heightBtn){
                heightMeasureSpec = heightEt;
            }else {
                heightMeasureSpec = heightBtn;
            }
        }


        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//        empty
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(s.length() == 0){
            button.setEnabled(false);
        }else {
            button.setEnabled(true);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
//      empty
    }

    public void setOnSendingButtonClickListener(OnClickListener listener){
        button.setOnClickListener(listener);
    }

    public String getText(){
        return editText.getText().toString();
    }
}
