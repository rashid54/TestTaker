package com.tutor.testtaker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DurationPickerDialog extends DialogFragment {
    private static final String TAG = "DurationPickerDialog";

    public TextView title;
    public NumberPicker hours;
    public NumberPicker minutes;
    public NumberPicker seconds;
    public Button button;

    interface DurationPickerInterface{
        public void ontimeset(int seconds);
    }

    DurationPickerInterface callback1;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateDialog: started");

        View view = getActivity().getLayoutInflater().inflate(R.layout.duration_picker_layout,null);
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity())
                .setView(view);

        title=view.findViewById(R.id.title);
        hours=view.findViewById(R.id.pkhour);
        minutes=view.findViewById(R.id.pkminute);
        seconds=view.findViewById(R.id.pksecond);
        button=view.findViewById(R.id.settime);
        hours.setMaxValue(5);
        minutes.setMaxValue(59);
        seconds.setMaxValue(59);

        hours.setMinValue(0);
        minutes.setMinValue(0);
        seconds.setMinValue(0);



        callback1= (DurationPickerInterface) getActivity();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time= hours.getValue()*3600+minutes.getValue()*60+seconds.getValue();
                callback1.ontimeset(time);
                dismiss();
            }
        });

        return builder.create();
    }
}
