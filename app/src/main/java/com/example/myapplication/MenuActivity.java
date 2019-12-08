package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void launchNotes(View view) {
        Intent intent = new Intent(MenuActivity.this, NotesActivity.class);
        startActivity(intent);
    }

    public void launchCalendar(View view) {
        Intent intent = new Intent(MenuActivity.this, CalendarActivity.class);
        startActivity(intent);
    }

    public void launchResources(View view) {
        Intent intent = new Intent(MenuActivity.this, ResourcesActivity.class);
        startActivity(intent);
    }

    public void launchQuotes(View view) {
        Intent intent = new Intent(MenuActivity.this, QuotesActivity.class);
        startActivity(intent);
    }
}
