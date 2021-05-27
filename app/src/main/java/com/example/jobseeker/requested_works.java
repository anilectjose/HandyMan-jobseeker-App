package com.example.jobseeker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class requested_works extends AppCompatActivity {
    String  RegUrl,BaseUrl,str_service_id;

    ArrayList<service_all_list> list=new ArrayList<service_all_list>();
    ListView lv;
    service_all_adapter adapter;
    SharedPreferences shp;
    String str_login_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requested_works);

        ActionBar newbar=getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("Other requests");

        shp = getSharedPreferences("login_data", MODE_PRIVATE);
        str_login_id = shp.getString("login_id", "");

        BaseUrl=getResources().getString(R.string.Base_URL);
        RegUrl=BaseUrl+"OtherReq.php";//of api

        lv=(ListView)findViewById(R.id.req_work_list);

        new list_leave().execute();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                str_service_id = adapter.getItem(position).getSer_id();
                Log.e("ID SELECTED:", str_service_id);

                Intent newIn =new Intent(requested_works.this,job_described.class);
                newIn.putExtra("job_id", str_service_id);
                startActivity(newIn);
            }
        });
    }

    class list_leave extends AsyncTask<String,String, JSONObject> {


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
                        service_all_list item=new service_all_list();
                        listJsonObj= item_json.getJSONObject(i);

                        String Serve_id=listJsonObj.getString("job_id");
                        String name= listJsonObj.getString("job_name");
                        String type= listJsonObj.getString("job_date");
                        String tag= listJsonObj.getString("job_due_date");

                        item.setSer_id(Serve_id);
                        item.setSName(name);
                        item.setSType(type);
                        item.setSDue(tag);

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
            adapter=new service_all_adapter(requested_works.this,list);
            lv.setAdapter(adapter);
        }

        @Override
        protected JSONObject doInBackground(String... strings)
        {
            JSONParser parser=new JSONParser();
            Map<String,String> hashmap = new HashMap<>();
            hashmap.put("login_idd", str_login_id);

            JSONObject jsonObject=parser.makeHttpRequest(RegUrl,"POST",hashmap);

            return jsonObject;
        }
    }
}