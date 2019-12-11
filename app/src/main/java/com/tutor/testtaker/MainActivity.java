
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





        Timer = new CountDownTimer(1500,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d(TAG, "onTick: running1");

                Log.d(TAG, "onTick: running ended");
            }

            @Override
            public void onFinish() {

                Intent intent = new Intent(MainActivity.this, Login.class);


                startActivity(intent);

            }
        };
        Timer.start();
    }


 }
