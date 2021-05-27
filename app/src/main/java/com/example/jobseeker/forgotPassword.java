package com.example.jobseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class forgotPassword extends AppCompatActivity {
    EditText email;
    String smail,BaseUrl;
    Button bfrgtpsd;
    TextView tlog,ttitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().hide();
        BaseUrl = getResources().getString(R.string.Base_URL) + "forgetpsd.php";

        email=findViewById(R.id.inputEmail);
        bfrgtpsd=findViewById(R.id.btnOTP);
        tlog=findViewById(R.id.forgotPassword);
        ttitle=findViewById(R.id.topText);

        tlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(forgotPassword.this,MainActivity.class);
                Pair[] pairs = new Pair[4];
                pairs[0] = new Pair<View, String>(ttitle, "title");
                pairs[1] = new Pair<View, String>(email, "usrn");
                pairs[2] = new Pair<View, String>(bfrgtpsd, "bttn");
                pairs[3] = new Pair<View, String>(tlog, "lnk");
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(forgotPassword.this, pairs);
                    startActivity(i, options.toBundle());
                }
            }
        });
        bfrgtpsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smail = email.getText().toString().trim();

                if (smail.equals("")) {
                    Toast.makeText(forgotPassword.this, "Enter your email address", Toast.LENGTH_LONG).show();
                }  else {
                    Toast.makeText(getApplicationContext(), "Something went wrong. Try again later", Toast.LENGTH_SHORT).show();
                    Intent j = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(j);
                }
            }
        });
    }
}