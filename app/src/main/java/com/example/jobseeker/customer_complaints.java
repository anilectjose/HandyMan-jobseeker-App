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

public class customer_complaints extends AppCompatActivity {
    String BaseURL = "",RegUrl;
    String str_user_id;

    ArrayList<complaints_list_model> list=new ArrayList<complaints_list_model>();
    ListView lv;
    complaints_list_adapter adapter;

    SharedPreferences shp;
    String str_role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_complaints);
        ActionBar newbar=getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("Your complaints");

        shp = getSharedPreferences("login_data", MODE_PRIVATE);
        str_role = shp.getString("user_role", "");
        str_user_id = shp.getString("login_id", "");

        lv=(ListView)findViewById(R.id.customer_compl_list);

        RegUrl = getResources().getString(R.string.Base_URL)+"view_complaints.php";

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
                        complaints_list_model item=new complaints_list_model();
                        listJsonObj= item_json.getJSONObject(i);

                        String st_id=listJsonObj.getString("complaint_id");
                        String name= listJsonObj.getString("name");
                        String tduedate= listJsonObj.getString("subject");
                        String compl= listJsonObj.getString("complaint");

                        item.setComp_id(st_id);
                        item.setName(name);
                        item.setSubj(tduedate);
                        item.setContent(compl);

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
            adapter=new complaints_list_adapter(customer_complaints.this,list);
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