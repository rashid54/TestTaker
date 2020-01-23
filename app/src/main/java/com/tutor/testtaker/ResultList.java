package com.tutor.testtaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ResultList extends AppCompatActivity {
    private static final String TAG = "ResultList";

    EditText testname;
    Button btnEnter;

    RecyclerView recyclerView;
    ResultAdapter resultAdapter;
    ArrayList<Result> resultlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list);

        initviews();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        resultAdapter= new ResultAdapter(this);

        getResultlist("");

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getResultlist(testname.getText().toString());
            }
        });
    }

    public void initviews()
    {
        testname= findViewById(R.id.testname);
        btnEnter= findViewById(R.id.btnEnter);
        recyclerView= findViewById(R.id.recview);
    }

    public void getResultlist(String str){
        Log.d(TAG, "getResultlist: started");

        String[] searchtxt= str.split("\\s+");
        str="";
        for(String st:searchtxt){
            str=str+"+"+st;
        }
        String url= "https://presslu1.pythonanywhere.com/api/testresult/?search="+str;

        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: started "+response);
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<Result>>() {
                }.getType();
                resultlist = gson.fromJson(response, type);

                recyclerView.setAdapter(resultAdapter);
                resultAdapter.setResultlist(resultlist);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: started Failed to get TestList");
                Toast.makeText(ResultList.this, "Failed to get TestList", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        requestQueue.start();
    }
}
