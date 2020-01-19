package com.tutor.testtaker;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    private static final String TAG = "SignUp";

    private Button signUp;
    private EditText name;
    private EditText username;
    private EditText email;
    private EditText password;
    private TextView info;
    private Button login;

    private  TextView SignUpMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUp = findViewById(R.id.btnSignUp);
        name= findViewById(R.id.username);
        username= findViewById(R.id.username);
        email= findViewById(R.id.email);
        password = findViewById(R.id.ePassword);
        info = findViewById(R.id.tvInfo);
        login = findViewById(R.id.btnLogin);
        SignUpMessage=findViewById(R.id.SignUpMsg);



        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupApi();
            }
        });

    }

    private void signupApi(){
        String url = "https://presslu1.pythonanywhere.com/api/profile/";

        Map<String,String> signupData = new HashMap<>();
        signupData.put("username",username.getText().toString());
        signupData.put("email",email.getText().toString());
        signupData.put("password",password.getText().toString());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(signupData), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "onResponse: signupApi");
                Toast.makeText(SignUp.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: signupApi");
                Toast.makeText(SignUp.this, "!!!SignUp failed!!!\nCan't connect to network", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
        requestQueue.start();
    }
}
