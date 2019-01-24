package com.example.admin.dayone.data;



import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    public Word(@NonNull String word) {this.mWord = word;}

    /**
     * this method returns a word -- javadoc
     * @return word
     */
    public String getWord(){return this.mWord;}

    /**
     * this will set a word
     * @param word
     */
    public void setWord(String word)
    {
        this.mWord = word;
    }


    @Override
    public String toString() {
        setWord("abdul");
        return mWord;

    }
}