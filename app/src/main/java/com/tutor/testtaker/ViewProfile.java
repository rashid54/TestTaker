package com.tutor.testtaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewProfile extends AppCompatActivity {

      private Button home;
       private UserData userData;
       private TextView name;
       private TextView email;
       private  TextView id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        userData=new UserData(this);
        home=findViewById(R.id.btnhome);
        name=findViewById(R.id.textView1);
        email=findViewById(R.id.textView2);
        id=findViewById(R.id.textView3);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ViewProfile.this, StartTestActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);


            }
        });
        UserProfile userprofile ;
        userprofile=userData.getUser();
        name.setText(userprofile.getUsername());
        email.setText(userprofile.getEmail());
        id.setText(userprofile.getId());
    }
}
