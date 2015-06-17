package com.example.shiza.dailyquranverses;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


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
    SharedPreferences sharedPreferencesChapter;
    private static String TODAY_CHAPTER = "TODAY_CHAPTER";

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

        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
        String today_string = df.format(c.getTime());
        String[] chapter = getChapterTodayContent(today_string);

//        Toast.makeText(getActivity().getApplicationContext(),"Date is " + today_string,Toast.LENGTH_LONG).show();

        for ( int item = 0 ; item < chapter.length ; item++ )
        {
            chapter_verse += chapter[item] + "\n";
        }
        textView.setText("Today's chapter is: " + getChapterToday(today_string) + "\n" + chapter_verse);
        chapter_verse="";
        return view;
    }
    public String[] getChapterTodayContent(String today)
    {
        sharedPreferencesChapter = getActivity().getApplicationContext().getSharedPreferences(TODAY_CHAPTER, Context.MODE_PRIVATE);
        int chapter_no = sharedPreferencesChapter.getInt(today,2);
        String chapter_array_name = "chapter_" + chapter_no;
        id = getResources().getIdentifier(chapter_array_name, "array", getActivity().getApplicationContext().getPackageName());
        return getResources().getStringArray(id);
    }

    public String getChapterToday(String today)
    {
        sharedPreferencesChapter = getActivity().getApplicationContext().getSharedPreferences(TODAY_CHAPTER,Context.MODE_PRIVATE);
        int chapter_no = sharedPreferencesChapter.getInt(today,0);
        chapterName = getResources().getStringArray(R.array.chapters);
        return chapterName[chapter_no - 1];
    }
}
