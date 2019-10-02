package com.example.storq.moodtracker1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    GridView mainGv;
    ArrayList<String> arr = new ArrayList<>();
    TextView dateTv, forwardTv, backTv;
    String dateStr;
    MyAdapter2 adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mainGv = (GridView)findViewById(R.id.mainGv);
        dateTv = (TextView)findViewById(R.id.date);
        forwardTv = (TextView)findViewById(R.id.forwardTv);
        backTv = (TextView)findViewById(R.id.backTv);

        forwardTv.setOnClickListener(this);
        backTv.setOnClickListener(this);
        init();
    }

    private void init() {

        adapter = new MyAdapter2(this);
        mainGv.setAdapter(adapter);
        mainGv.setOnItemClickListener(this);

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        dateStr = formatter.format(date);
        String[] str = dateStr.split("/");
        dateStr = str[0]+ "/01/" + str[2];



        setCalendar();



    }

    @Override
    public void onResume() {

        super.onResume();
        setCalendar();
        adapter.notifyDataSetChanged();
    }

    private void setCalendar(){
        arr.clear();

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate = null;
        try {
            startDate = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        //Log.d("heu","dateStr: " + dateStr + "day of wk: " + dayOfWeek);
        int daysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 1; i < dayOfWeek; i++) {
            arr.add("");
        }
        String[] str = dateStr.split("/");
        for (int i = 1; i <= daysInMonth; i++) {
            if(i < 10) {
                arr.add("" + str[0] + "/0" + i + "/" + str[2]);
            }else {
                arr.add("" + str[0] + "/" + i + "/" + str[2]);
            }
        }
        int num = 35 - (dayOfWeek - 1 + daysInMonth);
        for(int i = 0; i < num; i++){
            arr.add("");
        }
        dateTv.setText(dateStr);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String[] str;
        boolean flag = false;
        for (int i = 0; i < Storage.staticDm.size(); i++) {
            str = Storage.staticDm.get(i).day.split(" ");
            if (str[0].equals(arr.get(position))) {
                Intent intent = new Intent(this, com.example.storq.moodtracker1.ShowActivity.class);
                intent.putExtra("pos", i);
                startActivity(intent);
                flag = true;
            }
        }
        if(!flag){
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            Date date = new Date();
            String dateStr = formatter.format(date);
            Intent intent = new Intent(this, com.example.storq.moodtracker1.AddActivity.class);
            dateStr = arr.get(position) + " " + dateStr;
            intent.putExtra("date", dateStr);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        String[] str = dateStr.split("/");
        int num = Integer.parseInt(str[0]);
        int num2 = Integer.parseInt(str[2]);
        switch (v.getId()){
            case R.id.backTv:
                if(num == 1){
                    dateStr = "12/01/" + (num2 - 1);
                }else{
                    if((num - 1) < 10) {
                        dateStr = "0" + (num - 1) + "/01/" + str[2];
                    }else{
                        dateStr = (num - 1) + "/01/" + str[2];
                    }
                }
                break;
            case R.id.forwardTv:
                if(num == 12){
                    dateStr = "01/01/" + (num2 + 1);
                }else{
                    if((num + 1) < 10) {
                        dateStr = "0" + (num + 1) + "/01/" + str[2];
                    }else{
                        dateStr = (num + 1) + "/01/" + str[2];
                    }
                }
                break;
        }

        setCalendar();
        adapter.notifyDataSetChanged();
    }

    public class RowDataViewHolder{
        public TextView dateTv;
        public TextView backgroundTv;
    }

    class MyAdapter2 extends ArrayAdapter {
        LayoutInflater lnf;

        public MyAdapter2(Activity context) {
            super(context, R.layout.item2, arr);
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
                convertView = lnf.inflate(R.layout.item2, parent, false);
                viewHolder = new RowDataViewHolder();
                viewHolder.dateTv = (TextView) convertView.findViewById(R.id.dateTv);
                viewHolder.backgroundTv = (TextView) convertView.findViewById(R.id.backgroundTv);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (RowDataViewHolder) convertView.getTag();
            }

            String[] str;
            int happy = -1;
            int anger = -1;
            int anxiety = -1;

            for (int i = 0; i < Storage.staticDm.size(); i++) {
                str = Storage.staticDm.get(i).day.split(" ");
                //Log.d("heu", "str[0]: " + str[0]+ " arr: " + arr.get(position)+"<end>");
                if(str[0].equals(arr.get(position))){
                    happy = Storage.staticDm.get(i).happy;
                    anger = Storage.staticDm.get(i).anger;
                    anxiety = Storage.staticDm.get(i).anxiety;
                    //Log.d("heu", "happy + anger + anxiety: " + happy + " " + anger + " " + anxiety);
                }
            }
            if((happy + anger + anxiety) < 0 ) {
                viewHolder.backgroundTv.setBackgroundColor(Color.parseColor("#ffffff"));
            }
            if((happy + anger + anxiety) > 0 && (happy + anger + anxiety) <= 5 ) {
                viewHolder.backgroundTv.setBackgroundColor(Color.parseColor("#ff0000"));
            }
            if((happy + anger + anxiety) <= 10 &&  (happy + anger + anxiety) > 5) {
                viewHolder.backgroundTv.setBackgroundColor(Color.parseColor("#ffff00"));
            }
            if((happy + anger + anxiety) > 10 ) {
                viewHolder.backgroundTv.setBackgroundColor(Color.parseColor("#00ff00"));
            }

            if(arr.get(position).equals("")){
                viewHolder.dateTv.setText("");
            }else {
                String[] str2 = arr.get(position).split("/");
                viewHolder.dateTv.setText(str2[1]);
            }

            return convertView;
        }
    }
}
