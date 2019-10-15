package com.tutor.testtaker;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;

public class TestPageActivity extends AppCompatActivity {

    long time;
    ArrayList<Ques> quesList;

    RecyclerView recview;
    TextView txtTestName;
    Button btnFinish;
    TextView txttimer;
    ProgressBar timerbar;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_page);

        init();
        initquestions();

        txtTestName.setText("The Name of the Test");
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                Intent intent=new Intent(TestPageActivity.this,resultPageActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(getString(R.string.resultBundle),getResultBundle());
                startActivity(intent);
                //TODO: code for finishing the the test
            }
        });

        recview.setLayoutManager(new LinearLayoutManager(this));
        QuesAdapter quesAdapter=new QuesAdapter(this.quesList,this);
        recview.setAdapter(quesAdapter);
        quesAdapter.setQueslist(quesList);

    }

    public void init(){
        time=16;
        quesList=new ArrayList<>();
        txtTestName=findViewById(R.id.txtTestName);
        btnFinish=findViewById(R.id.btnFinish);
        recview=findViewById(R.id.recvQuestions);
        timerbar=findViewById(R.id.timerBar);
        txttimer=findViewById(R.id.txttimer);
        timerbar.setMax((int) time);
        timerbar.setProgress((int) time);
        timer= new CountDownTimer(time*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerbar.setProgress((int) (millisUntilFinished/1000));
                String str=String.format("%02d:%02d / %02d:%02d",millisUntilFinished/60000,(millisUntilFinished/1000)%60,time/60,time%60);
                txttimer.setText(str);
            }

            @Override
            public void onFinish() {
                Toast.makeText(TestPageActivity.this,"!! The Time is UP !!",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(TestPageActivity.this,resultPageActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                timer.cancel();
                intent.putExtra(getString(R.string.resultBundle),getResultBundle());
                startActivity(intent);
            }
        };
        timer.start();
    }
    public void initquestions(){
        quesList.add(new Ques("What is the extention of c1 plusplus?",".cpp",".c++",".cplusplus",".cplus",".cpp"));
        quesList.add(new Ques("What is the extention of c2 plusplus?",".cpp",".c++",".cplusplus",".cplus",".cpp"));
        quesList.add(new Ques("What is the extention of c3 plusplus?",".cpp",".c++",".cplusplus",".cplus",".cpp"));
        quesList.add(new Ques("What is the extention of c4 plusplus?",".cpp",".c++",".cplusplus",".cplus",".cpp"));
        quesList.add(new Ques("What is the extention of c5 plusplus?",".cpp",".c++",".cplusplus",".cplus",".cpp"));
        quesList.add(new Ques("What is the extention of c6 plusplus?",".cpp",".c++",".cplusplus",".cplus",".cpp"));

    }

    @Override
    public void onBackPressed() {
        timer.cancel();
        startActivity(new Intent(TestPageActivity.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    public Bundle getResultBundle()
    {
        Bundle bundleResult=new Bundle();
        bundleResult.putString(getString(R.string.testName),txtTestName.getText().toString());
        bundleResult.putString(getString(R.string.testTopic),"The Topic of the test");
        bundleResult.putString(getString(R.string.resultTime), String.valueOf(time));
        bundleResult.putString(getString(R.string.totalQues), String.valueOf(quesList.size()));
        bundleResult.putString(getString(R.string.testScore), String.valueOf(Test.totalCorrectAns(quesList)));
        bundleResult.putParcelableArrayList(getString(R.string.quesList),quesList);

        return bundleResult;
    }
}
