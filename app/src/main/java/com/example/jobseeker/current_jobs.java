package com.example.jobseeker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class current_jobs extends AppCompatActivity {
    String BaseURL = "",RegUrl;
    String str_user_id;

    ArrayList<currentjob_list_model> list=new ArrayList<currentjob_list_model>();
    ListView lv;
    currentjob_list_adapter adapter;

    SharedPreferences shp;
    String str_role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_jobs);

        ActionBar newbar=getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("Current jobs");

        shp = getSharedPreferences("login_data", MODE_PRIVATE);
        str_role = shp.getString("user_role", "");
        str_user_id = shp.getString("login_id", "");

        lv=(ListView)findViewById(R.id.current_jobs_list);

        RegUrl = getResources().getString(R.string.Base_URL)+"current_jobs.php";

        new list_name().execute();
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
                        currentjob_list_model item=new currentjob_list_model();
                        listJsonObj= item_json.getJSONObject(i);

                        String st_id=listJsonObj.getString("job_id");
                        String jname= listJsonObj.getString("job_name");
                        String wname= listJsonObj.getString("job_due_date");
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
            adapter=new currentjob_list_adapter(current_jobs.this,list);
            lv.setAdapter(adapter);
        }

        @Override
        protected JSONObject doInBackground(String... strings)
        {
            JSONParser parser = new JSONParser();
            Map<String,String> hashmap = new HashMap<>();
            hashmap.put("user_id", str_user_id);

            JSONObject jsonObject=parser.makeHttpRequest(RegUrl,"POST",hashmap);

            return jsonObject;
        }
    }
}