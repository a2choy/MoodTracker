package com.example.storq.moodtracker1;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StatActivity extends AppCompatActivity {

    ProgressBar progressBar1;
    ProgressBar progressBar2;
    ProgressBar progressBar3;
    int day, week, month;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);

        progressBar1 = (ProgressBar)findViewById(R.id.progressBar1);
        progressBar2 = (ProgressBar)findViewById(R.id.progressBar2);
        progressBar3 = (ProgressBar)findViewById(R.id.progressBar3);

        init();



    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0) {
                if (day > 0) {
                    progressBar1.incrementProgressBy(1);
                    day--;
                    handler.sendEmptyMessageDelayed(0, 100);
                }else{
                    removeMessages(0);
                }
            }
            if(msg.what == 1) {
                if (week > 0) {
                    progressBar2.incrementProgressBy(1);
                    week--;
                    handler.sendEmptyMessageDelayed(1, 100);
                }else{
                    removeMessages(1);
                }
            }
            if(msg.what == 2) {
                if (month > 0) {
                    progressBar3.incrementProgressBy(1);
                    month--;
                    handler.sendEmptyMessageDelayed(2, 10);
                }else{
                    removeMessages(2);
                }
            }
        }
    };


    private void init(){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String dateStr = formatter.format(date);

        day = 0;
        week = 0;
        month = 0;
        if(Storage.staticDm.size() != 0) {
            String[] str = Storage.staticDm.get(0).day.split(" ");

            //Log.d("heu","str[0]: " + str[0] + " datestr: " + dateStr);

            if (Storage.staticDm.size() > 0) {
                if (dateStr.equals(str[0])) {
                    day = Storage.staticDm.get(0).happy;
                }

                int num2 = (Storage.staticDm.size() >= 7) ? 7 : Storage.staticDm.size();

                for (int i = 0; i < num2; i++) {
                    week += Storage.staticDm.get(0 + i).happy;
                }

                int num3 = (Storage.staticDm.size() >= 31) ? 31 : Storage.staticDm.size();

                for (int i = 0; i < num3; i++) {
                    month += Storage.staticDm.get(0 + i).happy;
                }

                handler.sendEmptyMessage(0);
                handler.sendEmptyMessage(1);
                handler.sendEmptyMessage(2);

            }
        }
    }
}
