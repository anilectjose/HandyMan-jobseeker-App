package com.example.jobseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class edit_profile extends AppCompatActivity {
    EditText email,ephone,ename,eprice;
    String smail,sname,sphone,sprice;
    Button bregister;
    String BaseURL = "";

    SharedPreferences shp;
    String str_role,str_user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        BaseURL = getResources().getString(R.string.Base_URL) + "edit_worker.php";

        shp = getSharedPreferences("login_data", MODE_PRIVATE);
        str_role = shp.getString("user_role", "");
        str_user_id = shp.getString("login_id", "");

        email = findViewById(R.id.inputQual);
        ephone = findViewById(R.id.inputSpec);
        ename = findViewById(R.id.inputLink);
        eprice = findViewById(R.id.inputPrice);
        bregister = findViewById(R.id.btnUpdate);
        email.setRawInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);

        bregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smail = email.getText().toString().trim();
                sphone = ephone.getText().toString().trim();
                sname = ename.getText().toString().trim();
                sprice = eprice.getText().toString().trim();

                if (smail.equals("")) {
                    Toast.makeText(edit_profile.this, "Enter Qualification", Toast.LENGTH_LONG).show();
                } else if (sname.equals("")) {
                    Toast.makeText(edit_profile.this, "Enter Specialization", Toast.LENGTH_LONG).show();
                } else if (sprice.equals("")) {
                    Toast.makeText(edit_profile.this, "Enter Your Salary", Toast.LENGTH_LONG).show();
                }else{
                new RegistrationTask().execute();
                }
            }
        });
    }
        private class RegistrationTask extends AsyncTask<String, String, JSONObject> {

            @Override
            protected JSONObject doInBackground(String... strings) {
                JSONParser parser = new JSONParser();
                Map<String, String> hashmap = new HashMap<>();
                hashmap.put("name", sname);
                hashmap.put("email_id", smail);
                hashmap.put("mobile_num", sphone);
                hashmap.put("ser_price", sprice);
                hashmap.put("w_id", str_user_id);

                JSONObject jObj = parser.makeHttpRequest(BaseURL, "POST", hashmap);

                return jObj;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(JSONObject jsonObject) {
                try {
                    int success_value = jsonObject.getInt("success");

                    if (success_value == 1) {
                        String msg = jsonObject.getString("message");

                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                        finish();
                    } else {
                        String msg = jsonObject.getString("message");

                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }