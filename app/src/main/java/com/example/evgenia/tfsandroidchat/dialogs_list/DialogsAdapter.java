package com.example.evgenia.tfsandroidchat.dialogs_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evgenia.tfsandroidchat.R;
import com.example.evgenia.tfsandroidchat.dialogs_list.models.DialogModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Evgenia on 30.03.2017.
 */

public class DialogsAdapter extends RecyclerView.Adapter {
    private final static int MY_MSG = 1;
    private final static int SMB_MSG = 2;

    public interface OnRecyclerViewClickListener{
        void onRecyclerViewClick(int pos);
    }

    private ArrayList<DialogModel> list;
    private OnRecyclerViewClickListener clickListener;

    private String userLogin;

    public DialogsAdapter(ArrayList list, OnRecyclerViewClickListener clickListener, String userLogin) {
        this.list = list;
        this.clickListener = clickListener;
        this.userLogin = userLogin;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == MY_MSG){
            return new MyMessageVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_item_my_repl, parent, false));
        }else if(viewType == SMB_MSG){
            return new SmbMessageVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_item_smb_repl, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        /**
         * если имя автора последнего сообщения совпадает с именем залогиневшегося юзера, то это будет одна разметка - правая
         * иначе - левая для собеседников*/
        int lastPos = list.get(position).getMessagesList().size()-1;
        if(list.get(position).getMessagesList().get(lastPos).getAuthorLogin().equals(userLogin)){
            return MY_MSG;
        }else return SMB_MSG;
    }


    public class MyMessageVH extends ViewHolder{
        TextView tvMessage;
        TextView tvAuthor;
        TextView tvDatetime;

        public MyMessageVH(View itemView) {
            super(itemView);
            tvMessage = (TextView)itemView.findViewById(R.id.tv_message);
            tvAuthor = (TextView)itemView.findViewById(R.id.tv_chat_name);
            tvDatetime = (TextView)itemView.findViewById(R.id.tv_date);
            setListener();
        }

        @Override
        protected void onBind(DialogModel model) {
            super.onBind(model);
            tvMessage.setText(model.getMessagesList().get(model.getMessagesList().size()-1).getText());
            tvAuthor.setText(userLogin);
            tvDatetime.setText(new SimpleDateFormat("dd.MM.yy hh:mm")
                    .format(model.getMessagesList().get(model.getMessagesList().size()-1).getDate()));

        }
    }

    public class SmbMessageVH extends ViewHolder{
        TextView tvMessage;
        TextView tvAuthor;
        TextView tvDatetime;

        public SmbMessageVH(View itemView) {
            super(itemView);
            tvMessage = (TextView)itemView.findViewById(R.id.tv_message);
            tvAuthor = (TextView)itemView.findViewById(R.id.tv_chat_name);
            tvDatetime = (TextView)itemView.findViewById(R.id.tv_date);
            setListener();
        }

        @Override
        protected void onBind(DialogModel model) {
            super.onBind(model);
            tvMessage.setText(model.getMessagesList().get(model.getMessagesList().size()-1).getText());
            tvAuthor.setText(model.getMessagesList().get(model.getMessagesList().size()-1).getAuthorLogin());
            tvDatetime.setText(new SimpleDateFormat("dd.MM.yy hh:mm")
                    .format(model.getMessagesList().get(model.getMessagesList().size()-1).getDate()));

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
        protected void setListener() {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        clickListener.onRecyclerViewClick(getAdapterPosition());
                    }
                }
            });
        }

        protected void onBind(DialogModel model){

        }
    }
}
