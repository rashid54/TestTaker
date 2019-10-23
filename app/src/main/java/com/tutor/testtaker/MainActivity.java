package com.tutor.testtaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private CountDownTimer lockTimer;
    private TextView txtLockTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Name= findViewById(R.id.eName);
        Password = findViewById(R.id.ePassword);
        Info = findViewById(R.id.tvInfo);
        Login = findViewById(R.id.btnLogin);

        Info.setText("No of attempts remaining: 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        if((userName.equals("Student")) && (userPassword.equals("1234"))){
            Intent intent = new Intent(MainActivity.this, StartTestActivity.class);
            Log.d(TAG, "validate: Started");

            startActivity(intent);
        }else{
            counter--;

            Info.setText("No of attempts remaining: " + counter);

            if(counter == 0){
                Login.setEnabled(false);
                txtLockTimer.setVisibility(View.VISIBLE);
                lockTimer.start();
                Log.d(TAG, "validate: timer started");
            }
        }
    } }