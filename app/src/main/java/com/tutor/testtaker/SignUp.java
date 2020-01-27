package com.tutor.testtaker;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
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
    private EditText confirmpassword;
    private EditText institution;
    private TextView info;
    private Button login;
    private AppCompatCheckBox checkBox;

    private  TextView SignUpMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        signUp = findViewById(R.id.btnSignUp);
        name= findViewById(R.id.username);
        username= findViewById(R.id.username);
        email= findViewById(R.id.email);
        institution= findViewById(R.id.inst);
        password = findViewById(R.id.ePassword);
        info = findViewById(R.id.tvInfo);
        login = findViewById(R.id.btnLogin);

        checkBox= findViewById(R.id.checkbox);
        confirmpassword=findViewById(R.id.confirmPass);
//        signUp.setOnClickListener(new View.OnClickListener() {
//                                      @Override
//                                      public void onClick(View view) {
//
//                                      }
//                                  }


                signUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!password.getText().toString().equals(confirmpassword.getText().toString())) {

                            Toast.makeText(password.getContext(), "!!!Password did not match!!!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        signUp.setText("Signing Up ...");
                        signUp.setClickable(false);
                        signupApi();
                    }
                });

    }

    private void signupApi(){
        String url = Utils.getDOMAIN()+"profile/";

        final Map<String,String> signupData = new HashMap<>();
        signupData.put("username",username.getText().toString());
        signupData.put("email",email.getText().toString());
        signupData.put("password",password.getText().toString());
        signupData.put("institution",institution.getText().toString());

        JSONObject jsonData= null;
        try {
            jsonData = new JSONObject(signupData).put("is_teacher",checkBox.isChecked());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,jsonData, new Response.Listener<JSONObject>() {
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
                Log.d(TAG, "onErrorResponse: signupApi"+error);
                error.printStackTrace();
                Toast.makeText(SignUp.this, "!!!SignUp failed!!!\nPlease enter correct information \nand check you internet connection", Toast.LENGTH_SHORT).show();
                signUp.setText("SignUp");
                signUp.setClickable(true);
            }
        });

        VolleyPoint.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}
