package com.tutor.testtaker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreateTest extends AppCompatActivity implements CreateQuestionDialog.CreateQuesion , ListQuesAdapter.AddQuestion {
    private static final String TAG = "CreateTest";

    private EditText testname;
    private EditText duration;
    private Button btnAddQuestion;
    private Button btnQueslist;
    private Button btnFinish;
    private RecyclerView recyclerView;

    private ArrayList<Ques> queslist;
    private ArrayList<Integer> quesIDlist;
    QuesAdapter quesAdapter;
    UserData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_test);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Log.d(TAG, "onCreate: started");

        initviews();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        quesAdapter= new QuesAdapter(this.queslist,this){
            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                initviews(holder,position);
            }

            @Override
            public void initviews(ViewHolder holder, int position) {
                super.initviews(holder, position);

                holder.rdogrpAnswer.setClickable(false);
                holder.rdobtnAns0.setClickable(false);
                holder.rdobtnAns1.setClickable(false);
                holder.rdobtnAns2.setClickable(false);
                holder.rdobtnAns3.setClickable(false);

                if(queslist.get(position).getOpt1().equals(queslist.get(position).getAns())){
                    holder.rdobtnAns0.setTextColor(context.getResources().getColor(R.color.green));
                    holder.rdobtnAns0.setChecked(true);
                }
                else{
                    holder.rdobtnAns0.setTextColor(context.getResources().getColor(R.color.red));
                }
                if(queslist.get(position).getOpt2().equals(queslist.get(position).getAns())){
                    holder.rdobtnAns1.setTextColor(context.getResources().getColor(R.color.green));
                    holder.rdobtnAns1.setChecked(true);
                }
                else{
                    holder.rdobtnAns1.setTextColor(context.getResources().getColor(R.color.red));
                }
                if(queslist.get(position).getOpt3().equals(queslist.get(position).getAns())){
                    holder.rdobtnAns2.setTextColor(context.getResources().getColor(R.color.green));
                    holder.rdobtnAns2.setChecked(true);
                }
                else{
                    holder.rdobtnAns2.setTextColor(context.getResources().getColor(R.color.red));
                }
                if(queslist.get(position).getOpt4().equals(queslist.get(position).getAns())){
                    holder.rdobtnAns3.setTextColor(context.getResources().getColor(R.color.green));
                    holder.rdobtnAns3.setChecked(true);
                }
                else{
                    holder.rdobtnAns3.setTextColor(context.getResources().getColor(R.color.red));
                }
            }
        };
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

        btnQueslist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: started");
                QuesListDialog quesListDialog= new QuesListDialog();
                quesListDialog.show(getSupportFragmentManager(),"Queslist Dialog");
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo resolve 5 into duration and exit to another activity
                postTestApi(testname.getText().toString(),Integer.parseInt(duration.getText().toString()),quesIDlist);
            }
        });
    }

    private void initviews(){
        testname= findViewById(R.id.eN5);
        duration= findViewById(R.id.timeset);
        btnAddQuestion= findViewById(R.id.btnadd);
        btnQueslist= findViewById(R.id.btnqueslist);
        btnFinish= findViewById(R.id.btnfinish);
        recyclerView= findViewById(R.id.recview);

        queslist= new ArrayList<>();
        quesIDlist= new ArrayList<>();

        userData= new UserData(this);
    }

    @Override
    public void onAddQues(int id, Ques ques) {
        quesIDlist.add(id);
        queslist.add(ques);
        quesAdapter.setQueslist(queslist);
    }

    public void postTestApi(String testname,int duration,ArrayList<Integer> quesIDlist){
        String url= "https://presslu1.pythonanywhere.com/api/test/";
        JSONArray quesidarray=new JSONArray(quesIDlist);
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("testname",testname);
            jsonObject.put("duration",duration);
            jsonObject.put("questions",quesidarray);
            jsonObject.put("totalques",quesIDlist.size());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "onResponse: started");
                Toast.makeText(CreateTest.this, "Test Created Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CreateTest.this, MainMenu.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: started");
                Toast.makeText(CreateTest.this, "Test couldn't be created", Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> map= new HashMap<>();
                map.put("Authorization","token "+userData.getAuthToken());
                return map;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
        requestQueue.start();
    }

    @Override
    public void addQues(Ques ques) {
        quesIDlist.add(ques.getId());
        queslist.add(ques);
        quesAdapter.setQueslist(queslist);
    }
}
