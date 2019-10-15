package com.tutor.testtaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class resultPageActivity extends AppCompatActivity {
    private static final String TAG = "resultPageActivity";

    TextView txtTestName,txtTestTopic,txtTestTime,txtTestScore,txtQues;
    Button btnMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        init();

        Bundle resultBundle;
        try {
            resultBundle=getIntent().getBundleExtra(getString(R.string.resultBundle));

            txtTestName.setText(resultBundle.getString(getString(R.string.testName),"Test name not found"));
            txtTestTopic.setText("Topic: "+resultBundle.getString(getString(R.string.testTopic),"topic not found"));
            txtTestTime.setText("Time: "+resultBundle.getString(getString(R.string.resultTime),"time not received"));
            txtTestScore.setText("SCORE:\n"+resultBundle.getString(getString(R.string.testScore),"score not found"));
            txtQues.setText("Ques: "+resultBundle.getString(getString(R.string.totalQues),"not found"));

        }catch (NullPointerException ne)
        {
            Log.d(TAG, "onCreate: Null resultbundle found in resultPage");
        }

        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(resultPageActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void init(){
        txtTestName=findViewById(R.id.txtTestName);
        txtTestTopic=findViewById(R.id.txtTestTopic);
        txtTestTime=findViewById(R.id.txtTestTime);
        txtTestScore=findViewById(R.id.txtTestScore);
        txtQues=findViewById(R.id.txtQues);
        btnMainMenu=findViewById(R.id.btnMainMenu);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(resultPageActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
