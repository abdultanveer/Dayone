package com.example.admin.dayone;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

public class DownloadTask extends AsyncTask<Integer,Integer,Boolean> {

    ProgressBar mProgressBar;
    public DownloadTask(ProgressBar progressBar) {
        mProgressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Boolean doInBackground(Integer... numSeconds) {
        try {
            int totalSecs = numSeconds[0].intValue();
            for (int i = 0; i<=totalSecs; i++){
                Thread.sleep(100);
                float percentage = ((float)i / (float)totalSecs) * 100;
                //show the percentage of file downloaded
                publishProgress( new Float( percentage).intValue());//Step A
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        //Step B
        mProgressBar.setProgress(values[0]);

    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        mProgressBar.setVisibility(View.INVISIBLE);
    }
}
