package com.example.admin.dayone.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.dayone.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HeadlinesFragment extends Fragment implements AdapterView.OnItemClickListener {

ListView listView;
    OnHeadlineSelectedListener mCallback;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (OnHeadlineSelectedListener) getActivity();//wiring
    }

    // Container Activity must implement this interface
    public interface OnHeadlineSelectedListener {//switchboard
        public void onArticleSelected(int position,String item);//switch
    }
    public HeadlinesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_headlines, container, false);
        listView = view.findViewById(R.id.headlineslistview);
        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String itemClicked = (String) parent.getItemAtPosition(position);
        //Toast.makeText(getContext(), itemClicked, Toast.LENGTH_SHORT).show();
        //step A
        mCallback.onArticleSelected(position,itemClicked);//switching it on

    }
}
