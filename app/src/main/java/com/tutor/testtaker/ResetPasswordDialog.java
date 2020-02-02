package com.tutor.testtaker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ResetPasswordDialog extends DialogFragment {
    private static final String TAG = "ResetPasswordDialog";

    private EditText etxtemail;
    private Button btnSubmit;

    public interface ResetPasswordInterface{
        public void resetPassword(String email);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateDialog: ");
        View view= getActivity().getLayoutInflater().inflate(R.layout.dialog_reset_password,null);
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity())
                .setView(view);

        initviews(view);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetPasswordInterface resetPasswordInterface= (ResetPasswordInterface) getActivity();
                resetPasswordInterface.resetPassword(etxtemail.getText().toString());
                dismiss();
            }
        });

        return builder.create();
    }
    public void initviews(View view){
        etxtemail=view.findViewById(R.id.email);
        btnSubmit= view.findViewById(R.id.btnsubmit);
    }
}
