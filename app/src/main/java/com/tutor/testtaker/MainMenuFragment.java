package com.tutor.testtaker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

import static android.app.ProgressDialog.show;

public class MainMenuFragment extends Fragment{
    private static final String TAG = "MainMenuFragment";
    Button btnStartTest;

    Button Stat;
    Button Logout;
    Button Profile;
    Button Testlist;

    UserData userData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        final View view =inflater.inflate(R.layout.activity_main_menu,container,false);
        Logout=view.findViewById(R.id.btnLogout);
        Profile=view.findViewById(R.id.btnProfile);
        Stat = view.findViewById(R.id.stats);
        Testlist=view.findViewById(R.id.testlist);



        userData= new UserData(getContext());

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ViewProfile.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userData.setLoginStatus(false);
                Intent intent = new Intent(getActivity(), Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                userData.setLoginStatus(false);
                startActivity(intent);
            }
        });

        Stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: started");
                Intent intent = new Intent(getActivity(), ResultList.class);
                startActivity(intent);
            }});

        Testlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: started");
                Intent intent = new Intent(getActivity(), TestListActivity.class);
                startActivity(intent);
            }});


            String url = "https://presslu1.pythonanywhere.com/api/getid/";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d(TAG, "onResponse: started");
                    UserProfile userProfile = null;
                    try {
                        userProfile = new UserProfile(response.getString("username"), response.getString("email"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        userProfile.setId(response.getInt("id"));
                        userProfile.setIs_teacher(response.getBoolean("is_teacher"));
                        userData.setIsTeacher(response.getBoolean("is_teacher"));
                        userData.setUser_id(response.getInt("id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    userData.setUser(userProfile);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "onErrorResponse:couldn't save user info to sharedpreferences ");
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("Authorization", "token " + userData.getAuthToken());
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);
        requestQueue.start();
        return view;
        }
    }