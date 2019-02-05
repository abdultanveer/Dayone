package com.example.admin.dayone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void clickMe(View view) {
        EditText nameEditText = findViewById(R.id.editText);
        String name = nameEditText.getText().toString();
        TextView resTextView =  findViewById(R.id.textViewResult);
        resTextView.setText(name);
    }
}
