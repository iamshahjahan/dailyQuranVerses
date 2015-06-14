package com.example.shiza.dailyquranverses;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class completeQuran extends Fragment implements AdapterView.OnItemSelectedListener {
    Spinner spinner;


    public completeQuran() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_complete_quran, container, false);

        spinner = (Spinner)view.findViewById(R.id.selectChapter);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),R.array.chapters,android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        return view;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int quran_id;
        String chapter_verse="";
        TextView textView;
        position++;
        String chapter_array_name = "chapter_" + position;

        quran_id = getResources().getIdentifier(chapter_array_name, "array", getActivity().getApplicationContext().getPackageName());
        String[] chapter = getResources().getStringArray(quran_id);

        for ( int item = 0 ; item < chapter.length ; item++ )
        {
            chapter_verse += chapter[item] + "\n";
        }
        textView = (TextView)getView().findViewById(R.id.verse);
        textView.setText(chapter_verse);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {
        Toast.makeText(getActivity().getApplicationContext(),"Please enter your choice",Toast.LENGTH_LONG).show();
    }
}
