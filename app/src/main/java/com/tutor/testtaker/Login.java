
package com.tutor.testtaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Login extends AppCompatActivity {
    private static final String TAG = "Login";

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private CountDownTimer lockTimer;
    private TextView txtLockTimer;
    private  TextView SignUpMessage;
    private Button SignUp;
    private CheckBox showpassword;

    private UserData userdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Name= findViewById(R.id.username);
        Password = findViewById(R.id.ePassword);
        Info = findViewById(R.id.tvInfo);
        Login = findViewById(R.id.btnLogin);
        SignUpMessage=findViewById(R.id.SignUpMsg);
        SignUp = findViewById(R.id.btnSignUp);
        showpassword=findViewById(R.id.checkbox);

        userdata= new UserData(this);

        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

       // SignUpMessage.setText("Not a member yet?");
        SignUpMessage.setVisibility(View.VISIBLE);
        Info.setText("No of attempts remaining: 5");
         SignUp.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(Login.this, SignUp.class);
                 startActivity(intent);
             }
         }) ;
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login.setText("Loading ...");
                Login.setClickable(false);
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });
        txtLockTimer=findViewById(R.id.txtLockTimer);
        lockTimer = new CountDownTimer(16000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d(TAG, "onTick: running");
                txtLockTimer.setText(String.valueOf(millisUntilFinished/1000));
                Log.d(TAG, "onTick: running ended");
            }

            @Override
            public void onFinish() {
                txtLockTimer.setVisibility(View.GONE);
                Login.setEnabled(true);
                counter=5;
                Info.setText("No of attempts remaining: " + counter);
            }
        };
    }

    private void validate(String userName, String userPassword){
        Log.d(TAG, "validate: ");
        String url = "https://presslu1.pythonanywhere.com/api/login/";

        Map<String,String> logindata= new HashMap<>();
        logindata.put("username",userName);
        logindata.put("password",userPassword);

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.POST, url, new JSONObject(logindata), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "onResponse: validate started");
                try {
                    userdata.setAuthToken(response.getString("token"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                userdata.setLoginStatus(true);
                Intent intent = new Intent(Login.this, NaviagationDrawer.class);
                Login.setText("Login");
                Login.setClickable(true);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: validate started");
                counter--;
                Login.setText("Login");
                Login.setClickable(true);
                Info.setText("No of attempts remaining: " + counter);

                if(counter == 0){
                    Login.setEnabled(false);
                    txtLockTimer.setVisibility(View.VISIBLE);
                    lockTimer.start();
                    Log.d(TAG, "validate: timer started");
                }
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
        requestQueue.start();

    }

}
