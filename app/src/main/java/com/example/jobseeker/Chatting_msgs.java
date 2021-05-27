package com.example.jobseeker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Chatting_msgs extends AppCompatActivity {
    String BaseURL = "",RegUrl;
    String str_user_id,str_Chat_id;

    ArrayList<chat_list_model> list=new ArrayList<chat_list_model>();
    ListView lv;
    chat_list_adapter adapter;

    SharedPreferences shp;
    String str_role, str_msg;
    EditText msg;
    Button submit_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_msgs);

        ActionBar newbar=getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("Chat");

        shp = getSharedPreferences("login_data", MODE_PRIVATE);
        str_role = shp.getString("user_role", "");
        str_user_id = shp.getString("login_id", "");

        Intent in = getIntent();
        str_Chat_id = in.getStringExtra("chat_id");

        lv=(ListView)findViewById(R.id.chatting_msg_list);
        msg=findViewById(R.id.edt_chat);
        submit_msg=findViewById(R.id.caht_btn);

        RegUrl = getResources().getString(R.string.Base_URL)+"chatting_msgs.php";

        BaseURL = getResources().getString(R.string.Base_URL)+"add_messages.php";

        submit_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                str_msg=msg.getText().toString().trim();

                if (str_msg.equals(""))
                {
                    Toast.makeText(Chatting_msgs.this, "Msg required", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    new add_message().execute();
                }
            }
        });

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
                        chat_list_model item=new chat_list_model();
                        listJsonObj= item_json.getJSONObject(i);

                        String st_id=listJsonObj.getString("message_id");
                        String name= listJsonObj.getString("message");

                        item.setChat_id(st_id);
                        item.setMsg(name);

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
            adapter=new chat_list_adapter(Chatting_msgs.this,list);
            lv.setAdapter(adapter);
        }

        @Override
        protected JSONObject doInBackground(String... strings)
        {
            JSONParser parser = new JSONParser();
            Map<String,String> hashmap = new HashMap<>();
            hashmap.put("chatid", str_Chat_id);
            hashmap.put("user_id", str_user_id);
            hashmap.put("role_id", str_role);

            JSONObject jsonObject=parser.makeHttpRequest(RegUrl,"POST",hashmap);

            return jsonObject;
        }
    }

    private class add_message extends AsyncTask<String,String, JSONObject>
    {


        @Override
        protected JSONObject doInBackground(String... strings)
        {
            JSONParser parser = new JSONParser();
            Map<String,String> hashmap = new HashMap<>();
            hashmap.put("chatid", str_Chat_id);
            hashmap.put("subject", str_msg);
            hashmap.put("regno", str_user_id);

            JSONObject jObj = parser.makeHttpRequest(BaseURL,"POST", hashmap);

            return jObj;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try {
                int success_value = jsonObject.getInt("success");

                if(success_value==1)
                {
                    String msg = jsonObject.getString("message");

                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();

                    finish();

                }
                else
                {
                    String msg = jsonObject.getString("message");

                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();


                }
            }
            catch (JSONException e) {

            }
        }
    }

}




