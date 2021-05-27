package com.example.jobseeker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText eemail, epassword;
    String smail, spassword, BaseUrl;
    Button blogin;
    TextView tregister, ttitle,tfpass;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        BaseUrl = getResources().getString(R.string.Base_URL) + "user_login.php";

        eemail=findViewById(R.id.inputlEmail);
        epassword=findViewById(R.id.inputlPassword);
        blogin=findViewById(R.id.btnLogin);
        tfpass=findViewById(R.id.forgotPassword);
        tregister=findViewById(R.id.gotoRegister);
        ttitle=findViewById(R.id.topText);

        sp = getSharedPreferences("login_data", MODE_PRIVATE);
        tregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, registration.class);
                Pair[] pairs = new Pair[5];
                pairs[0] = new Pair<View, String>(ttitle, "title");
                pairs[1] = new Pair<View, String>(eemail, "usrn");
                pairs[2] = new Pair<View, String>(epassword, "psd");
                pairs[3] = new Pair<View, String>(blogin, "bttn");
                pairs[4] = new Pair<View, String>(tregister, "lnk");
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                    startActivity(i, options.toBundle());
                }
            }
        });
        tfpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,forgotPassword.class);
                Pair[] pairs = new Pair[4];
                pairs[0] = new Pair<View, String>(ttitle, "title");
                pairs[1] = new Pair<View, String>(eemail, "usrn");
                pairs[2] = new Pair<View, String>(blogin, "bttn");
                pairs[3] = new Pair<View, String>(tfpass, "lnk");
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                    startActivity(i, options.toBundle());
                }
            }
        });
        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smail = eemail.getText().toString().trim();
                spassword = epassword.getText().toString().trim();

                if (smail.equals("")) {
                    Toast.makeText(MainActivity.this, "Enter email", Toast.LENGTH_LONG).show();
                } else if (spassword.equals("")) {
                    Toast.makeText(MainActivity.this, "Enter password", Toast.LENGTH_LONG).show();
                } else {
                    new LoginTask().execute();
                }
            }
        });
    }
        private void checkPermission (String readExternalStorage,int storagePermissionCode)
        {
            if (ContextCompat.checkSelfPermission(MainActivity.this, readExternalStorage)
                    == PackageManager.PERMISSION_DENIED) {

                // Requesting the permission
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{readExternalStorage},
                        storagePermissionCode);
            }
        }
        private class LoginTask extends AsyncTask<String, String, JSONObject> {


            @Override
            protected JSONObject doInBackground(String... strings) {
                JSONParser parser = new JSONParser();
                Map<String, String> hashmap = new HashMap<>();
                hashmap.put("username", smail);
                hashmap.put("password", spassword);

                JSONObject jObj = parser.makeHttpRequest(BaseUrl, "POST", hashmap);

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

                    if (success_value == 1) {
                        String msg = jsonObject.getString("message");

                        String user_id = jsonObject.getJSONObject("data").getString("role_id");
                        String user_role = jsonObject.getJSONObject("data").getString("role");
                        String usr_name = jsonObject.getJSONObject("data").getString("email");
                        String usr_pass = jsonObject.getJSONObject("data").getString("password");

                        if (user_role.equals("worker")) {

                            SharedPreferences.Editor e = sp.edit();
                            e.putString("login_id", user_id); //login_id is the key for user id
                            e.putString("user_role", user_role);
                            e.putString("usrn", usr_name);
                            e.putString("pswd", usr_pass);


                            e.commit();

                            Intent i = new Intent(MainActivity.this, Worker_Dashboard.class);
                            startActivity(i);


                        } else if (user_role.equals("user")) {
                            SharedPreferences.Editor e = sp.edit();
                            e.putString("login_id", user_id);
                            e.putString("user_role", user_role);
                            e.putString("usrn", usr_name);
                            e.putString("pswd", usr_pass);

                            e.commit();

                            Intent i = new Intent(MainActivity.this, Customer_Dashboard.class);
                            startActivity(i);

                        }


                    } else {
                        String msg = jsonObject.getString("message");

                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }

            }
        }

    }