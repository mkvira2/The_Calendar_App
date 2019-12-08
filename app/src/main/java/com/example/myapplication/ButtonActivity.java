package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class ButtonActivity extends AppCompatActivity {
    Button happy;
    Button sensitive;
    Button sad;
    Button tenH;
    Button sevenH;
    Button fiveH;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String mood;
    String sleep;
    String selectedDate;
    String keyMood;
    String keySleep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        happy = findViewById(R.id.happy);
        sensitive = findViewById(R.id.sensitive);
        sad = findViewById(R.id.sad);

        tenH = findViewById(R.id.tenHours);
        sevenH = findViewById(R.id.sevenHours);
        fiveH = findViewById(R.id.fivehours);

        pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        mood = "mood";
        sleep = "sleep";
        editor = pref.edit();
        editor.apply();
        selectedDate = "date";
        getIntent().putExtra(selectedDate, CalendarActivity.class);
        getIntent().getStringExtra(selectedDate);
        keyMood = "TheDate+mood";
        keySleep = "TheDate+sleep";
        getIntent().putExtra(keyMood, CalendarActivity.class);
        getIntent().getStringExtra(keyMood);
        getIntent().getStringExtra(keySleep);

    }
    public void happyQ(View view) {
        Toast.makeText(this, "YAY! :)", Toast.LENGTH_LONG).show();
        //if Happy is clicked, save "Happy" to the database and set the text in the Alert dialog
        // to "Happy" when the button "view my mood button" is clicked on that specific day.
        String hap = happy.getText().toString();
        mood = hap;
        editor.putString(keyMood, mood);
        editor.commit();
    }

    public void sensQ(View view) {
        AlertDialog.Builder quote = new AlertDialog.Builder(this);
        quote.setMessage("Maybe try writing about it")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNeutralButton("Notes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ButtonActivity.this, NotesActivity.class);
                        startActivity(intent);
                    }
                })
                .create();
        quote.show();
        String sens = sensitive.getText().toString();
        mood = sens;
        editor.putString(keyMood, mood);
        editor.commit();
    }

    public void sadQ(View view) {
        AlertDialog.Builder quote = new AlertDialog.Builder(this);
        quote.setMessage("Its a good idea to write how you're feeling or talking to a friend.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNeutralButton("Notes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ButtonActivity.this, NotesActivity.class);
                        startActivity(intent);
                    }
                })
                .create();
        quote.show();
        String sadd = sad.getText().toString();
        mood = sadd;
        editor.putString(keyMood, mood);
        editor.commit();
    }

    public void ten(View view) {
        String ten = tenH.getText().toString();
        sleep = ten;
        editor.putString(keySleep, sleep);
        editor.commit();
        Toast.makeText(this, "You got a little too much sleep but that's normal!", Toast.LENGTH_LONG).show();
    }

    public void seven(View view) {
        String seven = sevenH.getText().toString();
        sleep = seven;
        editor.putString(keySleep, sleep);
        editor.commit();
        Toast.makeText(this, "You got the perfect amount of sleep!", Toast.LENGTH_LONG).show();
    }

    public void five(View view) {
        String five = fiveH.getText().toString();
        sleep = five;
        editor.putString(keySleep, sleep);
        editor.commit();
        Toast.makeText(this, "Sleep some more next time!", Toast.LENGTH_LONG).show();
    }

}
