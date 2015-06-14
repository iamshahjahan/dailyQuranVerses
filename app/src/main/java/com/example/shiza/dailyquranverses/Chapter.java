package com.example.shiza.dailyquranverses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Chapter extends ActionBarActivity {
    String chapter_verse = "";
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        Intent i = getIntent();

        int id = i.getIntExtra("chapter_id", 0);
        String chapter_name = i.getStringExtra("chapter_name");

        setTitle(" Today's chapter is : " + chapter_name);
        String[] chapter = getResources().getStringArray(id);

        for ( int item = 0 ; item < chapter.length ; item++ )
        {
//            if ( item > 0 )
//            {
//                chapter_verse += item + ". " ;
//            }
            chapter_verse += chapter[item] + "\n";
        }
        textView = (TextView)findViewById(R.id.verse);
        textView.setText(chapter_verse);
        textView.setMovementMethod(new ScrollingMovementMethod());


    }


}
