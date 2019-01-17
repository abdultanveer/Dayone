package com.example.admin.dayone;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    public static String TAG = MainActivity.class.getSimpleName();

    TextToSpeech textToSpeech;
    String sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textToSpeech = new TextToSpeech(MainActivity.this, this);
        Log.i(TAG, "onCreate");
        /*ProgressBar progressBar = findViewById(R.id.progressBar);
        DownloadTask downloadTask = new DownloadTask(progressBar);
        downloadTask.execute(Integer.valueOf(100));*/
        Uri tableName = Uri.parse("content://sms/inbox");

        //Step1: connect to the content provider
        ContentResolver contentResolver = getContentResolver();
        //step 2: query the content provider
        Cursor cursor = contentResolver.query(tableName, null, null, null, null);
        cursor.moveToFirst();
        int bodyColumnIndex = cursor.getColumnIndexOrThrow("body");
         sms = cursor.getString(bodyColumnIndex);
        //step3: get the data from cursor and show in the textview
        TextView smsTextView = findViewById(R.id.textViewSms);
        smsTextView.setText(sms);

        smsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech.speak(sms,TextToSpeech.QUEUE_FLUSH,null);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        int c = add(10, 20);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG, "onResume");

    }


    private int add(int a, int b) {
        return a + b;
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

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = textToSpeech.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {

            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }

}

