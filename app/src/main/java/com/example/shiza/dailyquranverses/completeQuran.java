package com.example.shiza.dailyquranverses;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class completeQuran extends Fragment implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    ListView completeQuranListView;
    Spinner spinnerLanguage;
    DailyQuranMethods dailyQuranMethods = new DailyQuranMethods();
    private String[] chapterList;
    private final String[] translateLanguage={"Translate To","English","Urdu","Hindi"};


    ArrayAdapter<String> adapter;
    public completeQuran()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_complete_quran, container, false);
        spinnerLanguage = (Spinner)view.findViewById(R.id.selectLanguage);
        spinner = (Spinner)view.findViewById(R.id.selectChapter);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(),   android.R.layout.simple_spinner_item, translateLanguage );
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinnerLanguage.setAdapter(spinnerArrayAdapter);
        spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        dailyQuranMethods.setTranslationLanguage(getActivity().getBaseContext(), "english");
                        SetText(spinner);
                        break;
                    case 1:
                        dailyQuranMethods.setTranslationLanguage(getActivity().getBaseContext(),"english");
                        SetText(spinner);

                        break;
                    case 2:
                        dailyQuranMethods.setTranslationLanguage(getActivity().getBaseContext(),"urdu");
                        SetText(spinner);

                        break;
                    default:
                        dailyQuranMethods.setTranslationLanguage(getActivity().getBaseContext(),"hindi");
                        SetText(spinner);
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }
    public void SetText(Spinner spinner)
    {
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),R.array.chapters_name_arabic_english,android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        position = 0;
        int quran_id;
        String chapter_verse="";
        position++;
        String[] chapter = dailyQuranMethods.getChapter(position,dailyQuranMethods.getTranslationLanguage(getActivity().getBaseContext()),getActivity().getBaseContext());

        completeQuranListView = (ListView) getView().findViewById(R.id.complete_quran_list_view);
        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,chapter);
        completeQuranListView.setAdapter(adapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {
    }
}
