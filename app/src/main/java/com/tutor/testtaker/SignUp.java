package com.tutor.testtaker;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    private Button SignUp;
    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;

    private  TextView SignUpMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        SignUp = findViewById(R.id.btnSignUp);
        Name= findViewById(R.id.eName);
        Password = findViewById(R.id.ePassword);
        Info = findViewById(R.id.tvInfo);
        Login = findViewById(R.id.btnLogin);
        SignUpMessage=findViewById(R.id.SignUpMsg);



        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, Login.class);


                startActivity(intent);
            }
        });

    }
}
