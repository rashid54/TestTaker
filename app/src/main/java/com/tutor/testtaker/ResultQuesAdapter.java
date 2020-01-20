package com.tutor.testtaker;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ResultQuesAdapter extends QuesAdapter {
    public ResultQuesAdapter(ArrayList<Ques> queslist, ArrayList<String> givenAnslist, Context context) {
        super(queslist, givenAnslist, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        super.initviews(holder,position);
        
    }
}
