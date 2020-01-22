package com.tutor.testtaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainMenu extends AppCompatActivity {
    private static final String TAG = "MainMenu";
    Button btnStartTest;
    
    Button Stat;
    Button Logout;
    Button Profile;

    UserData userData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Log.d(TAG, "onCreate: Started");
        Logout=findViewById(R.id.btnLogout);
        Profile=findViewById(R.id.btnProfile);
        Stat = findViewById(R.id.btnTeach);

        userData= new UserData(this);

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, ViewProfile.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                userData.setLoginStatus(false);
                startActivity(intent);
            }
        });
        btnStartTest=findViewById(R.id.btnStat);
        btnStartTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainMenu.this,"Test Started",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainMenu.this,TestPageActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
        Stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: started");
                Intent intent= new Intent(MainMenu.this,TestListActivity.class);
                startActivity(intent);
            }
        });

        String url= "https://presslu1.pythonanywhere.com/api/getuser/";
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "onResponse: started");
                UserProfile userProfile = null;
                try {
                    userProfile = new UserProfile(response.getString("username"), response.getString("email"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    userProfile.setId(response.getInt("id"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                userData.setUser(userProfile);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse:couldn't save user info to sharedpreferences ");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> data= new HashMap<>();
                data.put("Authorization","token "+userData.getAuthToken());
                return data;
            }
        };
    }
}
