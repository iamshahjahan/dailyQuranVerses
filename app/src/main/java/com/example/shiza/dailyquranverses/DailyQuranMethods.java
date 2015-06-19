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

        String chapter_array_name = "chapter_" + chapter_no;

        String verse = sharedPreferencesVerse.getString(DateToday(),null);

        if ( verse == null )
        {
            editor_verse.putString(DateToday(), getVerse(context,chapter_array_name));
            editor_verse.apply();
        }

        int chapter_number = sharedPreferencesChapter.getInt(DateToday(),0);

        if ( chapter_number == 0 )
        {

            editor_chapter.putInt(DateToday(),chapter_no);
            editor_chapter.apply();
        }


    }
    public String getVerseToday(String today,Context context)
    {
        sharedPreferencesVerse = context.getSharedPreferences(TODAY_VERSE, Context.MODE_PRIVATE);
        return sharedPreferencesVerse.getString(today,"7. The Way of those on whom You have bestowed Your Grace, not (the way) of those who earned Your Anger (such as the Jews), nor of those who went astray (such as the Christians).");
    }

    public String getVerse(Context context,String chapter_array_name)
    {
        int id = context.getResources().getIdentifier(chapter_array_name, "array", context.getPackageName());
        String[] chapter = context.getResources().getStringArray(id);
        int random_verse = GetRandom(1, chapter.length - 1);
        return chapter[random_verse];
    }


    public String[] getChapterTodayContent(String today,Context context)
    {
        sharedPreferencesChapter = context.getSharedPreferences(TODAY_CHAPTER, Context.MODE_PRIVATE);
        int chapter_no = sharedPreferencesChapter.getInt(today, 2);
        String chapter_array_name = "chapter_" + chapter_no;
        int id = context.getResources().getIdentifier(chapter_array_name, "array", context.getApplicationContext().getPackageName());
        return context.getResources().getStringArray(id);
    }

    public String getChapterTodayName(String today,Context context)
    {
        sharedPreferencesChapter = context.getApplicationContext().getSharedPreferences(TODAY_CHAPTER,Context.MODE_PRIVATE);
        int chapter_no = sharedPreferencesChapter.getInt(today, 0);
        String[] chapterName = context.getResources().getStringArray(R.array.chapters);
        return chapterName[chapter_no - 1];
    }



    public String DateToday()
   {
       Calendar c = Calendar.getInstance();

       SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
       return df.format(c.getTime());
   }


    //    Get quran verses in android
    public String[] getQuranVerses(Context context) {
        String[] whole_quran = new String[7000];
        String[] current_chapter;
        String chapterSize = "";
        String[] chapter_names;
        String chapter_array_name;
        int total_verse = 0, verse_in_current_chapter, chapter_number;

        chapter_names = context.getResources().getStringArray(R.array.chapters);

        for (chapter_number = 1; chapter_number < 114; chapter_number++) {
//            Grab each chapter containing verse from Quran
            chapter_array_name = "chapter_" + chapter_number;
            int id = context.getResources().getIdentifier(chapter_array_name, "array", context.getPackageName());
            current_chapter = context.getResources().getStringArray(id);

            for (verse_in_current_chapter = 1; verse_in_current_chapter < current_chapter.length - 1; verse_in_current_chapter++) {
                whole_quran[total_verse] = current_chapter[verse_in_current_chapter] + "," + chapter_names[chapter_number - 1];
                total_verse++;
            }
            chapterSize += chapter_number + ":" + chapter_names[chapter_number - 1] + ":" + current_chapter.length + "\n";
        }
        return whole_quran;

    }

    //    public void search()
//    {
//        //      Use search view on the top of your app
//
//        search = (SearchView) findViewById(R.id.mySearchView);
//        search.setQueryHint("Search Qur'an");
//
////      create a suggestion adapter for dropdown
//        final String[] from = new String[] {"cityName"};
//        final int[] to = new int[] {android.R.id.text1};
//        mAdapter = new SimpleCursorAdapter(this,
//                android.R.layout.simple_list_item_2,
//                null,
//                from,
//                to,
//                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
//
//        search.setSuggestionsAdapter(mAdapter);
//
//        search.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
//            @Override
//            public boolean onSuggestionClick(int position) {
//                // Your code here
//
//
//                Cursor theCursor = (Cursor) mAdapter.getCursor();
//                String selectedItem = theCursor.getString(position);
////                Toast.makeText(getBaseContext(), " on suggestion click position and item is" + position + selectedItem, Toast.LENGTH_LONG).show();
//
//
//                startActivity(new Intent(getBaseContext(), MainActivity.class));
//
//                return true;
//            }
//
//            @Override
//            public boolean onSuggestionSelect(int position) {
//                // Your code here
////                Toast.makeText(getBaseContext(), " on suggestion select position is" + position, Toast.LENGTH_LONG).show();
//
//                startActivity(new Intent(getBaseContext(), MainActivity.class));
//
//                return true;
//            }
//        });
//
//        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                startActivity(new Intent(getBaseContext(), MainActivity.class));
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                //
//                dailyQuranMethods.populateAdapter(newText,getApplicationContext());
//
//                return false;
//            }
//
//
//        });
//
//
//
//    }


    public void populateAdapter(String query, Context context, SimpleCursorAdapter mAdapter) {
        String[] SUGGESTIONS = getQuranVerses(context);
        final MatrixCursor c = new MatrixCursor(new String[]{BaseColumns._ID, "cityName"});
        int j = 0;
        int k = 0;
        if (query.length() > 3) {
            k = GetRandom(0, 60);
//            Toast.makeText(getApplicationContext(),"K is " + k,Toast.LENGTH_LONG).show();
            for (int i = 0; i < 6144; i++) {

                if (SUGGESTIONS[i].toLowerCase().contains(query.toLowerCase()) && SUGGESTIONS[i].length() > 0) {
                    c.addRow(new Object[]{i, SUGGESTIONS[i]});
                    j++;

                    if (j > 100) {
                        break;
                    }
                }
            }
            if (j == 0) {
                c.addRow(new Object[]{0, "No results found."});
            }
        } else {
            c.addRow(new Object[]{0, "Please enter at least 3 characters."});
            c.addRow(new Object[]{1, "Please be patient, we have to find in more than 6,000 verses"});
        }


        mAdapter.changeCursor(c);
    }

}
