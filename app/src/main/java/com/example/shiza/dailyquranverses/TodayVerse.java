package com.example.shiza.dailyquranverses;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
    DailyQuranMethods dailyQuranMethods = new DailyQuranMethods();
    private final String[] translateLanguage={"Translate To","English","Urdu","Hindi"};
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
        Spinner spinner = (Spinner)view.findViewById(R.id.selectLanguage);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(),   android.R.layout.simple_spinner_item, translateLanguage );
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                switch (position)
                {
                    case 0:
                       dailyQuranMethods.setTranslationLanguage(getActivity().getBaseContext(), "english");
                        SetText(textView);
                        break;
                    case 1:
                        dailyQuranMethods.setTranslationLanguage(getActivity().getBaseContext(),"english");
                        SetText(textView);
                        break;
                    case 2:
                        dailyQuranMethods.setTranslationLanguage(getActivity().getBaseContext(),"urdu");
                        SetText(textView);
                        break;
                    default:
                        dailyQuranMethods.setTranslationLanguage(getActivity().getBaseContext(),"hindi");
                        SetText(textView);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        setChapterVerse();

        return view;
    }

    public void SetText(TextView textView)
    {
        textView.setText(dailyQuranMethods.getChapter(dailyQuranMethods.getChapterNoToday(getActivity().getBaseContext()),  dailyQuranMethods.getTranslationLanguage(getActivity().getBaseContext()),getActivity().getBaseContext())[dailyQuranMethods.getVerseNoToday(getActivity().getBaseContext())] + "\nChapter: " + dailyQuranMethods.getChapterNoToday(getActivity().getBaseContext()) + ". " + getActivity().getResources().getStringArray(R.array.chapters_name_arabic_english)[dailyQuranMethods.getChapterNoToday(getActivity().getBaseContext()) - 1 ] ) ;

    }

}



