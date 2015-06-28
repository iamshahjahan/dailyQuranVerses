package com.example.shiza.dailyquranverses;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodayChapter extends Fragment {
    String chapter_verse = "";
    TextView textView;
    Spinner spinner;

    ListView todayChapterListView;
    ArrayAdapter<String> adapter;
    DailyQuranMethods dailyQuranMethods = new DailyQuranMethods();
    private final String[] translateLanguage={"Translate To","English","Urdu","Hindi"};

    public TodayChapter() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_today_chapter, container, false);

        todayChapterListView = (ListView)view.findViewById(R.id.today_chapter_list_view);
        textView = (TextView)view.findViewById(R.id.chapterName);
        spinner = (Spinner)view.findViewById(R.id.selectLanguage);
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
                        SetText(textView,todayChapterListView);
                        break;
                    case 1:
                        dailyQuranMethods.setTranslationLanguage(getActivity().getBaseContext(),"english");
                        SetText(textView,todayChapterListView);

                        break;
                    case 2:
                        dailyQuranMethods.setTranslationLanguage(getActivity().getBaseContext(),"urdu");
                        SetText(textView,todayChapterListView);

                        break;
                    default:
                        dailyQuranMethods.setTranslationLanguage(getActivity().getBaseContext(),"hindi");
                        SetText(textView,todayChapterListView);

                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

    public void SetText(TextView textView,ListView todayChapterListView)
    {
        String[] chapter = dailyQuranMethods.getChapter(dailyQuranMethods.getChapterNoToday(getActivity().getBaseContext()), dailyQuranMethods.getTranslationLanguage(getActivity().getBaseContext()), getActivity());

        String chapterName =  dailyQuranMethods.getChapterNoToday(getActivity().getBaseContext()) + ". " + getActivity().getResources().getStringArray(R.array.chapters_name_arabic_english)[dailyQuranMethods.getChapterNoToday(getActivity().getBaseContext()) - 1 ] ;

        textView.setText(chapterName);
        adapter =  new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,chapter);

        todayChapterListView.setAdapter(adapter);

    }
}
