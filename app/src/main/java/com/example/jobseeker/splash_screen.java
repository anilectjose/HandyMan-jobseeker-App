package com.example.jobseeker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splash_screen extends AppCompatActivity {
    ImageView imageDeliveryMan;
    TextView text1;
    TextView text2;
    SharedPreferences sp;
    String str_user_role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sp = getSharedPreferences("login_data", MODE_PRIVATE);
        str_user_role = sp.getString("user_role","");

        getSupportActionBar().hide();

        imageDeliveryMan = findViewById(R.id.image_delivery_man);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startEnterAnimation();

            }
        },2500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startExitAnimation();

            }
        },2000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(str_user_role.equals("worker"))
                {
                    Intent intent=new Intent(getApplicationContext(),Worker_Dashboard.class);
                    startActivity(intent);
                }
                else if (str_user_role.equals("user")) {
                    Intent intent=new Intent(getApplicationContext(),Customer_Dashboard.class);
                    startActivity(intent);
                    finish();
                }

                else
                {
                    Intent intent=new Intent(splash_screen.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        },5000);

    }


    private void startEnterAnimation() {

        imageDeliveryMan.startAnimation(AnimationUtils.loadAnimation(this,R.anim.image_in));
        imageDeliveryMan.setVisibility(View.VISIBLE);
    }

    private void startExitAnimation() {

        imageDeliveryMan.startAnimation(AnimationUtils.loadAnimation(this,R.anim.image_out));
        imageDeliveryMan.setVisibility(View.INVISIBLE);
    }
}