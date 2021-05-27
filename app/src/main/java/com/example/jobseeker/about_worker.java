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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class about_worker extends AppCompatActivity {
    TextView serv_id, serv_name, serv_place, serv_date, serv_due, serv_desc,serv_pri;
    TextView txt1,txt2,txt3,txt4,txt5,WorkId,txt6,txt7;
    Button btn_co, btn_rej;
    String RegUrl,BaseURL;
    String str_service_id,str_role;
    SharedPreferences shp;
    String str_login_id, req_sts="aaa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_worker);
        ActionBar newbar=getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("Details");

        Intent in = getIntent();
        str_service_id = in.getStringExtra("job_id");

        shp = getSharedPreferences("login_data", MODE_PRIVATE);
        str_login_id = shp.getString("login_id", "");
        str_role = shp.getString("user_role", "");

        RegUrl = getResources().getString(R.string.Base_URL)+"get_cus_details.php";
        BaseURL = getResources().getString(R.string.Base_URL)+"accept.php";

        serv_id=findViewById(R.id.service_id);
        serv_name=findViewById(R.id.sname);
        serv_place=findViewById(R.id.stype);

        serv_date=findViewById(R.id.Cus_name);
        txt1=findViewById(R.id.wwname);
        txt2=findViewById(R.id.wwqu);
        txt3=findViewById(R.id.wwspc);
        txt4=findViewById(R.id.wwlink);
        txt5=findViewById(R.id.wlink);
        WorkId=findViewById(R.id.Wid);
        txt6=findViewById(R.id.Wor_id);
        txt7=findViewById(R.id.wwprice);
        serv_pri=findViewById(R.id.wprice);
        serv_due=findViewById(R.id.Cus_palce);
        serv_desc=findViewById(R.id.Cus_phn);
        btn_co=findViewById(R.id.acc_btn);
        btn_rej=findViewById(R.id.rej_btn);

        if(str_role.equals("user")){
            txt4.setVisibility(View.VISIBLE);
            txt5.setVisibility(View.VISIBLE);
            txt6.setVisibility(View.VISIBLE);
            WorkId.setVisibility(View.VISIBLE);
            txt7.setVisibility(View.VISIBLE);
            serv_pri.setVisibility(View.VISIBLE);
        }

        new GetDetails().execute();

        btn_co.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                req_sts="Accepted";
                new AcceptTask().execute();

            }
        });

        btn_rej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                req_sts="Rejected";
                new AcceptTask().execute();

            }
        });

    }
    private class AcceptTask extends AsyncTask<String,String, JSONObject>
    {

        @Override
        protected JSONObject doInBackground(String... strings)
        {
            JSONParser parser = new JSONParser();
            Map<String,String> hashmap = new HashMap<>();
            hashmap.put("service_id", str_service_id);
            hashmap.put("resultant", req_sts);

            JSONObject jObj = parser.makeHttpRequest(BaseURL,"POST", hashmap);

            return jObj;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject)
        {
            try
            {
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
                e.printStackTrace();
            }
        }
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

                    serv_date.setText(sprice);
                    serv_due.setText(sworker);
                    serv_desc.setText(sdesc);

                }
                else if (success == 2) {
                    String event_title = jsonObject.getString("name");
                    String serid = jsonObject.getString("sid");
                    String stype = jsonObject.getString("stype");
                    String sdesc = jsonObject.getString("sdesc");
                    String sprice = jsonObject.getString("sprice");
                    String sworker = jsonObject.getString("sworkers");
                    String slin = jsonObject.getString("slink");
                    String woid = jsonObject.getString("wkid");
                    String stas = jsonObject.getString("sts");
                    String serp = jsonObject.getString("serprice");

                    serv_name.setText(event_title);
                    serv_id.setText(serid);
                    serv_place.setText(stype);

                    txt1.setText("Qualification: ");
                    txt2.setText("Specialization: ");
                    txt3.setText("Contact No: ");
                    txt5.setText(slin);
                    WorkId.setText(woid);
                    serv_pri.setText("₹ "+serp);

                    serv_date.setText(sprice);
                    serv_due.setText(sworker);
                    serv_desc.setText(sdesc);

                    if(!stas.equals("Completed")){
                        btn_co.setVisibility(View.VISIBLE);
                    }

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
            hashmap.put("srole", str_role);

            JSONObject jsonObject = parser.makeHttpRequest(RegUrl, "POST", hashmap);

            return jsonObject;
        }
    }
}