package com.example.admin.dayone.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.Loader;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.admin.dayone.R;

public class ContentProvidersActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    int IDENTITY_CARD = 007;
    Bundle specialInstructions = null;
    SimpleCursorAdapter cursorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_providers);

        LoaderManager loaderManager = getLoaderManager();
        //loader manager has hired a guy and given him an id card and then some instructions
        // callbacks -- whom should he report to
        loaderManager.initLoader(IDENTITY_CARD,specialInstructions,this);


       /* Cursor cursor =
                getContentResolver().query(uriSms,
                        null,null,null,null);*/

         cursorAdapter =
                new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,
                        null,new String[]{"body"},new int[]{android.R.id.text1,0});
        ListView listView =  findViewById(R.id.listview);
        listView.setAdapter(cursorAdapter);

    }


    @Override
    public android.content.Loader<Cursor> onCreateLoader(int id, Bundle splInstructions) {
        //uri = location of the warehouse
        Uri uriSms = Uri.parse("content://sms/inbox");

        return new CursorLoader(this,uriSms,null,null,null,null);
    }

    @Override
    public void onLoadFinished(android.content.Loader<Cursor> loader, Cursor data) {
            //loader is reporting back that he has loaded the data
        cursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) {

    }
}
