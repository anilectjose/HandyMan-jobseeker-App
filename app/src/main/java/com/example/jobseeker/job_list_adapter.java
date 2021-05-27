package com.example.jobseeker;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class job_list_adapter extends BaseAdapter {
    private Context activity;

    private LayoutInflater inflater;

    private final List<job_list_model> event_list;

    public job_list_adapter(Context activity, List<job_list_model> event_list)
    {
        this.activity=activity;
        this.event_list= event_list;
    }

    @Override
    public int getCount() {
        return event_list.size();
    }

    @Override
    public job_list_model getItem(int position) {
        return event_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)// objects are used further
    {
        job_list_adapter.ViewHolderDoc vHolder;
        View view_row=null;
        if(convertView==null)
        {
            inflater= ((Activity)activity).getLayoutInflater();
            view_row=inflater.inflate(R.layout.recent_jobs_layout,parent,false);
        }
        else
        {
            view_row=convertView;//object of viewholder
        }

        vHolder=new job_list_adapter.ViewHolderDoc();
        vHolder.assi_id=(TextView)view_row.findViewById(R.id.order_id);// var of list layout model
        vHolder.jobname=(TextView)view_row.findViewById(R.id.xsname);
        vHolder.jobdate=(TextView)view_row.findViewById(R.id.xdate);
        vHolder.jobworker=(TextView)view_row.findViewById(R.id.xwname);
        final job_list_model dlist= event_list.get(position);
        vHolder.assi_id.setText(""+dlist.getJobid());//variables of event model  distict
        vHolder.jobname.setText(""+dlist.getJob());//variables of event model
        vHolder.jobdate.setText(""+dlist.getDate());//variables of event model
        vHolder.jobworker.setText(""+dlist.getWorker());//variables of event model


        return view_row;
    }
    private class ViewHolderDoc
    {
        TextView assi_id,jobname,jobdate,jobworker;
    }
}

