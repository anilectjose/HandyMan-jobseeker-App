package com.example.jobseeker;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class complaints_list_adapter extends BaseAdapter {
    private Context activity;

    private LayoutInflater inflater;

    private final List<complaints_list_model> event_list;

    public complaints_list_adapter(Context activity, List<complaints_list_model> event_list)
    {
        this.activity=activity;
        this.event_list= event_list;
    }

    @Override
    public int getCount() {
        return event_list.size();
    }

    @Override
    public complaints_list_model getItem(int position) {
        return event_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)// objects are used further
    {
        complaints_list_adapter.ViewHolderDoc vHolder;
        View view_row=null;
        if(convertView==null)
        {
            inflater= ((Activity)activity).getLayoutInflater();
            view_row=inflater.inflate(R.layout.complaints_layout,parent,false);
        }
        else
        {
            view_row=convertView;//object of viewholder
        }

        vHolder=new complaints_list_adapter.ViewHolderDoc();
        vHolder.assi_id=(TextView)view_row.findViewById(R.id.compp);// var of list layout model
        vHolder.name=(TextView)view_row.findViewById(R.id.txt_name);
        vHolder.msg=(TextView)view_row.findViewById(R.id.edt_subj);
        vHolder.tdue=(TextView)view_row.findViewById(R.id.edt_cont);
        final complaints_list_model dlist= event_list.get(position);
        vHolder.assi_id.setText(""+dlist.getComp_id());//variables of event model  distict
        vHolder.name.setText(""+dlist.getName());//variables of event model  distict
        vHolder.msg.setText(""+dlist.getSubj());//variables of event model
        vHolder.tdue.setText(""+dlist.getContent());//variables of event model


        return view_row;
    }
    private class ViewHolderDoc
    {
        TextView assi_id,name,msg,tdue;
    }
}
