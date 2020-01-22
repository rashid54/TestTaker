package com.tutor.testtaker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class QuesListDialog extends DialogFragment implements ListQuesAdapter.AddQuestion {
    public static final String TAG = "QuesListDialog";

    ArrayList<Ques> queslist;
    RecyclerView recyclerView;
    ListQuesAdapter listQuesAdapter;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateDialog: started");
        View view= getActivity().getLayoutInflater().inflate(R.layout.dialog_ques_list,null);
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity())
                .setView(view);

        initviews(view);
        initQueslist();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listQuesAdapter= new ListQuesAdapter(getContext(),this);

        return builder.create();
    }

    private void initviews(View view) {
        recyclerView= view.findViewById(R.id.recview);
    }

    @Override
    public void addQues(Ques ques) {
        dismiss();
    }

    public void initQueslist()
    {
        String url= "https://presslu1.pythonanywhere.com/api/question/";

        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: started");
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<Ques>>() {
                }.getType();
                queslist = gson.fromJson(response, type);
                recyclerView.setAdapter(listQuesAdapter);
                listQuesAdapter.setQueslist(queslist);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: started Failed to get TestList");
                Toast.makeText(getContext(), "Failed to get TestList", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
        requestQueue.start();
    }
}
