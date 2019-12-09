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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {

    CalendarView calendarView;
    TextView myDate;
    Button pastMood;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String selectedDate;
    String keyMood;
    String keySleep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        myDate = (TextView) findViewById(R.id.myDate);
        pastMood = findViewById(R.id.pastMood);
        final Button rateMyDay = findViewById(R.id.rate);
        selectedDate = (Calendar.getInstance().get(Calendar.MONTH) + 1) + "/" + Calendar.getInstance()
                .get(Calendar.DAY_OF_MONTH) + "/" + Calendar.getInstance().get(Calendar.YEAR);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = (month + 1) + "/" + dayOfMonth + "/" + year;
                myDate.setText(selectedDate);

                if (isThisDateBefore(selectedDate, "MM/dd/yyyy")) {
                    rateMyDay.setVisibility(View.GONE);
                    pastMood.setVisibility(View.VISIBLE);
                } else {
                    rateMyDay.setVisibility(View.VISIBLE);
                    pastMood.setVisibility(View.GONE);
                }
            }
        });
        keyMood = selectedDate + "mood";
        keySleep = selectedDate + "sleep";

        rateMyDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, ButtonActivity.class);
                intent.putExtra("mood", selectedDate + "mood");
                intent.putExtra("sleep", selectedDate + "sleep");
                startActivity(intent);
            }
        });

        pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        editor = pref.edit();
        editor.apply();
    }

    public boolean isThisDateBefore(String dateToValidate, String dateFormat) {

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            // if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);

            Calendar current = Calendar.getInstance();

            if (date.before(current.getTime())) {
                return true;
            }
            if (date.after(current.getTime())) {
                return false;
            }

        } catch (ParseException e) {

            e.printStackTrace();
            return false;
        }
        return false;
    }

    public void launchMenu(View view) {
        Intent intent = new Intent(CalendarActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    public void viewMood(View view) {
        //open an Alert Dialog with the mood and amount of sleep got on that day.
        AlertDialog.Builder quote = new AlertDialog.Builder(this);
        quote.setMessage("Today your Mood was " + pref.getString(selectedDate + "mood", "mood")
                + " and you got " + pref.getString(selectedDate + "sleep", "sleep") + " of sleep.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        quote.show();
    }
}
