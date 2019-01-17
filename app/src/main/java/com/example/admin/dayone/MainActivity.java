package com.example.admin.dayone;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate");
        /*ProgressBar progressBar = findViewById(R.id.progressBar);
        DownloadTask downloadTask = new DownloadTask(progressBar);
        downloadTask.execute(Integer.valueOf(100));*/
        Uri tableName = Uri.parse("content://sms/inbox");

        //Step1: connect to the content provider
        ContentResolver contentResolver = getContentResolver();
        //step 2: query the content provider
        Cursor cursor = contentResolver.query(tableName,null,null,null,null);
        cursor.moveToFirst();
        int bodyColumnIndex = cursor.getColumnIndexOrThrow("body");
        String sms = cursor.getString(bodyColumnIndex);
        //step3: get the data from cursor and show in the textview
        TextView smsTextView = findViewById(R.id.textViewSms);
        smsTextView.setText(sms);


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
       int c = add(10,20);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG, "onResume");

    }


    private int add(int a, int b){
        return a+b;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
