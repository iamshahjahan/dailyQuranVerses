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
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodayVerse extends Fragment
{
    TextView textView;
    String[] chapterName;
    int id;
    SharedPreferences sharedPreferencesChapter;
    SharedPreferences sharedPreferencesVerse;

    private static String TODAY_CHAPTER = "TODAY_CHAPTER";
    private static String TODAY_VERSE = "TODAY_VERSE";

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
        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
        String today_string = df.format(c.getTime());




        textView.setText(getVerseToday(today_string) + "\nChapter:" + getChapterToday(today_string));
        return view;
    }

    public int GetRandom(int min,int max)
    {
        Random ran = new Random();
        return ran.nextInt((max - min) + 1) + min;
    }

    public String getVerseToday(String today)
    {
        sharedPreferencesVerse = getActivity().getApplicationContext().getSharedPreferences(TODAY_VERSE, Context.MODE_PRIVATE);
        return sharedPreferencesVerse.getString(today,"7. The Way of those on whom You have bestowed Your Grace, not (the way) of those who earned Your Anger (such as the Jews), nor of those who went astray (such as the Christians).");
    }

    public String getChapterToday(String today)
    {
        sharedPreferencesChapter = getActivity().getApplicationContext().getSharedPreferences(TODAY_CHAPTER,Context.MODE_PRIVATE);
        int chapter_no = sharedPreferencesChapter.getInt(today,0);
        chapterName = getResources().getStringArray(R.array.chapters);
        return chapterName[chapter_no - 1];

    }

    public String getVerse(String chapter_array_name)
    {
        id = getResources().getIdentifier(chapter_array_name, "array", getActivity().getApplicationContext().getPackageName());
        String[] chapter = getResources().getStringArray(id);
        int random_verse = GetRandom(1, chapter.length - 1);
        return chapter[random_verse];
    }
}
