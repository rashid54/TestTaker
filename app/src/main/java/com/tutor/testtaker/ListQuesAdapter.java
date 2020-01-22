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

    public ListQuesAdapter(Context context, QuesListDialog dialog) {
        super(context);
        this.dialog = dialog;
    }

    public interface AddQuestion{
        public void addQues(Ques ques);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        initviews(holder,position);

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
}
