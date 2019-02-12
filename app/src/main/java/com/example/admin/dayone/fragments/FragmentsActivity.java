package com.example.admin.dayone.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.admin.dayone.R;

public class FragmentsActivity extends AppCompatActivity implements HeadlinesFragment.OnHeadlineSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);
    }

    //step B
    @Override
    public void onArticleSelected(int position, String item) {
        NewsFragment articleFrag = (NewsFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragmentnews);

        if (articleFrag != null) {
            // If article frag is available, we're in two-pane layout...

            // Call a method in the ArticleFragment to update its content
            articleFrag.updateArticleView(item);
        } else {


            NewsFragment newFragment = new NewsFragment();
            Bundle args = new Bundle();
            args.putString(NewsFragment.ARG_POSITION, item);
            newFragment.setArguments(args);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.headlinesfragment, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
    }
}
