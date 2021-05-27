package com.example.jobseeker;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class add_co_list_adapter  extends BaseAdapter {
        private Context activity;
        String RejectUrl;
        private LayoutInflater inflater;
        private final List<add_co_list_model> event_list;

    public add_co_list_adapter(Context activity, List < add_co_list_model > event_list)
        {
            this.activity = activity;
            this.event_list = event_list;
            RejectUrl = activity.getResources().getString(R.string.Base_URL)+"Add_coworker.php";
        }

        @Override
        public int getCount () {
            return event_list.size();
        }

        @Override
        public add_co_list_model getItem ( int position){
            return event_list.get(position);
        }

        @Override
        public long getItemId ( int position){
            return position;
        }

        @Override
        public View getView ( int position, View convertView, ViewGroup
        parent)// objects are used further
        {
            add_co_list_adapter.ViewHolderDoc vHolder;
            View view_row = null;
            if (convertView == null) {
                inflater = ((Activity) activity).getLayoutInflater();
                view_row = inflater.inflate(R.layout.co_worker_layout, parent, false);
            } else {
                view_row = convertView;//object of viewholder
            }

            vHolder = new add_co_list_adapter.ViewHolderDoc();
            vHolder.assi_id = (TextView) view_row.findViewById(R.id.order_id);// var of list layout model
            vHolder.jobname = (TextView) view_row.findViewById(R.id.xsname);
            vHolder.jobdate = (TextView) view_row.findViewById(R.id.xdate);
            vHolder.jobworker = (TextView) view_row.findViewById(R.id.xwname);
            vHolder.request=(Button) view_row.findViewById(R.id.coworker_add_btn);
            final add_co_list_model dlist = event_list.get(position);
            vHolder.assi_id.setText("" + dlist.getJobid());//variables of event model  distict
            vHolder.jobname.setText("" + dlist.getJob());//variables of event model
            vHolder.jobdate.setText("" + dlist.getDate());//variables of event model
            vHolder.jobworker.setText("" + dlist.getWorker());//variables of event model

            vHolder.request.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {

                    String leaveID = dlist.getJobid(); //just read the text & identify the function

                    new RejectTask().execute(leaveID);
                }
            });



            return view_row;
        }
    private class RejectTask extends AsyncTask<String,String, JSONObject> {
        @Override
        protected JSONObject doInBackground(String... strings) {
            JSONParser parser = new JSONParser();
            String lea_id=strings[0];
            Map<String,String> params=new HashMap<>();
            params.put("jobid",lea_id);
            JSONObject jsonob=parser.makeHttpRequest(RejectUrl,"POST",params);
            return jsonob;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            try {
                int success = jsonObject.getInt("success");
                String str_data = jsonObject.getString("message");

                if (success == 1)
                {
                    Toast.makeText(activity,str_data,Toast.LENGTH_LONG).show();
                    ((add_co_worker)activity).finish();
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }
        private class ViewHolderDoc {
            TextView assi_id, jobname, jobdate, jobworker;
            Button request;
        }
    }