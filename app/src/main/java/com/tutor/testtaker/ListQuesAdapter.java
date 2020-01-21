package com.tutor.testtaker;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ListQuesAdapter extends QuesAdapter {
    private static final String TAG = "ListQuesAdapter";

    QuesListDialog dialog;

    public ListQuesAdapter(ArrayList<Ques> queslist, Context context,QuesListDialog dialog) {
        super(queslist, context);
        this.dialog= dialog;
    }

    public interface AddQuestion{
        public void addQues(Ques ques);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        super.initviews(holder,position);

        holder.rlBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddQuestion addQuestion= (AddQuestion) context;
                addQuestion.addQues(queslist.get(position));
                addQuestion = (AddQuestion) dialog;
                addQuestion.addQues(queslist.get(position));
            }
        });
    }
}
