package com.tutor.testtaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
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
            }
        }
    } }