package com.example.storq.moodtracker1;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    ListView mainLv;
    Button addBt, calendarBt, statBt, searchBt;
    MyAdapter adapter;
    int pos;

    //ArrayList<DayMood> arr = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLv = (ListView) findViewById(R.id.mainLv);
        addBt = (Button) findViewById(R.id.addBt);
        calendarBt = (Button) findViewById(R.id.calendarBt);
        statBt = (Button) findViewById(R.id.statBt);
        searchBt = (Button) findViewById(R.id.searchBt);

        addBt.setOnClickListener(this);
        calendarBt.setOnClickListener(this);
        statBt.setOnClickListener(this);
        searchBt.setOnClickListener(this);

        init();
    }

    private void init() {

        adapter = new MyAdapter(this);
        mainLv.setAdapter(adapter);
        mainLv.setOnItemClickListener(this);

        SQLiteDatabase db = null;
        if (db == null) {
            db = openOrCreateDatabase("sqlist_test.db", Context.MODE_PRIVATE, null);
        }

        //db.execSQL("DROP TABLE mood;");

        db.execSQL("CREATE TABLE IF NOT EXISTS mood("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "date TEXT,"
                + "happy INTEGER,"
                + "anger INTEGER,"
                + "anxiety INTEGER,"
                + "note TEXT"
                + ");"
        );

        /*db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('11/11/2018 02:33','5','1','1','weights')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('11/12/2018 02:33','5','2','5','work out')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('11/13/2018 02:33','5','1','3','exercise')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('11/14/2018 02:33','5','3','2','sit')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('11/15/2018 02:33','5','1','3','drink')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('11/16/2018 02:33','5','4','4','work')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('11/17/2018 02:33','5','1','5','sleep')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('11/18/2018 02:33','5','2','1','travel')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('11/19/2018 02:33','5','1','2','michelin')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('11/20/2018 02:33','5','3','3','eat')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('11/21/2018 02:33','5','1','4','tablet')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('11/22/2018 02:33','5','4','5','phone')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('11/23/2018 02:33','5','1','3','console')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('11/24/2018 02:33','5','2','2','computer')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('11/25/2018 02:33','5','1','3','squash')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('11/26/2018 02:33','5','5','1','badminton')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('11/27/2018 02:33','5','1','4','tennis')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('11/28/2018 02:33','5','5','5','radio')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('11/29/2018 02:33','5','1','1','game')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('11/30/2018 02:33','5','3','2','pet')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('11/31/2018 02:33','5','1','3','cat')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('12/01/2018 02:33','5','2','4','dog')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('12/02/2018 02:33','5','3','5','cook')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('12/03/2018 02:33','2','4','5','talk')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('12/04/2018 02:33','2','1','5','walking')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('12/05/2018 02:33','2','5','4','driving')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('12/06/2018 02:33','2','1','1','fishing')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('12/07/2018 02:33','2','3','2','running')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('12/08/2018 02:33','1','3','1','swimming')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('12/09/2018 02:33','3','1','2','hiking')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('12/10/2018 02:33','2','4','3','jump')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('12/11/2018 02:33','1','5','4','dash')");
        db.execSQL("INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('12/12/2018 02:33','2','2','5','programming')");*/

        Cursor c = db.rawQuery("SELECT * FROM mood ORDER BY _id DESC", null);

        Storage.staticDm.clear();
        //arr.clear();
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            int _id = c.getInt(0);
            String date = c.getString(1);
            int happy = c.getInt(2);
            int anger = c.getInt(3);
            int anxiety = c.getInt(4);
            String note = c.getString(5);
            DayMood dm = new DayMood(_id, date, happy, anger, anxiety, note);
            Storage.staticDm.add(dm);
            //arr.add(dm);
            c.moveToNext();
        }
        c.close();
        db.close();

        Collections.sort(Storage.staticDm);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.addBt:

                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Date date = new Date();
                String dateStr = formatter.format(date);

                if (Storage.staticDm.size() == 0) {
                    intent = new Intent(this, com.example.storq.moodtracker1.AddActivity.class);
                    intent.putExtra("edit", false);
                    startActivityForResult(intent, 3000);
                } else {

                    String[] str =
                            Storage.staticDm.get(0).day.split(" ");

                    //Log.d("heu","str[0]: " + str[0] + " datestr: " + dateStr);

                    if (dateStr.equals(str[0])) {
                        Toast.makeText(this, getResources().getString(R.string.day_limit), Toast.LENGTH_LONG).show();
                    } else {
                        intent = new Intent(this, com.example.storq.moodtracker1.AddActivity.class);
                        intent.putExtra("edit", false);
                        startActivityForResult(intent, 3000);
                    }
                }
                break;
            case R.id.calendarBt:
                intent = new Intent(this, com.example.storq.moodtracker1.CalendarActivity.class);
                startActivityForResult(intent, 2000);
                break;
            case R.id.statBt:
                intent = new Intent(this, com.example.storq.moodtracker1.StatActivity.class);
                startActivityForResult(intent, 1000);
                break;
            case R.id.searchBt:
                intent = new Intent(this, com.example.storq.moodtracker1.SearchActivity.class);
                startActivityForResult(intent, 4000);
                break;
        }
    }

    private void deleteDb() {
        SQLiteDatabase db = null;
        if (db == null) {
            db = openOrCreateDatabase("sqlist_test.db", Context.MODE_PRIVATE, null);
        }
        int _id = Storage.staticDm.get(pos)._id;
        String sql = "DELETE FROM mood WHERE _id = " + _id + ";";

        db.execSQL(sql);
        db.close();

    }

    private void showDialog() {
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
        alt_bld.setMessage(getResources().getString(R.string.update_delete)).setCancelable(
                true).setPositiveButton(getResources().getString(R.string.delete),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteDb();
                        selectData();
                    }
                }).setNegativeButton(getResources().getString(R.string.update),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(MainActivity.this, com.example.storq.moodtracker1.AddActivity.class);
                        intent.putExtra("edit", true);
                        intent.putExtra("pos", pos);
                        startActivityForResult(intent, 3000);
                    }
                });
        AlertDialog alert = alt_bld.create();
        alert.show();
    }

    @Override
    public void onResume() {

        super.onResume();
        selectData();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("heu", "reached it");
        this.pos = position;
        showDialog();
    }

    class MyAdapter extends ArrayAdapter {
        LayoutInflater lnf;

        public MyAdapter(Activity context) {
            super(context, R.layout.item, Storage.staticDm);
            lnf = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return Storage.staticDm.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return Storage.staticDm.get(position);
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

            String[] str = Storage.staticDm.get(position).day.split(" ");
            viewHolder.date.setText(str[0]);

            if (Storage.staticDm.get(position).happy == 1) {
                viewHolder.happyIv.setImageResource(R.drawable.very_sad);
            } else if (Storage.staticDm.get(position).happy == 2) {
                viewHolder.happyIv.setImageResource(R.drawable.sad);
            } else if (Storage.staticDm.get(position).happy == 3) {
                viewHolder.happyIv.setImageResource(R.drawable.soso);
            } else if (Storage.staticDm.get(position).happy == 4) {
                viewHolder.happyIv.setImageResource(R.drawable.happy);
            } else if (Storage.staticDm.get(position).happy == 5) {
                viewHolder.happyIv.setImageResource(R.drawable.very_happy);
            }

            if (Storage.staticDm.get(position).anger == 1) {
                viewHolder.angerIv.setImageResource(R.drawable.very_sad);
            } else if (Storage.staticDm.get(position).anger == 2) {
                viewHolder.angerIv.setImageResource(R.drawable.sad);
            } else if (Storage.staticDm.get(position).anger == 3) {
                viewHolder.angerIv.setImageResource(R.drawable.soso);
            } else if (Storage.staticDm.get(position).anger == 4) {
                viewHolder.angerIv.setImageResource(R.drawable.happy);
            } else if (Storage.staticDm.get(position).anger == 5) {
                viewHolder.angerIv.setImageResource(R.drawable.very_happy);
            }

            if (Storage.staticDm.get(position).anxiety == 1) {
                viewHolder.anxietyIv.setImageResource(R.drawable.very_sad);
            } else if (Storage.staticDm.get(position).anxiety == 2) {
                viewHolder.anxietyIv.setImageResource(R.drawable.sad);
            } else if (Storage.staticDm.get(position).anxiety == 3) {
                viewHolder.anxietyIv.setImageResource(R.drawable.soso);
            } else if (Storage.staticDm.get(position).anxiety == 4) {
                viewHolder.anxietyIv.setImageResource(R.drawable.happy);
            } else if (Storage.staticDm.get(position).anxiety == 5) {
                viewHolder.anxietyIv.setImageResource(R.drawable.very_happy);
            }

            return convertView;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        selectData();
    }

    private void selectData() {
        SQLiteDatabase db = null;
        if (db == null) {
            db = openOrCreateDatabase("sqlist_test.db", Context.MODE_PRIVATE, null);
        }
        Cursor c = db.rawQuery("SELECT * FROM mood ORDER BY _id DESC", null);

        Storage.staticDm.clear();
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            int _id = c.getInt(0);
            String date = c.getString(1);
            int happy = c.getInt(2);
            int anger = c.getInt(3);
            int anxiety = c.getInt(4);
            String note = c.getString(5);
            DayMood dm = new DayMood(_id, date, happy, anger, anxiety, note);
            Storage.staticDm.add(dm);
            c.moveToNext();
        }
        c.close();
        db.close();

        Collections.sort(Storage.staticDm);

        adapter.notifyDataSetChanged();
    }
}
