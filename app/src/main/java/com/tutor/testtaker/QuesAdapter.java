package com.tutor.testtaker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuesAdapter extends RecyclerView.Adapter<QuesAdapter.ViewHolder> {
    private static final String TAG = "QuesAdapter";

    ArrayList<Ques> queslist;
    ArrayList<String> givenAnslist;
    Context context;

    public QuesAdapter() {
    }

    public QuesAdapter(ArrayList<Ques> queslist, Context context) {
        this.queslist = queslist;
        this.context = context;
        this.givenAnslist = new ArrayList<>(queslist.size());
    }

    public QuesAdapter(ArrayList<Ques> queslist, ArrayList<String> givenAnslist, Context context) {
        this.queslist = queslist;
        this.givenAnslist = givenAnslist;
        this.context = context;
    }

    public QuesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: started");
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ques_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        initviews(holder,position);
        holder.rdogrpAnswer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rdobtnAns0:
                        givenAnslist.set(position,queslist.get(position).getOpt1());
                        break;
                    case R.id.rdobtnAns1:
                        givenAnslist.set(position,queslist.get(position).getOpt2());
                        break;
                    case R.id.rdobtnAns2:
                        givenAnslist.set(position,queslist.get(position).getOpt3());
                        break;
                    case R.id.rdobtnAns3:
                        givenAnslist.set(position,queslist.get(position).getOpt4());
                        break;
                    default:
                        givenAnslist.set(position,null);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return queslist.size();
    }

    public void initviews(ViewHolder holder,int position){
        // set question and answers texts
        holder.txtQuestion.setText(queslist.get(position).getQuestion());
        holder.rdobtnAns0.setText(queslist.get(position).getOpt1());
        holder.rdobtnAns1.setText(queslist.get(position).getOpt2());
        holder.rdobtnAns2.setText(queslist.get(position).getOpt3());
        holder.rdobtnAns3.setText(queslist.get(position).getOpt4());

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtQuestion;
        RadioGroup rdogrpAnswer;
        RadioButton rdobtnAns0,rdobtnAns1,rdobtnAns2,rdobtnAns3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQuestion=itemView.findViewById(R.id.txtquestion);
            rdogrpAnswer=itemView.findViewById(R.id.rdogrpAnswer);
            rdobtnAns0=itemView.findViewById(R.id.rdobtnAns0);
            rdobtnAns1=itemView.findViewById(R.id.rdobtnAns1);
            rdobtnAns2=itemView.findViewById(R.id.rdobtnAns2);
            rdobtnAns3=itemView.findViewById(R.id.rdobtnAns3);
        }
    }

    public void setQueslist(ArrayList<Ques> queslist) {
        this.queslist = queslist;
        this.givenAnslist = new ArrayList<>(queslist.size());
        notifyDataSetChanged();
    }

    public ArrayList<String> getGivenAnslist() {
        return givenAnslist;
    }

    public void setGivenAnslist(ArrayList<String> givenAnslist) {
        this.givenAnslist = givenAnslist;
        this.notifyDataSetChanged();
    }
}
