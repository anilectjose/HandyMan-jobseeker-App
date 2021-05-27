package com.example.jobseeker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class adding_job extends AppCompatActivity {
    TextInputLayout edt_name, edt_place, edt_desc, edt_duedate;
    String str_name,str_place,str_desc,str_duedate;
    Button addjob;
    TextView orders;
    String BaseURL = "";

    SharedPreferences shp;
    String str_role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_job);

        ActionBar newbar=getSupportActionBar();
        assert newbar != null;
        newbar.setTitle("Job");

        edt_name = findViewById(R.id.jobname);
        edt_place = findViewById(R.id.joblocation);
        edt_desc = findViewById(R.id.jobdescription);
        edt_duedate = findViewById(R.id.jobduedate);
        addjob = findViewById(R.id.Addjob_button);
        orders = findViewById(R.id.exi_works);

        shp = getSharedPreferences("login_data", MODE_PRIVATE);
        str_role = shp.getString("login_id", "");

        BaseURL = getResources().getString(R.string.Base_URL)+"add_job.php";

        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(adding_job.this,current_jobs.class);
                startActivity(i);
            }
        });

        addjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_name=edt_name.getEditText().getText().toString().trim();
                str_place=edt_place.getEditText().getText().toString().trim();
                str_desc= edt_desc.getEditText().getText().toString().trim();
                str_duedate= edt_duedate.getEditText().getText().toString().trim();

                if (str_name.equals(""))
                {
                    Toast.makeText(adding_job.this, "Name is required", Toast.LENGTH_SHORT).show();
                }
                else if(str_place.equals(""))
                {
                    Toast.makeText(adding_job.this, "Place required", Toast.LENGTH_SHORT).show();
                }
                else if (str_desc.equals(""))
                {
                    Toast.makeText(adding_job.this, "Description is required", Toast.LENGTH_SHORT).show();
                }
                else if (str_duedate.equals(""))
                {
                    Toast.makeText(adding_job.this, "Due date is required", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    new add_jobb().execute();
                }

            }
        });
    }

    private class add_jobb extends AsyncTask<String,String, JSONObject>
    {


        @Override
        protected JSONObject doInBackground(String... strings)
        {
            JSONParser parser = new JSONParser();
            Map<String,String> hashmap = new HashMap<>();
            hashmap.put("name", str_name);
            hashmap.put("place", str_place);
            hashmap.put("regno", str_role);
            hashmap.put("desc",str_desc);
            hashmap.put("due",str_duedate);

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