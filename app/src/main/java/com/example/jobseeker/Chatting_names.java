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
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Chatting_names extends AppCompatActivity {
    String BaseURL = "",RegUrl;
    String str_user_id,str_Chat_id;

    ArrayList<chatname_list_model> list=new ArrayList<chatname_list_model>();
    ListView lv;
    chatname_list_adapter adapter;

    SharedPreferences shp;
    String str_role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_names);

        ActionBar newbar=getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("Chatting area");

        shp = getSharedPreferences("login_data", MODE_PRIVATE);
        str_role = shp.getString("user_role", "");
        str_user_id = shp.getString("login_id", "");

        lv=(ListView)findViewById(R.id.chatting_name_list);

        RegUrl = getResources().getString(R.string.Base_URL)+"chatting_names.php";

        new list_name().execute();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                str_Chat_id = adapter.getItem(position).getChat_id();
                //parse the id through intent to the percentage marking page
                //get the id from intent on that activity
                Log.e("ChatID SELECTED:", str_Chat_id);
                Log.e("UserID SELECTED:", str_user_id);

                Intent newIn =new Intent(Chatting_names.this,Chatting_msgs.class);
                newIn.putExtra("chat_id", str_Chat_id);
                newIn.putExtra("login_id", str_user_id);
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
                    for (int i = 0; i < item_json.length(); i++) {
                        JSONObject listJsonObj = new JSONObject();
                        chatname_list_model item = new chatname_list_model();
                        listJsonObj = item_json.getJSONObject(i);

                        String st_id = listJsonObj.getString("chat_id");
                        String name = listJsonObj.getString("job_name");
                        String tduedate = listJsonObj.getString("job_due_date");

                        if (str_role.equals("user")) {
                            String tw1 = listJsonObj.getString("worker_name");
                            item.setTw2(tw1);
                        }else{
                            String tw1 = listJsonObj.getString("customer_name");
                            item.setTw2(tw1);
                        }

                        item.setChat_id(st_id);
                        item.setMsg(name);
                        item.setDuedate(tduedate);


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
            adapter=new chatname_list_adapter(Chatting_names.this,list);
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