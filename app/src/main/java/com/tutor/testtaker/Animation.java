 package com.tutor.testtaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

 public class Animation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Thread timer = new Thread () {
            public void run () {
                try {
                    sleep(5000);
                }
                catch ( InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    Intent intent=new Intent(Animation.this, MainMenu.class);
                    startActivity(intent);
                }
            }
        };
        timer.start();}
        @Override
                protected void onPause() {
            super.onPause();
            finish();
        }
    }

