package com.example.shiza.dailyquranverses;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    DailyQuranMethods dailyQuranMethods = new DailyQuranMethods();
    private final String[] translateLanguage={"English","Urdu","Hindi"};
    String language = "english";


    ArrayAdapter<String> adapter;
    public completeQuran() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_complete_quran, container, false);
        Spinner spinner = (Spinner)view.findViewById(R.id.selectLanguage);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(),   android.R.layout.simple_spinner_item, translateLanguage );
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
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
        spinner = (Spinner)view.findViewById(R.id.selectChapter);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),R.array.chapters_name_arabic,android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        return view;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        position = 0;
        int quran_id;
        String chapter_verse="";
        position++;
        String[] chapter = dailyQuranMethods.getChapter(position,"english",getActivity().getBaseContext());

        completeQuranListView = (ListView) getView().findViewById(R.id.complete_quran_list_view);
        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,chapter);
        completeQuranListView.setAdapter(adapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {
        Toast.makeText(getActivity().getApplicationContext(),"Please enter your choice",Toast.LENGTH_LONG).show();
    }
}
