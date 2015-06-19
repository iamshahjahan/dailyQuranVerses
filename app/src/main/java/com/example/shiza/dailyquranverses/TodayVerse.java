package com.example.shiza.dailyquranverses;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodayVerse extends Fragment
{
    TextView textView;

    DailyQuranMethods dailyQuranMethods = new DailyQuranMethods();

    public TodayVerse() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_today_verse, container, false);
        textView = (TextView) view.findViewById(R.id.verse);
//        setChapterVerse();

        textView.setText(dailyQuranMethods.getVerseToday(dailyQuranMethods.DateToday(),getActivity().getApplicationContext()) + "\nChapter:" + dailyQuranMethods.getChapterTodayName(dailyQuranMethods.DateToday(),getActivity().getApplicationContext()));
        return view;
    }


}
