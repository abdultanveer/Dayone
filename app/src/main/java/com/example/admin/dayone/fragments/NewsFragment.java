package com.example.admin.dayone.fragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.dayone.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {


    public static final String ARG_POSITION = "position";
    TextView textView;
    String news;
    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    //step C
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_news, container, false);
        textView = view.findViewById(R.id.newsTextview);

        Bundle bundle = getArguments();
        if(bundle != null) {
            news = bundle.getString(ARG_POSITION);
            textView.setText(news);
        }
        return  view;
    }

    //this was created to handle for tablets
    public void updateArticleView(String  item) {
        textView.setText(item);
    }
}
