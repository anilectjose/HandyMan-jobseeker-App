package com.example.jobseeker;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class service_all_adapter extends BaseAdapter{
    private Context activity;
    private LayoutInflater inflater;

    private final List<service_all_list> event_list;
    ArrayList<service_all_list> arrayList;

    public service_all_adapter(Context activity, List<service_all_list> event_list) {
        this.activity = activity;
        this.event_list = event_list;

    }
    @Override
    public int getCount() { return event_list.size(); }

    @Override
    public service_all_list getItem(int position) {
        return event_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        service_all_adapter.ViewHolderDoc vHolder;
        View view_row = null;
        if (convertView == null) {
            inflater = ((Activity) activity).getLayoutInflater();
            view_row = inflater.inflate(R.layout.service_layout, parent, false);
        } else {
            view_row = convertView;//object of viewholder
        }

        vHolder = new service_all_adapter.ViewHolderDoc();
        vHolder.Service_id = (TextView) view_row.findViewById(R.id.service_id);
        vHolder.Sername = (TextView) view_row.findViewById(R.id.sname);
        vHolder.Serdate = (TextView) view_row.findViewById(R.id.sdate);
        vHolder.Serdue = (TextView) view_row.findViewById(R.id.sdue);
        vHolder.Sertags = (TextView) view_row.findViewById(R.id.tags);
        final service_all_list dlist = event_list.get(position);
        vHolder.Service_id.setText("" + dlist.getSer_id());
        vHolder.Sername.setText("" + dlist.getSName());
        vHolder.Serdate.setText("" + dlist.getSType());
        vHolder.Serdue.setText("" + dlist.getSDue());
        vHolder.Sertags.setText("" + dlist.getSTags());

        return view_row;
    }

    private class ViewHolderDoc {
        TextView Service_id, Sername, Serdate, Serdue, Sertags;
    }
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        event_list.clear();

        if (charText.length()==0){
            event_list.addAll(arrayList);
        }
        else {
            for (service_all_list model : arrayList){
                if (model.getSName().toLowerCase(Locale.getDefault()).contains(charText)){
                    event_list.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
}


