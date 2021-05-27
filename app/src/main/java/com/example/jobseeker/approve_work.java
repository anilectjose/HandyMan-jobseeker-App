package com.example.jobseeker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class approve_work extends AppCompatActivity {
    String BaseURL = "",RegUrl;
    String str_user_id,str_service_id;

    ArrayList<job_list_model> list=new ArrayList<job_list_model>();
    ListView lv;
    job_list_adapter adapter;

    SharedPreferences shp;
    String str_role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_work);
        ActionBar newbar=getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("Approve works");

        shp = getSharedPreferences("login_data", MODE_PRIVATE);
        str_role = shp.getString("user_role", "");
        str_user_id = shp.getString("login_id", "");

        lv=(ListView)findViewById(R.id.recent_jobs_list );

        RegUrl = getResources().getString(R.string.Base_URL)+"accept_req.php";

        new list_name().execute();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                str_service_id = adapter.getItem(position).getJobid();
                Log.e("ID SELECTED:", str_service_id);

                Intent newIn =new Intent(approve_work.this,about_worker.class);
                newIn.putExtra("job_id", str_service_id);
                startActivity(newIn);
            }
        });
    }
    class list_name extends AsyncTask<String,String, JSONObject> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        public void onPostExecute(JSONObject jsonObject) {
            try {
                int success = jsonObject.getInt("success");

                if(success==1)
                {

                    JSONArray item_json = jsonObject.getJSONArray("result");//of api
                    for (int i = 0; i < item_json.length(); i++)
                    {
                        JSONObject listJsonObj =new JSONObject();
                        job_list_model item=new job_list_model();
                        listJsonObj= item_json.getJSONObject(i);

                        String st_id=listJsonObj.getString("job_id");
                        String jname= listJsonObj.getString("job_name");
                        String wname= listJsonObj.getString("worker_name");
                        String jdate= listJsonObj.getString("job_date");

                        item.setJobid(st_id);
                        item.setJob(jname);
                        item.setWorker(wname);
                        item.setDate(jdate);

                        list.add(item);
                    }
                }
                else if(success==2)
                {

                    JSONArray item_json = jsonObject.getJSONArray("result");//of api
                    for (int i = 0; i < item_json.length(); i++)
                    {
                        JSONObject listJsonObj =new JSONObject();
                        job_list_model item=new job_list_model();
                        listJsonObj= item_json.getJSONObject(i);

                        String st_id=listJsonObj.getString("job_id");
                        String jname= listJsonObj.getString("job_name");
                        String wname= listJsonObj.getString("customer_name");
                        String jdate= listJsonObj.getString("job_date");

                        item.setJobid(st_id);
                        item.setJob(jname);
                        item.setWorker(wname);
                        item.setDate(jdate);

                        list.add(item);
                    }
                }
                else
                {
                    Log.e("ERROR","API ERROR");
                }
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }
            adapter=new job_list_adapter(approve_work.this,list);
            lv.setAdapter(adapter);
        }

        @Override
        protected JSONObject doInBackground(String... strings)
        {
            JSONParser parser = new JSONParser();
            Map<String,String> hashmap = new HashMap<>();
            hashmap.put("user_id", str_user_id);
            hashmap.put("user_role", str_role);

            JSONObject jsonObject=parser.makeHttpRequest(RegUrl,"POST",hashmap);

            return jsonObject;
        }
    }
}