package com.example.shiza.dailyquranverses;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class Quran extends ActionBarActivity implements AdapterView.OnItemSelectedListener
{
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran);
        spinner = (Spinner)findViewById(R.id.selectChapter);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.chapters,android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int quran_id;
        String chapter_verse="";
        TextView textView;
        position++;
        String chapter_array_name = "chapter_" + position;

        quran_id = getResources().getIdentifier(chapter_array_name, "array",this.getPackageName());
        String[] chapter = getResources().getStringArray(quran_id);

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

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {
        Toast.makeText(this,"Please enter your choice",Toast.LENGTH_LONG).show();
    }
}
