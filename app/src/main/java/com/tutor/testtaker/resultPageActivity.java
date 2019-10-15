package com.tutor.testtaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class resultPageActivity extends AppCompatActivity {

    TextView txtTestName,txtTestTopic,txtTestTime,txtTestScore,txtQues;
    Button btnMainMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        init();

    }

    public void init(){
        txtTestName=findViewById(R.id.txtTestName);
        txtTestTopic=findViewById(R.id.txtTestTopic);
        txtTestTime=findViewById(R.id.txtTestTime);
        txtTestScore=findViewById(R.id.txtTestScore);
        txtQues=findViewById(R.id.txtQues);
        btnMainMenu=findViewById(R.id.btnMainMenu);
    }
}
