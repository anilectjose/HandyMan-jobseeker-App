package com.example.jobseeker;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class chatname_list_adapter extends BaseAdapter {
    private Context activity;
    SharedPreferences shp;
    String str_role;
    private LayoutInflater inflater;

    private final List<chatname_list_model> event_list;

    public chatname_list_adapter(Context activity, List<chatname_list_model> event_list)
    {
        this.activity=activity;
        this.event_list= event_list;
    }

    @Override
    public int getCount() {
        return event_list.size();
    }

    @Override
    public chatname_list_model getItem(int position) {
        return event_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)// objects are used further
    {
        chatname_list_adapter.ViewHolderDoc vHolder;
        View view_row=null;

        if(convertView==null)
        {
            inflater= ((Activity)activity).getLayoutInflater();
            view_row=inflater.inflate(R.layout.chatting_names_layout,parent,false);
        }
        else
        {
            view_row=convertView;//object of viewholder
        }

        vHolder=new chatname_list_adapter.ViewHolderDoc();
        vHolder.assi_id=(TextView)view_row.findViewById(R.id.chat_id);// var of list layout model
        vHolder.msg=(TextView)view_row.findViewById(R.id.tmsg);
        vHolder.tdue=(TextView)view_row.findViewById(R.id.tduedate);
        vHolder.tw2=(TextView)view_row.findViewById(R.id.w2);
        vHolder.tw=(TextView)view_row.findViewById(R.id.w1);
        final chatname_list_model dlist= event_list.get(position);
        vHolder.assi_id.setText(""+dlist.getChat_id());//variables of event model  distict
        vHolder.msg.setText(""+dlist.getMsg());//variables of event model
        vHolder.tdue.setText(""+dlist.getDuedate());//variables of event model
        vHolder.tw2.setText(""+dlist.getTw2());//variables of event model

        shp = activity.getSharedPreferences("login_data", MODE_PRIVATE);
        str_role = shp.getString("user_role", "");

            vHolder.tw.setVisibility(View.VISIBLE);
            vHolder.tw2.setVisibility(View.VISIBLE);

        return view_row;
    }
    private class ViewHolderDoc
    {
        TextView assi_id,msg,tdue,tw2,tw;
    }
}

