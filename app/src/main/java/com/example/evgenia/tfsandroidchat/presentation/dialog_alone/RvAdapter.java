package com.example.evgenia.tfsandroidchat.presentation.dialog_alone;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evgenia.tfsandroidchat.R;
import com.example.evgenia.tfsandroidchat.presentation.dialogs_list.models.MessageModel;
import com.example.evgenia.tfsandroidchat.presentation.login.user.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by User on 18.05.2017.
 */

public class RvAdapter extends RecyclerView.Adapter {
    ArrayList<MessageModel> list;
    private static final int LEFT = 235;
    private static final int RIGHT = 140;

    public RvAdapter() {
        this.list = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType == LEFT){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_dialog_alone_left, parent, false);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_dialog_alone_right, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).onBindModel(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position%2 == 0) {
            return LEFT;
        }else {
            return RIGHT;
        }

    }

    public void addNewMessage(MessageModel msg){
        list.add(msg);
        notifyItemInserted(0);
    }

    public void insertMessages(ArrayList<MessageModel> list){
        this.list.addAll(0, list);
        notifyItemRangeInserted(0, list.size());
    }

    public ArrayList<MessageModel> getList() {
        return list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView text;
        TextView time;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy HH:mm");
        public ViewHolder(View itemView) {
            super(itemView);

            text = (TextView)itemView.findViewById(R.id.tv_message);
            time = (TextView)itemView.findViewById(R.id.tv_date);
        }

        public void onBindModel(MessageModel model){
            text.setText(model.getText());
            time.setText(sdf.format(model.getDate()));
        }
    }



}
