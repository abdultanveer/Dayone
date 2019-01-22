package com.example.admin.dayone.data;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.admin.dayone.R;

public class ContentProvidersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_providers);

        Uri uriSms = Uri.parse("content://sms/inbox");

        Cursor cursor =
                getContentResolver().query(uriSms,null,null,null,null);

        SimpleCursorAdapter cursorAdapter =
                new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,
                        cursor,new String[]{"body"},new int[]{android.R.id.text1,0});
        ListView listView =  findViewById(R.id.listview);
        listView.setAdapter(cursorAdapter);

    }
}
