package com.tutor.testtaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class CreateTest extends AppCompatActivity implements CreateQuestionDialog.CreateQuesion {
    private static final String TAG = "CreateTest";

    private EditText testname;
    private Button btnAddQuestion;
    private Button btnFinish;
    private RecyclerView recyclerView;

    private ArrayList<Ques> queslist;
    private ArrayList<Integer> quesIDlist;
    QuesAdapter quesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_test);
        Log.d(TAG, "onCreate: started");

        initviews();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        quesAdapter= new QuesAdapter(this.queslist,this);
        recyclerView.setAdapter(quesAdapter);
        quesAdapter.setQueslist(queslist);

        btnAddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: started");
                CreateQuestionDialog createQuestionDialog= new CreateQuestionDialog();
                createQuestionDialog.show(getSupportFragmentManager(),"Create Question Dialog");
            }
        });

    }

    private void initviews(){
        testname= findViewById(R.id.eN5);
        btnAddQuestion= findViewById(R.id.btnadd);
        recyclerView= findViewById(R.id.recview);

        queslist= new ArrayList<>();
        quesIDlist= new ArrayList<>();
    }

    @Override
    public void onAddQues(int id, Ques ques) {
        quesIDlist.add(id);
        queslist.add(ques);
        quesAdapter.setQueslist(queslist);
    }
}
