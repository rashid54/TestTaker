package com.tutor.testtaker;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder>{
    private static final String TAG = "TestAdapter";

    private ArrayList<Test> testlist;
    Context context;

    public TestAdapter() {
    }

    public TestAdapter(Context context) {
        this.context = context;
    }

    public TestAdapter(ArrayList<Test> testlist, Context context) {
        this.testlist = testlist;
        this.context = context;
    }

    public TestAdapter(ArrayList<Test> testlist) {
        this.testlist = testlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: started");
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.test_layout,parent,false);
        return new TestAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txttestname.setText(testlist.get(position).getTestname());
        holder.txttest_id.setText("ID: "+testlist.get(position).getId());
        holder.txtcreated_by.setText("Creator ID: "+testlist.get(position).getUser_profile());
        holder.txtquestion_no.setText("Question No: "+testlist.get(position).getTotalques());
        holder.txtduration.setText("Duration: "+String.valueOf(testlist.get(position).getDuration()));

        holder.rlbody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo modify onclick actions
                startTest(testlist.get(position).getId(),testlist.get(position).getTestname(),testlist.get(position).getDuration());
            }
        });

    }

    @Override
    public int getItemCount() {
        return testlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txttestname;
        TextView txttest_id;
        TextView txtcreated_by;
        TextView txtquestion_no;
        TextView txtduration;
        RelativeLayout rlbody;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttestname=itemView.findViewById(R.id.txttestname);
            txttest_id=itemView.findViewById(R.id.txttest_id);
            txtcreated_by=itemView.findViewById(R.id.txtcreated_by);
            txtquestion_no=itemView.findViewById(R.id.txtquestion_no);
            txtduration= itemView.findViewById(R.id.txtduration);
            rlbody=itemView.findViewById(R.id.rltestbody);
        }
    }
    public void startTest(int test_id,String testname,long duration)
    {
        Intent intent= new Intent(context,TestPageActivity.class);
        intent.putExtra(context.getString(R.string.test_id),test_id);
        intent.putExtra(context.getString((R.string.testname)),testname);
        intent.putExtra(context.getString(R.string.duration),duration);
        context.startActivity(intent);
    }

    public void setTestlist(ArrayList<Test> testlist){
        this.testlist=testlist;
        notifyDataSetChanged();
    }

}
