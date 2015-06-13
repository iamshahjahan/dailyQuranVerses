package com.example.shiza.dailyquranverses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends ActionBarActivity
{
    TextView textView;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.verse);

        String[] chapterName = getResources().getStringArray(R.array.chapters);

        int chapter_no = GetRandom(1,114);

        String chapter_array_name = "chapter_" + chapter_no;

        id = getResources().getIdentifier(chapter_array_name, "array",this.getPackageName());
        String[] chapter = getResources().getStringArray(id);

        int random_verse = GetRandom(1,chapter.length);

        textView.setText(chapter[random_verse] +"\nVerse No: " + random_verse + "\nChapter:" + chapterName[chapter_no - 1]);

    }

    public int GetRandom(int min,int max)
    {
            Random ran = new Random();
            return ran.nextInt((max-min) + 1) + min;
    }
    public void GetQuran(View view)
    {
        Intent intent = new Intent(this,Quran.class);
        startActivity(intent);
    }
    public void GetChapter(View view)
    {
        Intent intent = new Intent(this,Chapter.class);
        intent.putExtra("chapter_id",id);
        startActivity(intent);
    }

}
