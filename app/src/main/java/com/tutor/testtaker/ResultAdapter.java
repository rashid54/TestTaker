package com.tutor.testtaker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder>{
    Context context;
    ArrayList<Result> resultlist;

    public ResultAdapter(Context context) {
        this.context = context;
    }

    public ResultAdapter(Context context, ArrayList<Result> resultlist) {
        this.context = context;
        this.resultlist = resultlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.result_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtTestname.setText(resultlist.get(position).getTestname());
        holder.txtUserName.setText(resultlist.get(position).getUsername());
        holder.txtScore.setText(resultlist.get(position).getScore());

        holder.txtTestname.setClickable(false);
        holder.txtUserName.setClickable(false);
        holder.txtScore.setClickable(false);

        holder.rlbody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo add result onclick actions
            }
        });

    }

    @Override
    public int getItemCount() {
        return resultlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTestname;
        TextView txtUserName;
        TextView txtScore;
        RelativeLayout rlbody;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTestname= itemView.findViewById(R.id.txttestname);
            txtUserName= itemView.findViewById(R.id.txtid);
            txtScore= itemView.findViewById(R.id.txtscore);
            rlbody= itemView.findViewById(R.id.rlresultbody);
        }
    }

    public void setResultlist(ArrayList<Result> resultlist) {
        this.resultlist = resultlist;
        notifyDataSetChanged();
    }
}
