package com.example.admin.dayone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.admin.dayone.data.Word;
import com.example.admin.dayone.data.WordDao;

import java.util.List;

public class RoomActivity extends AppCompatActivity {

    WordRoomDatabase db;
    private WordDao mWordDao;
    private List<Word> allWordsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        db = WordRoomDatabase.getDatabase(this);
        mWordDao = db.wordDao();

        ListView listView = findViewById(R.id.listview);
    }

    public void clickHandler(View view) {
        Word word = new Word("abdul");
        insert(word);
    }

    public void insert (Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
