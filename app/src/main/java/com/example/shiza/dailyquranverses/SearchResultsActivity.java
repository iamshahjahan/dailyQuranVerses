package com.example.shiza.dailyquranverses;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shiza.dailyquranverses.DailyQuranMethods;

public class SearchResultsActivity extends ActionBarActivity
{

    Toolbar toolbar;
    ListView listView;
    SearchView searchView;
    ArrayAdapter<String> adapter;
    DailyQuranMethods dailyQuranMethods = new DailyQuranMethods();
    final  String[] search = {"hello","where","how","when"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        toolbar = (Toolbar) findViewById(R.id.app_bar_search_results);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle(" Daily Qura'n Verses");
        setSupportActionBar(toolbar);
        Toast.makeText(this, "Hello from onCreate method", Toast.LENGTH_LONG).show();

//       using searchview to display data in the app

        searchView = (SearchView) findViewById(R.id.mySearchView);


        listView = (ListView) findViewById(R.id.listView);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, search);

        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }


}


