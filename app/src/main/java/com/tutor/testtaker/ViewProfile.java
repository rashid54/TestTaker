package com.tutor.testtaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ViewProfile extends AppCompatActivity {
    public static final String TAG="viewprofile";
      private Button home;
      private TextView name;
      private TextView email;
      private  TextView id;
      private  TextView institution;

      private UserData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        home=findViewById(R.id.btnhome);
        name=findViewById(R.id.textView1);
        email=findViewById(R.id.textView2);
        id=findViewById(R.id.textView3);
        institution=findViewById(R.id.textView4);
        userData= new UserData(this);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

                Intent intent = new Intent(ViewProfile.this, NaviagationDrawer.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);


            }
        });

        String url= Utils.getDOMAIN()+"getid/";
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "onResponse: started");
                try {
                    name.setText("User Name: "+response.getString("username"));
                    email.setText("Email: "+response.getString("email"));
                    id.setText("ID: "+response.getString("id"));
                    institution.setText("Instititution: "+response.getString("institution"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse:couldn't save user info to sharedpreferences ");
                Toast.makeText(ViewProfile.this, "Couldn't load profile info", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> data= new HashMap<>();
                data.put("Authorization","token "+userData.getAuthToken());
                return data;
            }
        };

        VolleyPoint.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}
