package com.example.shiza.dailyquranverses;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodayVerse extends Fragment
{
    TextView textView;
    String language="english";
    DailyQuranMethods dailyQuranMethods = new DailyQuranMethods();
    private final String[] translateLanguage={"English","Urdu","Hindi"};

    public TodayVerse() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_today_verse, container, false);
        Spinner spinner = (Spinner)view.findViewById(R.id.selectLanguage);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(),   android.R.layout.simple_spinner_item, translateLanguage );
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        language = "english";
                    case 1:
                        language = "urdu";
                    case 2:
                        language = "hindi";
                    default:
                        language = "english";

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        textView = (TextView) view.findViewById(R.id.verse);
//        setChapterVerse();

        textView.setText(dailyQuranMethods.getChapter(dailyQuranMethods.getChapterNoToday(getActivity().getBaseContext()),language,getActivity().getBaseContext())[dailyQuranMethods.getVerseNoToday(getActivity().getBaseContext())] + "\nChapter: " + getActivity().getResources().getStringArray(R.array.chapters_name_arabic)[dailyQuranMethods.getChapterNoToday(getActivity().getBaseContext()) - 1 ]);
        return view;
    }



}


