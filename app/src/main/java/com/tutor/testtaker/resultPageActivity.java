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

    TextView txtTestName,txtTestTopic,txtTestTime,txtTestScore, txtTotalQues;
    Button btnMainMenu;
    ResultQuesAdapter resultQuesAdapter;
    RecyclerView recvQueslist;
    ArrayList<Ques> quesList;
    ArrayList<String> selectedAnslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initviews();

        Bundle resultBundle;
        try {
            resultBundle=getIntent().getBundleExtra(getString(R.string.resultBundle));

            quesList=resultBundle.getParcelableArrayList(getString(R.string.quesList));
            selectedAnslist= resultBundle.getStringArrayList(getString(R.string.selectedAnslist));
            txtTestName.setText(resultBundle.getString(getString(R.string.testName),"Test name not found"));
            txtTestTopic.setText("Topic: "+resultBundle.getString(getString(R.string.testTopic),"topic not found"));
            txtTestTime.setText("Time: "+resultBundle.getString(getString(R.string.resultTime),"duration not received"));
            //txtTestScore.setText("SCORE:\n"+resultBundle.getString(getString(R.string.testScore),"score not found"));
            txtTestScore.setText("SCORE:\n"+totalCorrectAns(quesList,selectedAnslist));
            txtTotalQues.setText("Ques: "+resultBundle.getString(getString(R.string.totalQues),"not found"));

        }catch (NullPointerException ne)
        {
            Log.d(TAG, "onCreate: Null resultbundle found in resultPage");
        }

        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(resultPageActivity.this, MainMenu.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        recvQueslist.setLayoutManager(new LinearLayoutManager(this));
        resultQuesAdapter = new ResultQuesAdapter(quesList,selectedAnslist,this);
        recvQueslist.setAdapter(resultQuesAdapter);
        resultQuesAdapter.setQueslist(quesList);
        resultQuesAdapter.setSelectedAnslist(selectedAnslist);
    }

    public void initviews(){
        txtTestName=findViewById(R.id.txtTestName);
        txtTestTopic=findViewById(R.id.txtTestTopic);
        txtTestTime=findViewById(R.id.txtTestTime);
        txtTestScore=findViewById(R.id.txtTestScore);
        txtTotalQues =findViewById(R.id.txtQues);
        btnMainMenu=findViewById(R.id.btnMainMenu);
        recvQueslist=findViewById(R.id.recvQueslist);
    }

    public long totalCorrectAns(ArrayList<Ques> queslist,ArrayList<String>selectedAnslist)
    {
        int sum=0;
        for(int i=0;i<queslist.size();i++)
        {
            if(queslist.get(i).getAns()==selectedAnslist.get(i)){
                sum++;
            }
        }
        return sum;
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(resultPageActivity.this, MainMenu.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
