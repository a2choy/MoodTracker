package com.example.storq.moodtracker1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DayMood implements Comparable<DayMood>{
    String day, note;
    int _id, happy, anger, anxiety;

    public DayMood(int _id, String day, int happy, int anger, int anxiety, String note) {
        this._id = _id;
        this.day = day;
        this.happy = happy;
        this.anger = anger;
        this.anxiety = anxiety;
        this.note = note;
    }


    @Override
    public int compareTo(DayMood o) {
        Date date1 = null;
        Date date2 = null;
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try {
            date1 = formatter.parse(day);
            date2 = formatter.parse(o.day);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date2.compareTo(date1);
    }
}
