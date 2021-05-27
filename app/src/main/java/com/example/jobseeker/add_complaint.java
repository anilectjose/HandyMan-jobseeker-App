package com.example.jobseeker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class add_complaint extends AppCompatActivity {
    TextInputLayout edt_name, edt_subj, edt_desc,Woid;
    String str_name,str_subj,str_reason,str_wd;
    Button addcomp;
    TextView complaints;
    String BaseURL = "";

    SharedPreferences shp;
    String str_role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_complaint);

        ActionBar newbar=getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("Add complaints");

        shp = getSharedPreferences("login_data", MODE_PRIVATE);
        str_role = shp.getString("login_id", "");

        BaseURL = getResources().getString(R.string.Base_URL)+"add_complaint.php";

        edt_name = findViewById(R.id.compname);
        edt_subj = findViewById(R.id.compsubject);
        Woid = findViewById(R.id.woid);
        edt_desc = findViewById(R.id.complaints);
        addcomp = findViewById(R.id.Addcompl_button);
        complaints = findViewById(R.id.exi_complaints);

        complaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(add_complaint.this,customer_complaints.class);
                startActivity(i);
            }

        });
        addcomp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_name=edt_name.getEditText().getText().toString().trim();
                str_wd=Woid.getEditText().getText().toString().trim();
                str_name=edt_name.getEditText().getText().toString().trim();
                str_subj=edt_subj.getEditText().getText().toString().trim();
                str_reason= edt_desc.getEditText().getText().toString().trim();

                if (str_name.equals(""))
                {
                    Toast.makeText(add_complaint.this, "Name is required", Toast.LENGTH_SHORT).show();
                }
                else if (str_wd.equals(""))
                {
                    Toast.makeText(add_complaint.this, "Worker Id is requied", Toast.LENGTH_SHORT).show();
                }
                else if(str_subj.equals(""))
                {
                    Toast.makeText(add_complaint.this, "Subject requied", Toast.LENGTH_SHORT).show();
                }
                else if (str_reason.equals(""))
                {
                    Toast.makeText(add_complaint.this, "Complaint is requied", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    new leaveTask().execute();
                }

            }
        });
    }

    private class leaveTask extends AsyncTask<String,String, JSONObject>
    {


        @Override
        protected JSONObject doInBackground(String... strings)
        {
            JSONParser parser = new JSONParser();
            Map<String,String> hashmap = new HashMap<>();
            hashmap.put("name", str_name);
            hashmap.put("wid", str_wd);
            hashmap.put("subject", str_subj);
            hashmap.put("regno", str_role);
            hashmap.put("reason",str_reason);

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