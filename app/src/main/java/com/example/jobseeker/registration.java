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
import android.text.InputType;
import android.util.Pair;
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

public class registration extends AppCompatActivity {
    EditText email,ephone,ename,epassword, elocation;
    String smail,sname,sphone,spassword,slocation, scheck="0";
    Button bregister;
    CheckBox rolecode;
    TextView tlogin, ttitle,tworker;
    String BaseURL = "";
    View topv, wave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();
        BaseURL = getResources().getString(R.string.Base_URL)+"user_registration.php";

        email=findViewById(R.id.inputEmail);
        ephone=findViewById(R.id.inputPhone);
        ename=findViewById(R.id.inputName);
        epassword=findViewById(R.id.inputPassword);
        elocation=findViewById(R.id.inputPlace);
        rolecode=findViewById(R.id.inputSecurity);
        bregister=findViewById(R.id.btnRegister);
        tlogin=findViewById(R.id.gotoLogin);
        ttitle=findViewById(R.id.topText2);
        topv=findViewById(R.id.topView);
        wave=findViewById(R.id.view5);
        tworker=findViewById(R.id.text4);
        ename.setRawInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        elocation.setRawInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_CAP_WORDS);

        tlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(registration.this,MainActivity.class);
                Pair[] pairs = new Pair[5];
                pairs[0] = new Pair<View, String>(ttitle, "title");
                pairs[1] = new Pair<View, String>(email, "usrn");
                pairs[2] = new Pair<View, String>(epassword, "psd");
                pairs[3] = new Pair<View, String>(bregister, "bttn");
                pairs[4] = new Pair<View, String>(tlogin, "lnk");
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(registration.this, pairs);
                    startActivity(i, options.toBundle());
                }
            }
        });
        rolecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if (rolecode.isChecked()){
                 tworker.setTextColor(getResources().getColor(R.color.teal_700));
                 bregister.setBackground(getResources().getDrawable(R.drawable.round_bg_green));
                 email.setBackground(getResources().getDrawable(R.drawable.round_border_green));
                 email.setCompoundDrawables(getResources().getDrawable(R.drawable.ic_email2),null,null,null);
                 ephone.setBackground(getResources().getDrawable(R.drawable.round_border_green));
                 ephone.setCompoundDrawables(getResources().getDrawable(R.drawable.ic_phone2),null,null,null);
                 ename.setBackground(getResources().getDrawable(R.drawable.round_border_green));
                 ename.setCompoundDrawables(getResources().getDrawable(R.drawable.ic_name2),null,null,null);
                 epassword.setBackground(getResources().getDrawable(R.drawable.round_border_green));
                 epassword.setCompoundDrawables(getResources().getDrawable(R.drawable.ic_lock2),null,null,null);
                 elocation.setBackground(getResources().getDrawable(R.drawable.round_border_green));
                 elocation.setCompoundDrawables(getResources().getDrawable(R.drawable.ic_loca2),null,null,null);
                 tlogin.setTextColor(getResources().getColor(R.color.teal_700));
                 topv.setBackgroundColor(getResources().getColor(R.color.teal_700));
                 wave.setBackground(getResources().getDrawable(R.drawable.wave_green));
               }
             else{
                 tworker.setTextColor(getResources().getColor(R.color.colorPrimary));
                 bregister.setBackground(getResources().getDrawable(R.drawable.round_bg));
                 email.setBackground(getResources().getDrawable(R.drawable.round_border));
                 email.setCompoundDrawables(getResources().getDrawable(R.drawable.ic_email),null,null,null);
                 ephone.setBackground(getResources().getDrawable(R.drawable.round_border));
                 ephone.setCompoundDrawables(getResources().getDrawable(R.drawable.ic_phone),null,null,null);
                 ename.setBackground(getResources().getDrawable(R.drawable.round_border));
                 ename.setCompoundDrawables(getResources().getDrawable(R.drawable.ic_name),null,null,null);
                 epassword.setBackground(getResources().getDrawable(R.drawable.round_border));
                 epassword.setCompoundDrawables(getResources().getDrawable(R.drawable.ic_lock),null,null,null);
                 elocation.setBackground(getResources().getDrawable(R.drawable.round_border));
                 elocation.setCompoundDrawables(getResources().getDrawable(R.drawable.ic_location),null,null,null);
                 tlogin.setTextColor(getResources().getColor(R.color.colorPrimary));
                 topv.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                 wave.setBackground(getResources().getDrawable(R.drawable.wave));
             }
            }
        });

        bregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smail = email.getText().toString().trim();
                sphone = ephone.getText().toString().trim();
                sname = ename.getText().toString().trim();
                spassword = epassword.getText().toString().trim();
                slocation = elocation.getText().toString().trim();
                scheck="0";

                if (smail.equals("")) {
                    Toast.makeText(registration.this, "Enter email address", Toast.LENGTH_LONG).show();
                } else if (sname.equals("")) {
                    Toast.makeText(registration.this, "Enter your name", Toast.LENGTH_LONG).show();
                } else if (sphone.equals("")) {
                    Toast.makeText(registration.this, "Enter phone number", Toast.LENGTH_LONG).show();
                }else if (spassword.equals("")) {
                    Toast.makeText(registration.this, "Enter password", Toast.LENGTH_LONG).show();
                }else if (slocation.equals("")) {
                    Toast.makeText(registration.this, "Enter your location", Toast.LENGTH_LONG).show();
                }else if (rolecode.isChecked())
                {
                    scheck="1";
                    new RegistrationTask().execute();
                }else {
                    new RegistrationTask().execute();
                }
            }
        });
    }
    private void checkPermission (String readExternalStorage,int storagePermissionCode)
    {
        if (ContextCompat.checkSelfPermission(registration.this, readExternalStorage)

                == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(registration.this,
                    new String[]{readExternalStorage},
                    storagePermissionCode);
        }
    }
    private class RegistrationTask extends AsyncTask<String,String, JSONObject>
    {

        @Override
        protected JSONObject doInBackground(String... strings)
        {
            JSONParser parser = new JSONParser();
            Map<String,String> hashmap = new HashMap<>();
            hashmap.put("name", sname);
            hashmap.put("email_id", smail);
            hashmap.put("mobile_num", sphone);
            hashmap.put("password",spassword);
            hashmap.put("location",slocation);
            hashmap.put("security",scheck);

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
}