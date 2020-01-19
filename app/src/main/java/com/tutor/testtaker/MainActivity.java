
package com.tutor.testtaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private CountDownTimer Timer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        UserData userData = new UserData(this);
        if(userData.isLoginStatus()==true)
        {
            Intent intent = new Intent(Login.this, Animation.class);
            startActivity(intent);
        }


        Timer = new CountDownTimer(1500,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d(TAG, "onTick: running1");

                Log.d(TAG, "onTick: running ended");
            }

            @Override
            public void onFinish() {

                Intent intent = new Intent(MainActivity.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);


                startActivity(intent);

            }
        };
        Timer.start();
    }


 }
