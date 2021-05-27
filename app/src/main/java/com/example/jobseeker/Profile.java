package com.example.jobseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {
    TextView uname, eorders, ecomplaints, efeedback, email, phn, place, age,work,rrole;
    String RegUrl, BaseUrl;
    Button logout;
    ImageView edit;

    ProgressBar pgBar;
    JSONParser parser = new JSONParser();

    SharedPreferences shp;
    String str_login_id;
    String str_role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().hide();

        BaseUrl = getResources().getString(R.string.Base_URL);
        RegUrl = BaseUrl + "profile.php";//of apiUploadURL = getResources().getString(R.string.Base_URL)+"Uploaduserimage.php";

        shp = getSharedPreferences("login_data", MODE_PRIVATE);
        str_login_id = shp.getString("login_id", "");
        str_role = shp.getString("user_role", "");

        Log.e("LOGIN ID", str_login_id);
        Log.e("LOGIN ROLE", str_role);


        uname = findViewById(R.id.nm);
        rrole = findViewById(R.id.rl);
        edit = findViewById(R.id.edit);
        eorders = findViewById(R.id.orders);
        ecomplaints = findViewById(R.id.complaints);
        age = findViewById(R.id.page);
        email = findViewById(R.id.gmail);
        phn = findViewById(R.id.phn);
        place = findViewById(R.id.eplace);
        logout = findViewById(R.id.lgout);
        work = findViewById(R.id.ordwork);

        if(str_role.equals("worker")){
            edit.setVisibility(View.VISIBLE);
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getApplicationContext(), edit_profile.class);
                startActivity(j);
                finish();
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Logging out", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = shp.edit();
                editor.clear();
                editor.commit();
                Intent j = new Intent(getApplicationContext(), splash_screen.class);
                startActivity(j);
                finish();
            }
        });

/*        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });*/

        new GetDetails().execute();

    }

    /* private void showProgressResult(String s)
     {
         AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setMessage("Registration Success!").setTitle("Success")
                 .setCancelable(false)
                 .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int id) {

                         finish();
                     }
                 });
         AlertDialog alert = builder.create();
         alert.show();

     }
 */

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
                    String Orders = jsonObject.getString("wcount");
                    String emai = jsonObject.getString("email");
                    String phne = jsonObject.getString("phonenum");
                    String eage = jsonObject.getString("agee");
                    String Place = jsonObject.getString("plce");
                    String comp = jsonObject.getString("ccount");

                    uname.setText(event_title);
                    eorders.setText(Orders);
                    email.setText(emai);
                    phn.setText(phne);
                    ecomplaints.setText(comp);
                    age.setText(eage);
                    place.setText(Place);
                    rrole.setText(str_role);

                } else if (success == 2){

                    String event_title = jsonObject.getString("name");
                    String Orders = jsonObject.getString("ocount");
                    String emai = jsonObject.getString("email");
                    String phne = jsonObject.getString("phonenum");
                    String compl = jsonObject.getString("cage");
                    String Place = jsonObject.getString("plce");
                    String rorder = jsonObject.getString("ccount");

                    uname.setText(event_title);
                    eorders.setText(Orders);
                    email.setText(emai);
                    phn.setText(phne);
                    ecomplaints.setText(rorder);
                    age.setText(compl);
                    place.setText(Place);
                    rrole.setText(str_role);
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
            hashmap.put("log_id", str_login_id);
            hashmap.put("role", str_role);

            JSONObject jsonObject = parser.makeHttpRequest(RegUrl, "POST", hashmap);

            return jsonObject;
        }
    }
    }