package com.example.shiza.dailyquranverses;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodayVerse extends Fragment
{
    TextView textView;
    String[] chapterName;
    int chapter_no;
    int id;

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

        chapterName = getResources().getStringArray(R.array.chapters);

        chapter_no = GetRandom(1, 114);

        String chapter_array_name = "chapter_" + chapter_no;

        id = getResources().getIdentifier(chapter_array_name, "array", getActivity().getApplicationContext().getPackageName());
        String[] chapter = getResources().getStringArray(id);

        int random_verse = GetRandom(1, chapter.length);

        textView.setText(chapter[random_verse - 1] + "\nChapter:" + chapterName[chapter_no - 1]);
        return view;
    }

    public int GetRandom(int min, int max)
    {
        Random ran = new Random();
        return ran.nextInt((max - min) + 1) + min;
    }


}
