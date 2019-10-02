package com.example.storq.moodtracker1;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spin1, spin2;
    String happy;
    String anger;
    String anxiety;
    private String[] mood = new String[3];
    private static final String[] rating = {"1", "2", "3", "4", "5"};
    ListView mainLv;
    int moodVal;
    int ratingVal;
    MyAdapter adapter;
    ArrayList<DayMood> arr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        spin1 = (Spinner) findViewById(R.id.spin1);
        spin2 = (Spinner) findViewById(R.id.spin2);
        mainLv = (ListView) findViewById(R.id.mainLv);

        happy = getResources().getString(R.string.happy);
        anger = getResources().getString(R.string.anger);
        anxiety = getResources().getString(R.string.anxiety);
        mood[0] = happy;
        mood[1] = anger;
        mood[2] = anxiety;

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(SearchActivity.this,
                android.R.layout.simple_spinner_item, mood);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adapter1);
        spin1.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(SearchActivity.this,
                android.R.layout.simple_spinner_item, rating);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(adapter2);
        spin2.setOnItemSelectedListener(this);

        moodVal = 0;
        ratingVal = 0;

        adapter = new MyAdapter(this);
        mainLv.setAdapter(adapter);


    }

    public class RowDataViewHolder {
        public TextView date;
        public ImageView happyIv;
        public ImageView angerIv;
        public ImageView anxietyIv;
    }

    class MyAdapter extends ArrayAdapter {
        LayoutInflater lnf;

        public MyAdapter(Activity context) {
            super(context, R.layout.item, arr);
            lnf = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return arr.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return arr.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            RowDataViewHolder viewHolder;
            if (convertView == null) {
                convertView = lnf.inflate(R.layout.item, parent, false);
                viewHolder = new RowDataViewHolder();
                viewHolder.date = (TextView) convertView.findViewById(R.id.date);
                viewHolder.happyIv = (ImageView) convertView.findViewById(R.id.happyIv);
                viewHolder.angerIv = (ImageView) convertView.findViewById(R.id.angerIv);
                viewHolder.anxietyIv = (ImageView) convertView.findViewById(R.id.anxietyIv);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (RowDataViewHolder) convertView.getTag();
            }

            String[] str = arr.get(position).day.split(" ");
            viewHolder.date.setText(str[0]);

            if(arr.get(position).happy == 1){
                viewHolder.happyIv.setImageResource(R.drawable.very_sad);
            }else if(arr.get(position).happy == 2){
                viewHolder.happyIv.setImageResource(R.drawable.sad);
            }else if(arr.get(position).happy == 3){
                viewHolder.happyIv.setImageResource(R.drawable.soso);
            }else if(arr.get(position).happy == 4){
                viewHolder.happyIv.setImageResource(R.drawable.happy);
            }else if(arr.get(position).happy == 5){
                viewHolder.happyIv.setImageResource(R.drawable.very_happy);
            }

            if(arr.get(position).anger == 1){
                viewHolder.angerIv.setImageResource(R.drawable.very_sad);
            }else if(arr.get(position).anger == 2){
                viewHolder.angerIv.setImageResource(R.drawable.sad);
            }else if(arr.get(position).anger == 3){
                viewHolder.angerIv.setImageResource(R.drawable.soso);
            }else if(arr.get(position).anger == 4){
                viewHolder.angerIv.setImageResource(R.drawable.happy);
            }else if(arr.get(position).anger == 5){
                viewHolder.angerIv.setImageResource(R.drawable.very_happy);
            }

            if(arr.get(position).anxiety == 1){
                viewHolder.anxietyIv.setImageResource(R.drawable.very_sad);
            }else if(arr.get(position).anxiety == 2){
                viewHolder.anxietyIv.setImageResource(R.drawable.sad);
            }else if(arr.get(position).anxiety == 3){
                viewHolder.anxietyIv.setImageResource(R.drawable.soso);
            }else if(arr.get(position).anxiety == 4){
                viewHolder.anxietyIv.setImageResource(R.drawable.happy);
            }else if(arr.get(position).anxiety == 5){
                viewHolder.anxietyIv.setImageResource(R.drawable.very_happy);
            }

            return convertView;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spin1:
                switch (position) {
                    case 0:
                        moodVal = 1;
                        break;
                    case 1:
                        moodVal = 2;
                        break;
                    case 2:
                        moodVal = 3;
                        break;
                }
                break;
            case R.id.spin2:
                switch (position) {
                    case 0:
                        ratingVal = 1;
                        break;
                    case 1:
                        ratingVal = 2;
                        break;
                    case 2:
                        ratingVal = 3;
                        break;
                    case 3:
                        ratingVal = 4;
                        break;
                    case 4:
                        ratingVal = 5;
                        break;
                }
                break;
        }
        //Log.d("heu", "rating val: " + ratingVal);
        if(moodVal >= 1 && moodVal <= 3 && ratingVal >=1 && ratingVal <=5) {
            searchDb();
        }
    }
    private void searchDb(){
        arr.clear();

        for (int i = 0; i < Storage.staticDm.size(); i++) {
            if(moodVal == 1){
                if(Storage.staticDm.get(i).happy == ratingVal){
                    arr.add(Storage.staticDm.get(i));
                }
            }else if(moodVal == 2){
                if(Storage.staticDm.get(i).anger == ratingVal){
                    arr.add(Storage.staticDm.get(i));
                }
            }else if(moodVal == 3){
                if(Storage.staticDm.get(i).anxiety == ratingVal){
                    arr.add(Storage.staticDm.get(i));
                }
            }
        }

        adapter.notifyDataSetChanged();


    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
