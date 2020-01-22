
package com.tutor.testtaker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import static com.tutor.testtaker.R.anim.bottom_animation;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private CountDownTimer Timer;
    private static int SPLASH_SCREEN = 5000;
    ImageView image;
    TextView logo, slogan;
    android.view.animation.Animation topAnim;
    android.view.animation.Animation bottomAnim;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_main);
        image = findViewById(R.id.imageView);
        logo = findViewById(R.id.textView5);
        slogan = findViewById(R.id.textView6);


        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this, bottom_animation);


        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);

        intent = new Intent(MainActivity.this, Login.class);

        UserData userData= new UserData(this);
        if(userData.isLoginStatus()==true){
            intent= new Intent(MainActivity.this,MainMenu.class);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                Pair[]  pairs = new Pair[2];
                pairs[0] = new Pair<View,String>(image, "image");
                pairs[1] = new Pair<View,String>(logo, "textview5");

                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                startActivity(intent,options.toBundle());
                finish();

            }
        }, SPLASH_SCREEN);
    }


 }
