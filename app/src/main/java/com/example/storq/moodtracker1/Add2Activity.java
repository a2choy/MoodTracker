package com.example.storq.moodtracker1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Add2Activity extends AppCompatActivity implements View.OnClickListener {

    ImageView happyIv, anxietyIv, angerIv;
    EditText inputEt;
    Button submitBt;
    String note;
    boolean edit;
    int anger,anxiety,happy, pos;
    String dateStr1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add2);

        happyIv = (ImageView)findViewById(R.id.happyIv);
        anxietyIv = (ImageView)findViewById(R.id.anxietyIv);
        angerIv = (ImageView)findViewById(R.id.angerIv);
        inputEt = (EditText)findViewById(R.id.inputEt);
        submitBt = (Button)findViewById(R.id.submitBt);

        submitBt.setOnClickListener(this);

        init();

    }

    private void init(){


        Intent intent = getIntent();
        anger = intent.getIntExtra("anger",0);
        anxiety = intent.getIntExtra("anxiety",0);
        happy = intent.getIntExtra("happy",0);
        edit = intent.getBooleanExtra("edit", false);
        pos = intent.getIntExtra("pos", -1);
        dateStr1 = intent.getStringExtra("date");

        if(happy == 1){
            happyIv.setImageResource(R.drawable.very_sad);
        }else if(happy == 2){
            happyIv.setImageResource(R.drawable.sad);
        }else if(happy == 3){
            happyIv.setImageResource(R.drawable.soso);
        }else if(happy == 4){
            happyIv.setImageResource(R.drawable.happy);
        }else if(happy == 5){
            happyIv.setImageResource(R.drawable.very_happy);
        }

        if(anxiety == 1){
            anxietyIv.setImageResource(R.drawable.very_sad);
        }else if(anxiety == 2){
            anxietyIv.setImageResource(R.drawable.sad);
        }else if(anxiety == 3){
            anxietyIv.setImageResource(R.drawable.soso);
        }else if(anxiety == 4){
            anxietyIv.setImageResource(R.drawable.happy);
        }else if(anxiety == 5){
            anxietyIv.setImageResource(R.drawable.very_happy);
        }

        if(anger == 1){
            angerIv.setImageResource(R.drawable.very_sad);
        }else if(anger == 2){
            angerIv.setImageResource(R.drawable.sad);
        }else if(anger == 3){
            angerIv.setImageResource(R.drawable.soso);
        }else if(anger == 4){
            angerIv.setImageResource(R.drawable.happy);
        }else if(anger == 5){
            angerIv.setImageResource(R.drawable.very_happy);
        }



    }
    private void showDialogNoNote(){
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
        alt_bld.setMessage(R.string.dialog_no_note).setCancelable(
                false).setPositiveButton(R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        submit();
                        finish();
                    }
                }).setNegativeButton(R.string.no,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        AlertDialog alert = alt_bld.create();
        alert.show();
    }
    private void submit(){
        SQLiteDatabase db = null;
        if (db == null) {
            db = openOrCreateDatabase("sqlist_test.db", Context.MODE_PRIVATE, null);
        }

        String dateStr;
        if(dateStr1 == null) {
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            Date date = new Date();
            dateStr = formatter.format(date);
        } else {
            dateStr = dateStr1;
        }

        if(edit) {
            int _id = Storage.staticDm.get(pos)._id;
            dateStr = Storage.staticDm.get(pos).day;
            String str = "UPDATE mood SET date = '" + dateStr +
                    "', happy = '" + happy +
                    "',anger = '" + anger +
                    "',anxiety = '" + anxiety +
                    "',note = '" + note +
                    "' WHERE _id = " + _id + ";";
            db.execSQL(str);
        }else {
            String str = "INSERT INTO mood (date,happy,anger,anxiety,note) VALUES ('" + dateStr +
                    "','" + happy + "','" + anger + "','" + anxiety + "','" + note + "')";
            db.execSQL(str);
        }
        db.close();

    }
    @Override
    public void onClick(View v) {

        note = inputEt.getText().toString().trim();
        if(note.length() == 0){
            showDialogNoNote();
        }else{
            submit();
            Intent intent = new Intent(this, com.example.storq.moodtracker1.MainActivity.class);

            startActivity(intent);
        }

    }
}
