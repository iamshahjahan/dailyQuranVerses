package com.example.shiza.dailyquranverses;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
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
        setChapterVerse();

        Calendar c = Calendar.getInstance();

        int today = c.DATE;

        String today_string = today + "";


//        chapterName = getResources().getStringArray(R.array.chapters);
//
//        chapter_no = GetRandom(1, 114);
//
//        String chapter_array_name = "chapter_" + chapter_no;
//
//        id = getResources().getIdentifier(chapter_array_name, "array", getActivity().getApplicationContext().getPackageName());
//        String[] chapter = getResources().getStringArray(id);
//
//        int random_verse = GetRandom(1, chapter.length);

        textView.setText(getVerseToday(today_string) + "\nChapter:" + getChapterToday(today_string));
        return view;
    }

    public int GetRandom(int min,int max)
    {
        Random ran = new Random();
        return ran.nextInt((max - min) + 1) + min;
    }

    public void setChapterVerse()
    {

        sharedPreferencesChapter = getActivity().getApplicationContext().getSharedPreferences(TODAY_CHAPTER, Context.MODE_PRIVATE);
        sharedPreferencesVerse = getActivity().getApplicationContext().getSharedPreferences(TODAY_VERSE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor_chapter = sharedPreferencesChapter.edit();
        SharedPreferences.Editor editor_verse = sharedPreferencesVerse.edit();

        Calendar c = Calendar.getInstance();

        int today = c.DATE;

        String today_string = today + "";


        int chapter_no = GetRandom(1, 114);

        String chapter_array_name = "chapter_" + chapter_no;

        String verse = sharedPreferencesVerse.getString(today_string,null);

        if ( verse == null )
        {
            editor_verse.putString(today_string,getVerse(chapter_array_name));
            editor_verse.commit();
        }

        int chapter_number = sharedPreferencesChapter.getInt(today_string,0);

        if ( chapter_number == 0 )
        {
            editor_chapter.putInt(today_string,chapter_no -1);
            editor_chapter.commit();
        }


    }

    public String getVerseToday(String today)
    {
        sharedPreferencesVerse = getActivity().getApplicationContext().getSharedPreferences(TODAY_VERSE, Context.MODE_PRIVATE);
        return sharedPreferencesVerse.getString(today,"In the name of Allah, the Most gracious,the Most Merciful");
    }

    public String getChapterToday(String today)
    {
        sharedPreferencesChapter = getActivity().getApplicationContext().getSharedPreferences(TODAY_CHAPTER,Context.MODE_PRIVATE);
        int chapter_no = sharedPreferencesChapter.getInt(today,1);
        chapterName = getResources().getStringArray(R.array.chapters);
        return chapterName[chapter_no];

    }

    public String getVerse(String chapter_array_name)
    {
        id = getResources().getIdentifier(chapter_array_name, "array", getActivity().getApplicationContext().getPackageName());
        String[] chapter = getResources().getStringArray(id);
        int random_verse = GetRandom(1, chapter.length - 1);
        return chapter[random_verse];
    }
}
