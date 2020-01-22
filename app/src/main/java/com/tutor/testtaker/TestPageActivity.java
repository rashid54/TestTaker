package com.tutor.testtaker;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

public class TestPageActivity extends AppCompatActivity {
    private static final String TAG = "TestPageActivity";

    int test_id;
    long duration;
    String testname;
    ArrayList<Ques> quesList;

    RecyclerView recview;
    QuesAdapter quesAdapter;
    TextView txtTestName;
    Button btnFinish;
    TextView txttimer;
    ProgressBar timerbar;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_page);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //todo set bundle
        try {
            Intent intent= getIntent();
            test_id= intent.getIntExtra(getString(R.string.test_id),-1);
            testname= intent.getStringExtra(getResources().getString(R.string.testname));
        }catch (NullPointerException e){
            Log.d(TAG, "onCreate: null pointer exception in testPageActivity");
        }

        init();
        //initquestions();

        txtTestName.setText(testname);
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
        quesAdapter=new QuesAdapter(this);
        initTestQuestions(test_id);
        timer.start();
    }

    public void init(){
        duration =16;
        quesList=new ArrayList<>();
        txtTestName=findViewById(R.id.txtTestName);
        btnFinish=findViewById(R.id.btnFinish);
        recview=findViewById(R.id.recvQuestions);
        timerbar=findViewById(R.id.timerBar);
        txttimer=findViewById(R.id.txttimer);
        timerbar.setMax((int) duration);
        timerbar.setProgress((int) duration);
        timer= new CountDownTimer(duration *1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerbar.setProgress((int) (millisUntilFinished/1000));
                String str=String.format("%02d:%02d / %02d:%02d",millisUntilFinished/60000,(millisUntilFinished/1000)%60, duration /60, duration %60);
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
    }
    public void initquestions(){
        quesList.add(new Ques("What is the extention of c1 plusplus?",".cpp",".c++",".cplusplus",".cplus",".cpp"));
        quesList.add(new Ques("What is the extention of c2 plusplus?",".cpp",".c++",".cplusplus",".cplus",".cpp"));
        quesList.add(new Ques("What is the extention of c3 plusplus?",".cpp",".c++",".cplusplus",".cplus",".cpp"));
        quesList.add(new Ques("What is the extention of c4 plusplus?",".cpp",".c++",".cplusplus",".cplus",".cpp"));
        quesList.add(new Ques("What is the extention of c5 plusplus?",".cpp",".c++",".cplusplus",".cplus",".cpp"));
        quesList.add(new Ques("What is the extention of c6 plusplus?",".cpp",".c++",".cplusplus",".cplus",".cpp"));

    }
    public void initTestQuestions(int test_id){
        String url="https://presslu1.pythonanywhere.com/api/gettestquestion/"+test_id;

        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: started");
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<Ques>>() {
                }.getType();
                quesList = gson.fromJson(response, type);
                recview.setAdapter(quesAdapter);
                quesAdapter.setQueslist(quesList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: started");
                quesList= null;
            }
        });
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
        bundleResult.putString(getString(R.string.resultTime), String.valueOf(duration));
        bundleResult.putString(getString(R.string.totalQues), String.valueOf(quesList.size()));
        //bundleResult.putString(getString(R.string.testScore), String.valueOf(Test.totalCorrectAns(quesList,quesAdapter.getSelectedAnslist())));
        bundleResult.putParcelableArrayList(getString(R.string.quesList),quesList);
        bundleResult.putStringArrayList(getString(R.string.selectedAnslist),quesAdapter.getSelectedAnslist());

        return bundleResult;
    }
}
