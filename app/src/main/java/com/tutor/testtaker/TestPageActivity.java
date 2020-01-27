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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestPageActivity extends AppCompatActivity {
    private static final String TAG = "TestPageActivity";

    int test_id;
    long duration;
    String testname;
    String username;
    int result_id;
    int score;
    ArrayList<Ques> quesList;

    UserData userData;

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

        userData= new UserData(this);

        //todo set bundle
        try {
            Intent intent= getIntent();
            test_id= intent.getIntExtra(getString(R.string.test_id),-1);
            testname= intent.getStringExtra(getResources().getString(R.string.testname));
            duration= intent.getLongExtra(getResources().getString(R.string.duration),-1);
        }catch (NullPointerException e){
            Log.d(TAG, "onCreate: null pointer exception in testPageActivity");
        }

        init();
        //initquestions();

        txtTestName.setText(testname);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishTest();
            }
        });

        recview.setLayoutManager(new LinearLayoutManager(this));
        quesAdapter=new QuesAdapter(this);
        initTestQuestions(test_id);
    }

    public void init(){
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
                finishTest();
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
        String url=Utils.getDOMAIN()+"gettestquestion/"+test_id;

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
                timer.start();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: started");
                quesList= null;
            }
        });

        VolleyPoint.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onBackPressed() {
        timer.cancel();
        startActivity(new Intent(TestPageActivity.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    public Bundle getResultBundle()
    {
        Bundle bundleResult=new Bundle();

        bundleResult.putString(getString(R.string.testTopic),"The Topic of the test");
        bundleResult.putString(getString(R.string.testName),txtTestName.getText().toString());
        bundleResult.putString(getString(R.string.resultTime), String.valueOf(duration));
        bundleResult.putString(getString(R.string.totalQues), String.valueOf(quesList.size()));
        bundleResult.putString(getString(R.string.testScore), String.valueOf(score));
        bundleResult.putParcelableArrayList(getString(R.string.quesList),quesList);
        bundleResult.putStringArrayList(getString(R.string.selectedAnslist),quesAdapter.getSelectedAnslist());

        return bundleResult;
    }

    public JsonObjectRequest postResult(){
        Log.d(TAG, "postResult: started");
        String url= Utils.getDOMAIN()+"testresult/";

        JSONObject jsonObject= new JSONObject();
        try {
            username= userData.getUser().getUsername();
            score=totalCorrectAns(quesList,quesAdapter.getSelectedAnslist());
            jsonObject.put("test_id",this.test_id);
            jsonObject.put("score",score);
            jsonObject.put("testname",testname);
            jsonObject.put("username",username);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "onResponse: started");
                try {
                    result_id= response.getInt("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                moveToNextPage();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: started");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> map= new HashMap<>();
                map.put("Authorization","token "+userData.getAuthToken());
                return map;
            }
        };

        return jsonObjectRequest;

    }

    public void finishTest(){
        VolleyPoint.getInstance(this).addToRequestQueue(postResult());
    }

    public int totalCorrectAns(ArrayList<Ques> quesList,ArrayList<String> selectedAns){
        int sum=0;
        for(int i=0;i<quesList.size();i++)
        {
            if(quesList.get(i).getAns().equals(selectedAns.get(i))){
                sum++;
            }
        }
        return sum;
    }

    public void moveToNextPage(){
        timer.cancel();
        Intent intent=new Intent(TestPageActivity.this,resultPageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(getString(R.string.resultBundle),getResultBundle());
        startActivity(intent);
    }

}
