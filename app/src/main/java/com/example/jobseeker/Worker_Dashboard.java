package com.example.jobseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Worker_Dashboard extends AppCompatActivity {
    Button bworks,bprofile,bcalender,bchat,baddworker,bcomplaints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker__dashboard);
        getSupportActionBar().hide();

        bworks= findViewById(R.id.btnworks);
        bprofile= findViewById(R.id.btnprofile);
        //bcalender= findViewById(R.id.btncalender);
        bchat= findViewById(R.id.btnchat);
        bcalender= findViewById(R.id.btnhistory);
        baddworker= findViewById(R.id.btncoworker);
        bcomplaints= findViewById(R.id.btncmplt);

        bworks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Worker_Dashboard.this,view_all_works.class);
                startActivity(i);
            }
        });
        bprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Worker_Dashboard.this,Profile.class);
                startActivity(i);
            }
        });
        bcalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Worker_Dashboard.this,recent_jobs.class);
                startActivity(i);
            }
        });
        bchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Worker_Dashboard.this,Chatting_names.class);
                startActivity(i);
            }
        });
        baddworker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Worker_Dashboard.this,add_co_worker.class);
                startActivity(i);
            }
        });
        bcomplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Worker_Dashboard.this,customer_complaints.class);
                startActivity(i);
            }
        });

    }
}