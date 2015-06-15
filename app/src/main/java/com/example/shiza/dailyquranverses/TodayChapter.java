package com.example.shiza.dailyquranverses;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodayChapter extends Fragment
{
    String chapter_verse = "";
    TextView textView;
    int id;
    String[] chapterName;
    int chapter_no;

    public TodayChapter()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_today_chapter, container, false);
        textView = (TextView) view.findViewById(R.id.verse);
        chapterName = getResources().getStringArray(R.array.chapters);

        chapter_no = GetRandom(1, 114);

        String chapter_array_name = "chapter_" + chapter_no;

        id = getResources().getIdentifier(chapter_array_name, "array", getActivity().getApplicationContext().getPackageName());
        String[] chapter = getResources().getStringArray(id);

        for ( int item = 0 ; item < chapter.length ; item++ )
        {
            chapter_verse += chapter[item] + "\n";
        }
        textView.setText("Today's chapter is: " + chapterName[chapter_no - 1] + "\n" + chapter_verse);
        return view;
    }

    public int GetRandom(int min, int max)
    {
        Random ran = new Random();
        return ran.nextInt((max - min) + 1) + min;
    }
}
