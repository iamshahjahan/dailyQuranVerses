package com.example.shiza.dailyquranverses;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;


public class SearchResultsActivity extends ActionBarActivity {

    Toolbar toolbar;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        toolbar = (Toolbar) findViewById(R.id.app_bar);
//        toolbar.setLogo(R.mipmap.ic_launcher);
//        toolbar.setTitle(" Daily Qura'n Verses");
//        setSupportActionBar(toolbar);
    Toast.makeText(this,"Hello from onCreate method",Toast.LENGTH_LONG).show();

    }
}