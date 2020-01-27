
package com.tutor.testtaker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

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

    AlertDialog.Builder builder;
    private UserData userdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        builder= new AlertDialog.Builder(this);

        Name= findViewById(R.id.username);
        Password = findViewById(R.id.ePassword);
        Info = findViewById(R.id.tvInfo);
        Login = findViewById(R.id.btnLogin);

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
//        SignUpMessage.setVisibility(View.VISIBLE);
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
        String url = Utils.getDOMAIN()+"login/";

        Map<String,String> logindata= new HashMap<>();
        logindata.put("username",userName);
        logindata.put("password",userPassword);

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.POST, url, new JSONObject(logindata), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "onResponse: validate started");
                try {
                    if(response.getBoolean("is_active")== false){
                        final int id= response.getInt("id");
                        builder.setTitle("Activate Account")
                                .setMessage("Your account has not been activated.\n" +
                                        "Please activate your account through the activation link sent to your Email.\n"+
                                        "Didn't receive any Email,Resend?")
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                                .setPositiveButton("Resend", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Log.d(TAG, "onClick: started");

                                        String url= Utils.getDOMAIN()+"confirm/"+id+"/";
                                        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                Log.d(TAG, "onResponse: started");
                                                Toast.makeText(Login.this, "The confirmation Email has been sent.", Toast.LENGTH_SHORT).show();

                                            }
                                        }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                Log.d(TAG, "onErrorResponse: started");
                                                Toast.makeText(Login.this, "Failed to send confirmation Email.", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        VolleyPoint.getInstance(Login.this).addToRequestQueue(stringRequest);
                                    }
                                })
                                .create().show();
                    }
                    else {
                        userdata.setAuthToken(response.getString("token"));
                        userdata.setLoginStatus(true);
                        Intent intent = new Intent(Login.this, NaviagationDrawer.class);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Login.setText("Login");
                Login.setClickable(true);

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
                Toast.makeText(Login.this, "Failed to login", Toast.LENGTH_SHORT).show();
            }
        });

        VolleyPoint.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }

}
