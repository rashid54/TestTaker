package com.tutor.testtaker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class NaviagationDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView username;
    TextView email;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Intent intent;
    UserData userData;
    //FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naviagation_drawer);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        userData= new UserData(this);
        View header= navigationView.getHeaderView(0);
        username= (TextView) header.findViewById(R.id.text);
        email= (TextView) header.findViewById(R.id.email);
        username.setText(userData.getUser().getUsername());
        email.setText(userData.getUser().getEmail());

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container,new MainMenuFragment()).commit();

    }
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }

        }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.mainmenu:
                intent = new Intent(NaviagationDrawer.this, MainMenu.class);
                break;
            case R.id.profile:
                intent = new Intent(NaviagationDrawer.this, ViewProfile.class);
            break;

            case R.id.logout:

                intent = new Intent(NaviagationDrawer.this, Login.class);
                break;
            case R.id.credit:

                intent = new Intent(NaviagationDrawer.this, Credit.class);
                break;
            case R.id.about:

                intent = new Intent(NaviagationDrawer.this, About.class);
                break;
        }
        startActivity(intent);

        return false;
    }
}