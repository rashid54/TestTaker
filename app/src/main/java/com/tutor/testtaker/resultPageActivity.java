package com.tutor.testtaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class resultPageActivity extends AppCompatActivity {
    private static final String TAG = "resultPageActivity";

    TextView txtTestName,txtTestTopic,txtTestTime,txtTestScore,txtQues;
    Button btnMainMenu;
    RecyclerView recvQueslist;
    ArrayList<Ques> quesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();

        Bundle resultBundle;
        try {
            resultBundle=getIntent().getBundleExtra(getString(R.string.resultBundle));

            txtTestName.setText(resultBundle.getString(getString(R.string.testName),"Test name not found"));
            txtTestTopic.setText("Topic: "+resultBundle.getString(getString(R.string.testTopic),"topic not found"));
            txtTestTime.setText("Time: "+resultBundle.getString(getString(R.string.resultTime),"time not received"));
            txtTestScore.setText("SCORE:\n"+resultBundle.getString(getString(R.string.testScore),"score not found"));
            txtQues.setText("Ques: "+resultBundle.getString(getString(R.string.totalQues),"not found"));
            quesList=resultBundle.getParcelableArrayList(getString(R.string.quesList));
        }catch (NullPointerException ne)
        {
            Log.d(TAG, "onCreate: Null resultbundle found in resultPage");
        }

        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(resultPageActivity.this,StartTestActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        recvQueslist.setLayoutManager(new LinearLayoutManager(this));
        QuesAdapter adapter=new QuesAdapter(this.quesList,this,true);
        recvQueslist.setAdapter(adapter);
        adapter.setQueslist(quesList);
    }

    public void init(){
        txtTestName=findViewById(R.id.txtTestName);
        txtTestTopic=findViewById(R.id.txtTestTopic);
        txtTestTime=findViewById(R.id.txtTestTime);
        txtTestScore=findViewById(R.id.txtTestScore);
        txtQues=findViewById(R.id.txtQues);
        btnMainMenu=findViewById(R.id.btnMainMenu);
        recvQueslist=findViewById(R.id.recvQueslist);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(resultPageActivity.this,StartTestActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
