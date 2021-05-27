package com.example.jobseeker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class chat_list_adapter extends BaseAdapter {
    private Context activity;

    private LayoutInflater inflater;

    private final List<chat_list_model> event_list;

    public chat_list_adapter(Context activity, List<chat_list_model> event_list)
    {
        this.activity=activity;
        this.event_list= event_list;
    }

    @Override
    public int getCount() {
        return event_list.size();
    }

    @Override
    public chat_list_model getItem(int position) {
        return event_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)// objects are used further
    {
        ViewHolderDoc vHolder;
        View view_row=null;
        if(convertView==null)
        {
            inflater= ((Activity)activity).getLayoutInflater();
            view_row=inflater.inflate(R.layout.chatting_layout,parent,false);
        }
        else
        {
            view_row=convertView;//object of viewholder
        }

        vHolder=new ViewHolderDoc();
        vHolder.assi_id=(TextView)view_row.findViewById(R.id.chat_id);// var of list layout model
        vHolder.msg=(TextView)view_row.findViewById(R.id.tmsg);
        final chat_list_model dlist= event_list.get(position);
        vHolder.assi_id.setText(""+dlist.getChat_id());//variables of event model  distict
        vHolder.msg.setText(""+dlist.getMsg());//variables of event model

        return view_row;
    }
    private class ViewHolderDoc
    {
        TextView assi_id,msg;
    }
}

