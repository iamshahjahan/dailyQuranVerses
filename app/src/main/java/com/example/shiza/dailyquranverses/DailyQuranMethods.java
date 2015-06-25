package com.example.shiza.dailyquranverses;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.MatrixCursor;
import android.preference.PreferenceManager;
import android.provider.BaseColumns;
import android.widget.SimpleCursorAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import com.example.shiza.dailyquranverses.TodayChapter;

import android.preference.Preference;

/**
 * Created by Shiza on 19-06-2015.
 */
public class DailyQuranMethods {
    //    generate a random number.
    SharedPreferences sharedPreferencesChapter;
    SharedPreferences sharedPreferencesVerse;
    private static String TODAY_CHAPTER = "TODAY_CHAPTER";
    private static String TODAY_VERSE = "TODAY_VERSE";


    public int GetRandom(int min, int max) {
        Random ran = new Random();
        return ran.nextInt((max - min) + 1) + min;
    }


    public void setChapterVerseOfToday(Context context)
    {

        sharedPreferencesChapter = context.getSharedPreferences(TODAY_CHAPTER, Context.MODE_PRIVATE);
        sharedPreferencesVerse = context.getApplicationContext().getSharedPreferences(TODAY_VERSE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor_chapter = sharedPreferencesChapter.edit();
        SharedPreferences.Editor editor_verse = sharedPreferencesVerse.edit();


        int chapter_no = GetRandom(1, 114);

        String chapter_array_name = "chapter_arabic_" + chapter_no;

        String verse = sharedPreferencesVerse.getString(DateToday(), null);

        if (verse == null) {
            editor_verse.putInt(DateToday(), getVerseNumber(context, chapter_array_name));
            editor_verse.apply();
        }

        int chapter_number = sharedPreferencesChapter.getInt(DateToday(), 0);

        if (chapter_number == 0)
        {

            editor_chapter.putInt(DateToday(), chapter_no);
            editor_chapter.apply();
        }


    }

    public int getChapterNoToday(Context context)
    {
        sharedPreferencesChapter = context.getSharedPreferences(TODAY_CHAPTER,Context.MODE_PRIVATE);
        return sharedPreferencesChapter.getInt(DateToday(),1);
    }

    public int getVerseNoToday(Context context)
    {
        sharedPreferencesVerse = context.getSharedPreferences(TODAY_VERSE,Context.MODE_PRIVATE);
        return sharedPreferencesVerse.getInt(DateToday(),1);
    }


    public String[] getChapter(int chapter_no,String trans,Context context)
    {
        String chapter_array_name_arabic = "chapter_arabic_" + chapter_no;
        String chapter_array_name_translate = "chapter_" + trans + "_" + chapter_no;
        int arabic_id = context.getResources().getIdentifier(chapter_array_name_arabic, "array", context.getApplicationContext().getPackageName());
        int translate_id = context.getResources().getIdentifier(chapter_array_name_translate, "array", context.getApplicationContext().getPackageName());

        String[] arabic_chapter = context.getResources().getStringArray(arabic_id);
        String[] translate_chapter = context.getResources().getStringArray(translate_id);

        String[] composite_chapter = new String[arabic_chapter.length];

        for ( int verse = 0 ; verse < arabic_chapter.length ; verse++ )
        {
            composite_chapter[verse] = arabic_chapter[verse] + "\n" + translate_chapter[verse];
        }
        return composite_chapter;
    }

    public int getVerseNumber(Context context, String chapter_array_name) {
        int id = context.getResources().getIdentifier(chapter_array_name, "array", context.getPackageName());
        String[] chapter = context.getResources().getStringArray(id);
        return GetRandom(1, chapter.length - 1);

    }
    public String DateToday() {
        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
        return df.format(c.getTime());
    }

}


