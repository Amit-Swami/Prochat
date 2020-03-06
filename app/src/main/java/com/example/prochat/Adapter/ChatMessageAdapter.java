package com.example.prochat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.prochat.Holder.QBChatMessagesHolder;
import com.example.prochat.Holder.QBUsersHolder;
import com.example.prochat.R;
import com.github.library.bubbleview.BubbleTextView;
import com.quickblox.chat.QBChatService;
import com.quickblox.chat.model.QBChatMessage;

import java.util.ArrayList;

public class ChatMessageAdapter extends BaseAdapter {

   private Context context;
   private ArrayList<QBChatMessage> qbChatMessages;

    public ChatMessageAdapter(Context context, ArrayList<QBChatMessage> qbChatMessages) {
        this.context = context;
        this.qbChatMessages = qbChatMessages;
    }

    @Override
    public int getCount() {
        return qbChatMessages.size();
    }

    @Override
    public Object getItem(int i) {
        return qbChatMessages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view=convertView;
        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (qbChatMessages.get(i).getSenderId().equals(QBChatService.getInstance().getUser().getId()))
            {
                view = inflater.inflate(R.layout.list_send_msg,null);
                BubbleTextView bubbleTextView = view.findViewById(R.id.message_content);
                bubbleTextView.setText(qbChatMessages.get(i).getBody());
            }
            else
            {
                view = inflater.inflate(R.layout.list_recv_msg,null);
                BubbleTextView bubbleTextView = view.findViewById(R.id.message_content);
                bubbleTextView.setText(qbChatMessages.get(i).getBody());
                TextView txtName = view.findViewById(R.id.message_user);
                txtName.setText(QBUsersHolder.getInstance().getUserById(qbChatMessages.get(i).getSenderId()).getFullName());
            }
        }
        return view;
    }
}
