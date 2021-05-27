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
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class job_described extends AppCompatActivity {
    TextView serv_id, serv_name, serv_place, serv_date, serv_due, serv_desc;
    Button btn_order;
    String BaseURL = "",RegUrl;
    String str_service_id,scheck="0";
    CheckBox wcode;
    SharedPreferences shp;
    String str_login_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_described);

        ActionBar newbar=getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("Service");

        Intent in = getIntent();
        str_service_id = in.getStringExtra("job_id");

        shp = getSharedPreferences("login_data", MODE_PRIVATE);
        str_login_id = shp.getString("login_id", "");

        RegUrl = getResources().getString(R.string.Base_URL)+"get_details.php";
        BaseURL = getResources().getString(R.string.Base_URL) + "Ordering.php";

        serv_id=findViewById(R.id.service_id);
        serv_name=findViewById(R.id.sname);
        serv_place=findViewById(R.id.stype);
        wcode=findViewById(R.id.workind);
        serv_date=findViewById(R.id.servie_price);
        serv_due=findViewById(R.id.work_count);
        serv_desc=findViewById(R.id.descriptio);
        btn_order=findViewById(R.id.order);

        new GetDetails().execute();


        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scheck="0";

                if (wcode.isChecked()) {
                    scheck="1";
                    new OrderTask().execute();
                }else {
                    new OrderTask().execute();
                }
            }
        });


    }
    private class GetDetails extends AsyncTask<String, String, JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try {
                int success = jsonObject.getInt("success");


                if (success == 1) {
                    String event_title = jsonObject.getString("name");
                    String serid = jsonObject.getString("sid");
                    String stype = jsonObject.getString("stype");
                    String sdesc = jsonObject.getString("sdesc");
                    String sprice = jsonObject.getString("sprice");
                    String sworker = jsonObject.getString("sworkers");

                    serv_name.setText(event_title);
                    serv_id.setText(serid);
                    serv_place.setText(stype);
                    serv_desc.setText(sdesc);
                    serv_date.setText(sprice);
                    serv_due.setText(sworker);

                }
                else {
                    Log.e("ERROR", "API ERROR");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected JSONObject doInBackground(String... strings)
        {
            JSONParser parser = new JSONParser();
            Map<String, String> hashmap = new HashMap<>();
            hashmap.put("service_id", str_service_id);

            JSONObject jsonObject = parser.makeHttpRequest(RegUrl, "POST", hashmap);

            return jsonObject;
        }
    }
    private class OrderTask extends AsyncTask<String,String, JSONObject>
    {


        @Override
        protected JSONObject doInBackground(String... strings)
        {
            JSONParser parser = new JSONParser();
            Map<String,String> hashmap = new HashMap<>();
            hashmap.put("lid", str_login_id);
            hashmap.put("service_id", str_service_id);
            hashmap.put("scode", scheck);

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