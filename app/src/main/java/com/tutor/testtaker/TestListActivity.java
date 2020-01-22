package com.tutor.testtaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

public class TestListActivity extends AppCompatActivity {
    public static final String TAG = "TestListActivity";

    Button btnCreateTest;
    TextView txtTestList;

    RecyclerView recyclerView;
    TestAdapter testAdapter;

    ArrayList<Test> testlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_list);

        initviews();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        testAdapter= new TestAdapter(this);

        initTestlist();

        btnCreateTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(TestListActivity.this,CreateTest.class);
                startActivity(intent);
            }
        });
    }

    public void initviews(){
        btnCreateTest= findViewById(R.id.btnadd);
        txtTestList= findViewById(R.id.txtlist);
        recyclerView=findViewById(R.id.recview);
    }

    public void initTestlist()
    {
        Log.d(TAG, "initTestlist: started");
        String url= "https://presslu1.pythonanywhere.com/api/test/";

        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: started "+response);
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<Test>>() {
                }.getType();
                testlist = gson.fromJson(response, type);

                recyclerView.setAdapter(testAdapter);
                testAdapter.setTestlist(testlist);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: started Failed to get TestList");
                Toast.makeText(TestListActivity.this, "Failed to get TestList", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        requestQueue.start();
    }
}
