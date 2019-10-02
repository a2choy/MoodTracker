package com.example.storq.moodtracker1;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    TextView dayTv, hourTv;
    ImageView verySadHappy, sadHappy, sosoHappy, happyHappy, veryHappyHappy;
    ImageView verySadAnger, sadAnger, sosoAnger, happyAnger, veryHappyAnger;
    ImageView verySadAnxiety, sadAnxiety, sosoAnxiety, happyAnxiety, veryHappyAnxiety;
    ImageView next;

    boolean edit;
    int happy, anger, anxiety, pos;
    String dateStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        dayTv = (TextView) findViewById(R.id.dayTv);
        hourTv = (TextView) findViewById(R.id.hourTv);
        verySadHappy = (ImageView) findViewById(R.id.verySadHappy);
        sadHappy = (ImageView) findViewById(R.id.sadHappy);
        sosoHappy = (ImageView) findViewById(R.id.sosoHappy);
        happyHappy = (ImageView) findViewById(R.id.happyHappy);
        veryHappyHappy = (ImageView) findViewById(R.id.veryHappyHappy);

        verySadAnger = (ImageView) findViewById(R.id.verySadAnger);
        sadAnger = (ImageView) findViewById(R.id.sadAnger);
        sosoAnger = (ImageView) findViewById(R.id.sosoAnger);
        happyAnger = (ImageView) findViewById(R.id.happyAnger);
        veryHappyAnger = (ImageView) findViewById(R.id.veryHappyAnger);

        verySadAnxiety = (ImageView) findViewById(R.id.verySadAnxiety);
        sadAnxiety = (ImageView) findViewById(R.id.sadAnxiety);
        sosoAnxiety = (ImageView) findViewById(R.id.sosoAnxiety);
        happyAnxiety = (ImageView) findViewById(R.id.happyAnxiety);
        veryHappyAnxiety = (ImageView) findViewById(R.id.veryHappyAnxiety);

        next = (ImageView) findViewById(R.id.next);

        verySadHappy.setOnClickListener(this);
        sadHappy.setOnClickListener(this);
        sosoHappy.setOnClickListener(this);
        happyHappy.setOnClickListener(this);
        veryHappyHappy.setOnClickListener(this);

        verySadAnger.setOnClickListener(this);
        sadAnger.setOnClickListener(this);
        sosoAnger.setOnClickListener(this);
        happyAnger.setOnClickListener(this);
        veryHappyAnger.setOnClickListener(this);

        verySadAnxiety.setOnClickListener(this);
        sadAnxiety.setOnClickListener(this);
        sosoAnxiety.setOnClickListener(this);
        happyAnxiety.setOnClickListener(this);
        veryHappyAnxiety.setOnClickListener(this);

        next.setOnClickListener(this);

        init();

    }


    private void init() {
        Intent intent = getIntent();
        dateStr = intent.getStringExtra("date");
        String[] ar;
        if(dateStr == null) {
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            Date date = new Date();
            String str = formatter.format(date);
            ar = str.split(" ");
        } else {
            ar = dateStr.split(" ");
        }
        dayTv.setText(ar[0]);
        hourTv.setText(ar[1]);

        happy = -1;
        anger = -1;
        anxiety = -1;


        edit = intent.getBooleanExtra("edit", false);
        pos = intent.getIntExtra("pos", -1);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.verySadHappy:
                verySadHappy.setImageResource(R.drawable.very_sad_chk);
                sadHappy.setImageResource(R.drawable.sad);
                sosoHappy.setImageResource(R.drawable.soso);
                happyHappy.setImageResource(R.drawable.happy);
                veryHappyHappy.setImageResource(R.drawable.very_happy);
                happy = 1;
                break;
            case R.id.sadHappy:
                verySadHappy.setImageResource(R.drawable.very_sad);
                sadHappy.setImageResource(R.drawable.sad_chk);
                sosoHappy.setImageResource(R.drawable.soso);
                happyHappy.setImageResource(R.drawable.happy);
                veryHappyHappy.setImageResource(R.drawable.very_happy);
                happy = 2;
                break;
            case R.id.sosoHappy:
                verySadHappy.setImageResource(R.drawable.very_sad);
                sadHappy.setImageResource(R.drawable.sad);
                sosoHappy.setImageResource(R.drawable.soso_chk);
                happyHappy.setImageResource(R.drawable.happy);
                veryHappyHappy.setImageResource(R.drawable.very_happy);
                happy = 3;
                break;
            case R.id.happyHappy:
                verySadHappy.setImageResource(R.drawable.very_sad);
                sadHappy.setImageResource(R.drawable.sad);
                sosoHappy.setImageResource(R.drawable.soso);
                happyHappy.setImageResource(R.drawable.happy_chk);
                veryHappyHappy.setImageResource(R.drawable.very_happy);
                happy = 4;
                break;
            case R.id.veryHappyHappy:
                verySadHappy.setImageResource(R.drawable.very_sad);
                sadHappy.setImageResource(R.drawable.sad);
                sosoHappy.setImageResource(R.drawable.soso);
                happyHappy.setImageResource(R.drawable.happy);
                veryHappyHappy.setImageResource(R.drawable.very_happy_chk);
                happy = 5;
                break;

            case R.id.verySadAnger:
                verySadAnger.setImageResource(R.drawable.very_sad_chk);
                sadAnger.setImageResource(R.drawable.sad);
                sosoAnger.setImageResource(R.drawable.soso);
                happyAnger.setImageResource(R.drawable.happy);
                veryHappyAnger.setImageResource(R.drawable.very_happy);
                anger = 1;
                break;
            case R.id.sadAnger:
                verySadAnger.setImageResource(R.drawable.very_sad);
                sadAnger.setImageResource(R.drawable.sad_chk);
                sosoAnger.setImageResource(R.drawable.soso);
                happyAnger.setImageResource(R.drawable.happy);
                veryHappyAnger.setImageResource(R.drawable.very_happy);
                anger = 2;
                break;
            case R.id.sosoAnger:
                verySadAnger.setImageResource(R.drawable.very_sad);
                sadAnger.setImageResource(R.drawable.sad);
                sosoAnger.setImageResource(R.drawable.soso_chk);
                happyAnger.setImageResource(R.drawable.happy);
                veryHappyAnger.setImageResource(R.drawable.very_happy);
                anger = 3;
                break;
            case R.id.happyAnger:
                verySadAnger.setImageResource(R.drawable.very_sad);
                sadAnger.setImageResource(R.drawable.sad);
                sosoAnger.setImageResource(R.drawable.soso);
                happyAnger.setImageResource(R.drawable.happy_chk);
                veryHappyAnger.setImageResource(R.drawable.very_happy);
                anger = 4;
                break;
            case R.id.veryHappyAnger:
                verySadAnger.setImageResource(R.drawable.very_sad);
                sadAnger.setImageResource(R.drawable.sad);
                sosoAnger.setImageResource(R.drawable.soso);
                happyAnger.setImageResource(R.drawable.happy);
                veryHappyAnger.setImageResource(R.drawable.very_happy_chk);
                anger = 5;
                break;

            case R.id.verySadAnxiety:
                verySadAnxiety.setImageResource(R.drawable.very_sad_chk);
                sadAnxiety.setImageResource(R.drawable.sad);
                sosoAnxiety.setImageResource(R.drawable.soso);
                happyAnxiety.setImageResource(R.drawable.happy);
                veryHappyAnxiety.setImageResource(R.drawable.very_happy);
                anxiety = 1;
                break;
            case R.id.sadAnxiety:
                verySadAnxiety.setImageResource(R.drawable.very_sad);
                sadAnxiety.setImageResource(R.drawable.sad_chk);
                sosoAnxiety.setImageResource(R.drawable.soso);
                happyAnxiety.setImageResource(R.drawable.happy);
                veryHappyAnxiety.setImageResource(R.drawable.very_happy);
                anxiety = 2;
                break;
            case R.id.sosoAnxiety:
                verySadAnxiety.setImageResource(R.drawable.very_sad);
                sadAnxiety.setImageResource(R.drawable.sad);
                sosoAnxiety.setImageResource(R.drawable.soso_chk);
                happyAnxiety.setImageResource(R.drawable.happy);
                veryHappyAnxiety.setImageResource(R.drawable.very_happy);
                anxiety = 3;
                break;
            case R.id.happyAnxiety:
                verySadAnxiety.setImageResource(R.drawable.very_sad);
                sadAnxiety.setImageResource(R.drawable.sad);
                sosoAnxiety.setImageResource(R.drawable.soso);
                happyAnxiety.setImageResource(R.drawable.happy_chk);
                veryHappyAnxiety.setImageResource(R.drawable.very_happy);
                anxiety = 4;
                break;
            case R.id.veryHappyAnxiety:
                verySadAnxiety.setImageResource(R.drawable.very_sad);
                sadAnxiety.setImageResource(R.drawable.sad);
                sosoAnxiety.setImageResource(R.drawable.soso);
                happyAnxiety.setImageResource(R.drawable.happy);
                veryHappyAnxiety.setImageResource(R.drawable.very_happy_chk);
                anxiety = 5;
                break;

            case R.id.next:
                if(anger >= 1 && anger <= 5 && anxiety >= 1 && anxiety  <= 5 && happy >= 1 && happy  <= 5) {
                    Intent intent = new Intent(this, com.example.storq.moodtracker1.Add2Activity.class);
                    intent.putExtra("happy", happy);
                    intent.putExtra("anger", anger);
                    intent.putExtra("anxiety", anxiety);
                    intent.putExtra("edit", edit);
                    intent.putExtra("pos", pos);
                    if(dateStr != null) {
                        intent.putExtra("date", dateStr);
                    }
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(this, R.string.toast_select, Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
