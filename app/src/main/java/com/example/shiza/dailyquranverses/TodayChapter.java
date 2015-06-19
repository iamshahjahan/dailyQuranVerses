package com.example.shiza.dailyquranverses;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodayChapter extends Fragment
{
    String chapter_verse = "";
    TextView textView;
    DailyQuranMethods dailyQuranMethods = new DailyQuranMethods();
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

       String[] chapter = dailyQuranMethods.getChapterTodayContent(dailyQuranMethods.DateToday(), getActivity().getApplicationContext());

//        Toast.makeText(getActivity().getApplicationContext(),"Date is " + today_string,Toast.LENGTH_LONG).show();

        for ( int item = 0 ; item < chapter.length ; item++ )
        {
            chapter_verse += chapter[item] + "\n";
        }
        textView.setText("Today's chapter is: " + dailyQuranMethods.getChapterTodayName(dailyQuranMethods.DateToday(), getActivity().getApplicationContext()) + "\n" + chapter_verse);
        chapter_verse="";
        return view;
    }

}
