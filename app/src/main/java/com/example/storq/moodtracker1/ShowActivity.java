package com.example.storq.moodtracker1;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {

    ImageView happyIv;
    ImageView angerIv;
    ImageView anxietyIv;
    TextView noteTv;
    TextView date;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        happyIv = (ImageView)findViewById(R.id.happyIv);
        angerIv = (ImageView)findViewById(R.id.angerIv);
        anxietyIv = (ImageView)findViewById(R.id.anxietyIv);
        noteTv = (TextView) findViewById(R.id.noteTv);
        date = (TextView) findViewById(R.id.date);
        init();
    }
    private void init(){
        position = getIntent().getIntExtra("pos", 0);

        String[] str = Storage.staticDm.get(position).day.split(" ");
        date.setText(str[0]);


        if (Storage.staticDm.get(position).happy == 1) {
            happyIv.setImageResource(R.drawable.very_sad);
        } else if (Storage.staticDm.get(position).happy == 2) {
            happyIv.setImageResource(R.drawable.sad);
        } else if (Storage.staticDm.get(position).happy == 3) {
            happyIv.setImageResource(R.drawable.soso);
        } else if (Storage.staticDm.get(position).happy == 4) {
            happyIv.setImageResource(R.drawable.happy);
        } else if (Storage.staticDm.get(position).happy == 5) {
            happyIv.setImageResource(R.drawable.very_happy);
        }

        if (Storage.staticDm.get(position).anger == 1) {
            angerIv.setImageResource(R.drawable.very_sad);
        } else if (Storage.staticDm.get(position).anger == 2) {
            angerIv.setImageResource(R.drawable.sad);
        } else if (Storage.staticDm.get(position).anger == 3) {
            angerIv.setImageResource(R.drawable.soso);
        } else if (Storage.staticDm.get(position).anger == 4) {
            angerIv.setImageResource(R.drawable.happy);
        } else if (Storage.staticDm.get(position).anger == 5) {
            angerIv.setImageResource(R.drawable.very_happy);
        }

        if (Storage.staticDm.get(position).anxiety == 1) {
            anxietyIv.setImageResource(R.drawable.very_sad);
        } else if (Storage.staticDm.get(position).anxiety == 2) {
            anxietyIv.setImageResource(R.drawable.sad);
        } else if (Storage.staticDm.get(position).anxiety == 3) {
            anxietyIv.setImageResource(R.drawable.soso);
        } else if (Storage.staticDm.get(position).anxiety == 4) {
            anxietyIv.setImageResource(R.drawable.happy);
        } else if (Storage.staticDm.get(position).anxiety == 5) {
            anxietyIv.setImageResource(R.drawable.very_happy);
        }

        noteTv.setText(Storage.staticDm.get(position).note);
    }
}
