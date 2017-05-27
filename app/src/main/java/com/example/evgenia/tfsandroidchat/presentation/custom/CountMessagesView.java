package com.example.evgenia.tfsandroidchat.presentation.custom;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.evgenia.tfsandroidchat.R;

/**
 * Created by User on 30.04.2017.
 */

public class CountMessagesView extends View {
    private static final String TAG = "CountMessagesView";

    private Paint paintCircle;
    private Paint paintText;
    private int colorCircle = getResources().getColor(R.color.color_circle);
    private int colorText = getResources().getColor(R.color.color_text);
    private Path path;
    private String text = "text";
    private int mHeight = 100;
    private int mWidth = 100;
    private int padding = 0;

    public CountMessagesView(Context context) {
        super(context);
        init();
    }

    public CountMessagesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        setAttrs(attrs);
    }

    public CountMessagesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        setAttrs(attrs);
    }
    private void init(){
        paintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintCircle.setStyle(Paint.Style.FILL);
        paintCircle.setColor(colorCircle);

        paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setColor(colorText);

        path = new Path();

    }


    private void setAttrs(AttributeSet attrs){
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.count_messages);
        if(array != null){
            try {
                float textSize = array.getDimension(R.styleable.count_messages_text_size, 0);
                if(textSize > 0){
                    paintText.setTextSize(textSize);
                }
                int textColor = array.getColor(R.styleable.count_messages_text_color, 0);
                if(textColor != 0){
                    paintText.setColor(textColor);
                }

                int circleColor = array.getColor(R.styleable.count_messages_circle_color, 0);
                if(textColor != 0){
                    paintCircle.setColor(circleColor);
                }
                float padding = array.getDimension(R.styleable.count_messages_padding, 0);
                if(padding > 0){
                    this.padding = (int)padding;
                }

            }finally {
                array.recycle();
            }
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw: ");

        path.arcTo(new RectF(0, 0, 0+mHeight, 0+mHeight), 90, 180);
        path.arcTo(new RectF(0+mWidth, 0, 0+mWidth+mHeight, 0+mHeight),270, 180);
        canvas.drawPath(path, paintCircle);

        canvas.drawText(text, 0+getRadius(), 0+mHeight-padding, paintText);
        path.reset();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG, "onMeasure: ");
        ViewGroup.LayoutParams params = this.getLayoutParams();
        params.height = mHeight;
        params.width = mWidth+mHeight;
        this.setLayoutParams(params);

        widthMeasureSpec = mWidth+mHeight;
        heightMeasureSpec = mHeight;

        setMinimumWidth(widthMeasureSpec);
        setMinimumHeight(heightMeasureSpec);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private float getRadius(){
        return mHeight/2;
    }


    public int getColorCircle() {
        return colorCircle;
    }

    public void setColorCircle(int resColor) {
        this.colorCircle = getResources().getColor(resColor);
        this.paintCircle.setColor( this.colorCircle);
    }

    public int getColorText() {
        return colorText;
    }

    public void setColorText(int colorText) {
        this.colorText = getResources().getColor(colorText);
        this.paintText.setColor(this.colorText);
    }


    public int getmHeight() {
        return mHeight;
    }

    public void setmHeight(int mHeight) {
        this.mHeight = mHeight;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        Log.d(TAG, "setText: ");
        this.text = text;
        measureText(text);

    }


    private void measureText(String text){
        Rect bounds = new Rect();
        paintText.setTextSize(50);
        paintText.getTextBounds(text, 0, text.length(), bounds);
        mHeight = bounds.height();

        mWidth = bounds.width();


    }


    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
        mHeight += padding*2;
    }


}
