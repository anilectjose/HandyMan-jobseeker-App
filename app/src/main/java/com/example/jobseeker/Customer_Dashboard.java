package com.example.jobseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Customer_Dashboard extends AppCompatActivity {
    Button bworks,bprofile,brecent,bchat,baddworker,bcomplaints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__dashboard);
        getSupportActionBar().hide();

        bworks= findViewById(R.id.btnaddworks);
        bprofile= findViewById(R.id.btnprofile);
        brecent= findViewById(R.id.btnhistory);
        bchat= findViewById(R.id.btnchat);
        baddworker= findViewById(R.id.btnsetting);
        bcomplaints= findViewById(R.id.btncmplt);

        bworks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Customer_Dashboard.this,adding_job.class);
                startActivity(i);
            }
        });
        bprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Customer_Dashboard.this,Profile.class);
                startActivity(i);
            }
        });
        brecent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Customer_Dashboard.this,recent_jobs.class);
                startActivity(i);
            }
        });
        bchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Customer_Dashboard.this,Chatting_names.class);
                startActivity(i);
            }
        });
        baddworker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Customer_Dashboard.this,approve_work.class);
                startActivity(i);
            }
        });
        bcomplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Customer_Dashboard.this,add_complaint.class);
                startActivity(i);
            }
        });

    }
}