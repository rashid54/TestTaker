package com.tutor.testtaker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuesAdapter extends RecyclerView.Adapter<QuesAdapter.ViewHolder> {

    private static final String TAG = "QuesAdapter";
    ArrayList<Ques> queslist;
    Context context;

    public QuesAdapter() {
    }

    public QuesAdapter(ArrayList<Ques> queslist, Context context) {
        this.queslist = queslist;
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
        // set question and answers texts
        holder.txtQuestion.setText(queslist.get(position).getQuestion());
        holder.rdobtnAns0.setText(queslist.get(position).getAns()[0]);
        holder.rdobtnAns1.setText(queslist.get(position).getAns()[1]);
        holder.rdobtnAns2.setText(queslist.get(position).getAns()[2]);
        holder.rdobtnAns3.setText(queslist.get(position).getAns()[3]);

        holder.rdogrpAnswer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rdobtnAns0:
                        queslist.get(position).setAns_given(queslist.get(position).getAns()[0]);
                        break;
                    case R.id.rdobtnAns1:
                        queslist.get(position).setAns_given(queslist.get(position).getAns()[1]);
                        break;
                    case R.id.rdobtnAns2:
                        queslist.get(position).setAns_given(queslist.get(position).getAns()[2]);
                        break;
                    case R.id.rdobtnAns3:
                        queslist.get(position).setAns_given(queslist.get(position).getAns()[3]);
                        break;
                    default:
                        queslist.get(position).setAns_given(null);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return queslist.size();
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
        notifyDataSetChanged();
    }
}
