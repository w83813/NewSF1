package com.example.miis200;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReportActivity extends AppCompatActivity {

    private static final String TAG = "ReportActivity";
    private ArrayList imagepathlist;
    private ImageView report_image1,report_image2,report_image3,report_image4;
    private ImageView circleborder1,circleborder2,circleborder3,circleborder4;
    private TextView report_memo1,report_memo2,report_memo3,report_memo4;
    private FrameLayout report_framelayout1,report_framelayout2,report_framelayout3,report_framelayout4;
    private LinearLayout layout_memo1,layout_memo2,layout_memo3,layout_memo4;
    DatabaseHelper databaseHelper;
    private String getPatientid,Reportdate;

    private String image1size,image2size,image3size,image4size;

    private SimpleDateFormat simpleDateFormat;
    private Date date;

    //patient info
    private TextView patientname,patientid,patientgender,patientage,patientphonenumber,patientcheckdate,patientreportdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        databaseHelper = new DatabaseHelper(this);

        report_image1 = findViewById(R.id.report_image1);
        report_image2 = findViewById(R.id.report_image2);
        report_image3 = findViewById(R.id.report_image3);
        report_image4 = findViewById(R.id.report_image4);

        report_memo1 = findViewById(R.id.report_memo1);
        report_memo2 = findViewById(R.id.report_memo2);
        report_memo3 = findViewById(R.id.report_memo3);
        report_memo4 = findViewById(R.id.report_memo4);

        report_framelayout1 = findViewById(R.id.report_framelayout1);
        report_framelayout2 = findViewById(R.id.report_framelayout2);
        report_framelayout3 = findViewById(R.id.report_framelayout3);
        report_framelayout4 = findViewById(R.id.report_framelayout4);

        circleborder1 = findViewById(R.id.circleborder1);
        circleborder2 = findViewById(R.id.circleborder2);
        circleborder3 = findViewById(R.id.circleborder3);
        circleborder4 = findViewById(R.id.circleborder4);

        layout_memo1 = findViewById(R.id.layout_memo1);
        layout_memo2 = findViewById(R.id.layout_memo2);
        layout_memo3 = findViewById(R.id.layout_memo3);
        layout_memo4 = findViewById(R.id.layout_memo4);

        patientname = findViewById(R.id.report_name);
        patientid = findViewById(R.id.report_id);
        patientgender = findViewById(R.id.report_gender);
        patientage = findViewById(R.id.report_age);
        patientphonenumber = findViewById(R.id.report_phonenumber);
        patientcheckdate = findViewById(R.id.report_checktime);
        patientreportdate = findViewById(R.id.report_date);

        simpleDateFormat = new SimpleDateFormat( "yyyy年MM月dd日 ");
        date = new Date( System.currentTimeMillis());
        Reportdate = simpleDateFormat.format(date);

        Intent intent  =  getIntent();
        imagepathlist = intent.getStringArrayListExtra("imagepathlist");
        getPatientid = intent.getStringExtra("patientid");

        patientname.setText((String) databaseHelper.getpatientinfo(intent.getStringExtra("patientid")).get(0));
        patientid.setText((String) databaseHelper.getpatientinfo(intent.getStringExtra("patientid")).get(1));
        patientphonenumber.setText((String) databaseHelper.getpatientinfo(intent.getStringExtra("patientid")).get(2));
        patientgender.setText((String) databaseHelper.getpatientinfo(intent.getStringExtra("patientid")).get(4));
        patientcheckdate.setText((String) databaseHelper.getpatientinfo(intent.getStringExtra("patientid")).get(5));
        patientage.setText((String) databaseHelper.getpatientinfo(intent.getStringExtra("patientid")).get(6));
        patientreportdate.setText(Reportdate);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        if (imagepathlist.size() >= 1) {
            report_framelayout1.setVisibility(View.VISIBLE);
            layout_memo1.setVisibility(View.VISIBLE);
            Bitmap thumbnail = BitmapFactory.decodeFile((String) imagepathlist.get(0), options);
            image1size = options.outWidth +"x"+options.outHeight;
            report_image1.setImageBitmap(thumbnail);
            report_memo1.setText((String) imagepathlist.get(1));
        }

        if (imagepathlist.size() >= 3) {
            report_framelayout2.setVisibility(View.VISIBLE);
            layout_memo2.setVisibility(View.VISIBLE);
            Bitmap thumbnail = BitmapFactory.decodeFile((String) imagepathlist.get(2), options);
            image2size = options.outWidth +"x"+options.outHeight;
            report_image2.setImageBitmap(thumbnail);
            report_memo2.setText((String) imagepathlist.get(3));
        }

        if (imagepathlist.size() >= 5) {
            report_framelayout3.setVisibility(View.VISIBLE);
            layout_memo3.setVisibility(View.VISIBLE);
            Bitmap thumbnail = BitmapFactory.decodeFile((String) imagepathlist.get(4), options);
            image3size = options.outWidth +"x"+options.outHeight;
            report_image3.setImageBitmap(thumbnail);
            report_memo3.setText((String) imagepathlist.get(5));
        }

        if (imagepathlist.size() >= 7) {
            report_framelayout4.setVisibility(View.VISIBLE);
            layout_memo4.setVisibility(View.VISIBLE);
            Bitmap thumbnail = BitmapFactory.decodeFile((String) imagepathlist.get(6), options);
            image4size = options.outWidth +"x"+options.outHeight;
            report_image4.setImageBitmap(thumbnail);
            report_memo4.setText((String) imagepathlist.get(7));
        }

        switch (image1size) {
            case "1716x1632":
                circleborder1.setImageResource(R.drawable.circleborder17161632);
                Log.i(TAG,"circleborder17161632");
                int width1 = circleborder1.getDrawable().getIntrinsicWidth();
                int heigh1 = circleborder1.getDrawable().getIntrinsicHeight();
                Picasso.get().load(R.drawable.circleborder17161632).resize(width1/3,heigh1/3).into(circleborder1);
                break;
            case "1801x1201":
                circleborder1.setImageResource(R.drawable.circleborder18011201);
                Log.i(TAG,"circleborder18011201");
                int width2 = circleborder1.getDrawable().getIntrinsicWidth();
                int heigh2 = circleborder1.getDrawable().getIntrinsicHeight();
                Picasso.get().load(R.drawable.circleborder18011201).resize(width2/3,heigh2/3).into(circleborder1);
                break;
            case "1956x1934":
                circleborder1.setImageResource(R.drawable.circleborder19561934);
                Log.i(TAG,"circleborder19561934");
                int width3 = circleborder1.getDrawable().getIntrinsicWidth();
                int heigh3 = circleborder1.getDrawable().getIntrinsicHeight();
                Picasso.get().load(R.drawable.circleborder19561934).resize(width3/3,heigh3/3).into(circleborder1);
                break;
            case "2304x1728":
                circleborder1.setImageResource(R.drawable.circleborder23041728);
                Log.i(TAG,"circleborder23041728");
                int width4 = circleborder1.getDrawable().getIntrinsicWidth();
                int heigh4 = circleborder1.getDrawable().getIntrinsicHeight();
                Picasso.get().load(R.drawable.circleborder23041728).resize(width4/3,heigh4/3).into(circleborder1);
                break;
            case "2400x2448":
                circleborder1.setImageResource(R.drawable.circleborder24002448);
                Log.i(TAG,"circleborder24002448");
                int width5 = circleborder1.getDrawable().getIntrinsicWidth();
                int heigh5 = circleborder1.getDrawable().getIntrinsicHeight();
                Picasso.get().load(R.drawable.circleborder24002448).resize(width5/3,heigh5/3).into(circleborder1);
                break;
            case "2560x1920":
                circleborder1.setImageResource(R.drawable.circleborder25601921);
                Log.i(TAG,"circleborder25601921");
                int width6 = circleborder1.getDrawable().getIntrinsicWidth();
                int heigh6 = circleborder1.getDrawable().getIntrinsicHeight();
                Picasso.get().load(R.drawable.circleborder25601921).resize(width6/3,heigh6/3).into(circleborder1);
                break;
            case "3200x2550":
                circleborder1.setImageResource(R.drawable.circleborder32002550);
                Log.i(TAG,"circleborder32002550");
                int width7 = circleborder1.getDrawable().getIntrinsicWidth();
                int heigh7 = circleborder1.getDrawable().getIntrinsicHeight();
                Picasso.get().load(R.drawable.circleborder32002550).resize(width7/3,heigh7/3).into(circleborder1);
                break;
            case "3696x2448":
                circleborder1.setImageResource(R.drawable.circleborder36962448);
                Log.i(TAG,"circleborder36962448");
                int width8 = circleborder1.getDrawable().getIntrinsicWidth();
                int heigh8 = circleborder1.getDrawable().getIntrinsicHeight();
                Picasso.get().load(R.drawable.circleborder36962448).resize(width8/3,heigh8/3).into(circleborder1);
                break;
            case "3888x2592":
                circleborder1.setImageResource(R.drawable.circleborder38882592);
                Log.i(TAG,"circleborder38882592");
                int width9 = circleborder1.getDrawable().getIntrinsicWidth();
                int heigh9 = circleborder1.getDrawable().getIntrinsicHeight();
                Picasso.get().load(R.drawable.circleborder38882592).resize(width9/3,heigh9/3).into(circleborder1);
                break;
            case "768x768":
                circleborder1.setImageResource(R.drawable.circleborder768768);
                Log.i(TAG,"circleborder768768");
                int width10 = circleborder1.getDrawable().getIntrinsicWidth();
                int heigh10 = circleborder1.getDrawable().getIntrinsicHeight();
                Picasso.get().load(R.drawable.circleborder768768).resize(width10/3,heigh10/3).into(circleborder1);
                break;
        }

        if (image2size != null ){
            switch (image2size) {
                case "1716x1632":
                    circleborder2.setImageResource(R.drawable.circleborder17161632);
                    Log.i(TAG,"circleborder17161632");
                    int width1 = circleborder2.getDrawable().getIntrinsicWidth();
                    int heigh1 = circleborder2.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder17161632).resize(width1/3,heigh1/3).into(circleborder2);
                    break;
                case "1801x1201":
                    circleborder2.setImageResource(R.drawable.circleborder18011201);
                    Log.i(TAG,"circleborder18011201");
                    int width2 = circleborder2.getDrawable().getIntrinsicWidth();
                    int heigh2 = circleborder2.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder18011201).resize(width2/3,heigh2/3).into(circleborder2);
                    break;
                case "1956x1934":
                    circleborder2.setImageResource(R.drawable.circleborder19561934);
                    Log.i(TAG,"circleborder19561934");
                    int width3 = circleborder2.getDrawable().getIntrinsicWidth();
                    int heigh3 = circleborder2.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder19561934).resize(width3/3,heigh3/3).into(circleborder2);
                    break;
                case "2304x1728":
                    circleborder2.setImageResource(R.drawable.circleborder23041728);
                    Log.i(TAG,"circleborder23041728");
                    int width4 = circleborder2.getDrawable().getIntrinsicWidth();
                    int heigh4 = circleborder2.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder23041728).resize(width4/3,heigh4/3).into(circleborder2);
                    break;
                case "2400x2448":
                    circleborder2.setImageResource(R.drawable.circleborder24002448);
                    Log.i(TAG,"circleborder24002448");
                    int width5 = circleborder2.getDrawable().getIntrinsicWidth();
                    int heigh5 = circleborder2.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder24002448).resize(width5/3,heigh5/3).into(circleborder2);
                    break;
                case "2560x1920":
                    circleborder2.setImageResource(R.drawable.circleborder25601921);
                    Log.i(TAG,"circleborder25601921");
                    int width6 = circleborder2.getDrawable().getIntrinsicWidth();
                    int heigh6 = circleborder2.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder25601921).resize(width6/3,heigh6/3).into(circleborder2);
                    break;
                case "3200x2550":
                    circleborder2.setImageResource(R.drawable.circleborder32002550);
                    Log.i(TAG,"circleborder32002550");
                    int width7 = circleborder2.getDrawable().getIntrinsicWidth();
                    int heigh7 = circleborder2.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder32002550).resize(width7/3,heigh7/3).into(circleborder2);
                    break;
                case "3696x2448":
                    circleborder2.setImageResource(R.drawable.circleborder36962448);
                    Log.i(TAG,"circleborder36962448");
                    int width8 = circleborder2.getDrawable().getIntrinsicWidth();
                    int heigh8 = circleborder2.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder36962448).resize(width8/3,heigh8/3).into(circleborder2);
                    break;
                case "3888x2592":
                    circleborder2.setImageResource(R.drawable.circleborder38882592);
                    Log.i(TAG,"circleborder38882592");
                    int width9 = circleborder2.getDrawable().getIntrinsicWidth();
                    int heigh9 = circleborder2.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder38882592).resize(width9/3,heigh9/3).into(circleborder2);
                    break;
                case "768x768":
                    circleborder2.setImageResource(R.drawable.circleborder768768);
                    Log.i("circleborder768768","circleborder768768");
                    int width10 = circleborder2.getDrawable().getIntrinsicWidth();
                    int heigh10 = circleborder2.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder768768).resize(width10/3,heigh10/3).into(circleborder2);
                    break;
            }
        }

        if (image3size != null){
            switch (image3size) {
                case "1716x1632":
                    circleborder3.setImageResource(R.drawable.circleborder17161632);
                    Log.i(TAG,"circleborder17161632");
                    int width1 = circleborder3.getDrawable().getIntrinsicWidth();
                    int heigh1 = circleborder3.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder17161632).resize(width1/3,heigh1/3).into(circleborder3);
                    break;
                case "1801x1201":
                    circleborder3.setImageResource(R.drawable.circleborder18011201);
                    Log.i(TAG,"circleborder18011201");
                    int width2 = circleborder3.getDrawable().getIntrinsicWidth();
                    int heigh2 = circleborder3.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder18011201).resize(width2/3,heigh2/3).into(circleborder3);
                    break;
                case "1956x1934":
                    circleborder3.setImageResource(R.drawable.circleborder19561934);
                    Log.i(TAG,"circleborder19561934");
                    int width3 = circleborder3.getDrawable().getIntrinsicWidth();
                    int heigh3 = circleborder3.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder19561934).resize(width3/3,heigh3/3).into(circleborder3);
                    break;
                case "2304x1728":
                    circleborder3.setImageResource(R.drawable.circleborder23041728);
                    Log.i(TAG,"circleborder23041728");
                    int width4 = circleborder3.getDrawable().getIntrinsicWidth();
                    int heigh4 = circleborder3.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder23041728).resize(width4/3,heigh4/3).into(circleborder3);
                    break;
                case "2400x2448":
                    circleborder3.setImageResource(R.drawable.circleborder24002448);
                    Log.i(TAG,"circleborder24002448");
                    int width5 = circleborder3.getDrawable().getIntrinsicWidth();
                    int heigh5 = circleborder3.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder24002448).resize(width5/3,heigh5/3).into(circleborder3);
                    break;
                case "2560x1920":
                    circleborder3.setImageResource(R.drawable.circleborder25601921);
                    Log.i(TAG,"circleborder25601921");
                    int width6 = circleborder3.getDrawable().getIntrinsicWidth();
                    int heigh6 = circleborder3.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder25601921).resize(width6/3,heigh6/3).into(circleborder3);
                    break;
                case "3200x2550":
                    circleborder3.setImageResource(R.drawable.circleborder32002550);
                    Log.i(TAG,"circleborder32002550");
                    int width7 = circleborder3.getDrawable().getIntrinsicWidth();
                    int heigh7 = circleborder3.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder32002550).resize(width7/3,heigh7/3).into(circleborder3);
                    break;
                case "3696x2448":
                    circleborder3.setImageResource(R.drawable.circleborder36962448);
                    Log.i(TAG,"circleborder36962448");
                    int width8 = circleborder3.getDrawable().getIntrinsicWidth();
                    int heigh8 = circleborder3.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder36962448).resize(width8/3,heigh8/3).into(circleborder3);
                    break;
                case "3888x2592":
                    circleborder3.setImageResource(R.drawable.circleborder38882592);
                    Log.i(TAG,"circleborder38882592");
                    int width9 = circleborder3.getDrawable().getIntrinsicWidth();
                    int heigh9 = circleborder3.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder38882592).resize(width9/3,heigh9/3).into(circleborder3);
                    break;
                case "768x768":
                    circleborder3.setImageResource(R.drawable.circleborder768768);
                    Log.i(TAG,"circleborder768768");
                    int width10 = circleborder3.getDrawable().getIntrinsicWidth();
                    int heigh10 = circleborder3.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder768768).resize(width10/3,heigh10/3).into(circleborder3);
                    break;
            }
        }

        if (image4size != null){
            switch (image4size) {
                case "1716x1632":
                    circleborder4.setImageResource(R.drawable.circleborder17161632);
                    Log.i(TAG,"circleborder17161632");
                    int width1 = circleborder4.getDrawable().getIntrinsicWidth();
                    int heigh1 = circleborder4.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder17161632).resize(width1/3,heigh1/3).into(circleborder4);
                    break;
                case "1801x1201":
                    circleborder4.setImageResource(R.drawable.circleborder18011201);
                    Log.i(TAG,"circleborder18011201");
                    int width2 = circleborder4.getDrawable().getIntrinsicWidth();
                    int heigh2 = circleborder4.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder18011201).resize(width2/3,heigh2/3).into(circleborder4);
                    break;
                case "1956x1934":
                    circleborder4.setImageResource(R.drawable.circleborder19561934);
                    Log.i(TAG,"circleborder19561934");
                    int width3 = circleborder4.getDrawable().getIntrinsicWidth();
                    int heigh3 = circleborder4.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder19561934).resize(width3/3,heigh3/3).into(circleborder4);
                    break;
                case "2304x1728":
                    circleborder4.setImageResource(R.drawable.circleborder23041728);
                    Log.i(TAG,"circleborder23041728");
                    int width4 = circleborder4.getDrawable().getIntrinsicWidth();
                    int heigh4 = circleborder4.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder23041728).resize(width4/3,heigh4/3).into(circleborder4);
                    break;
                case "2400x2448":
                    circleborder4.setImageResource(R.drawable.circleborder24002448);
                    Log.i(TAG,"circleborder24002448");
                    int width5 = circleborder4.getDrawable().getIntrinsicWidth();
                    int heigh5 = circleborder4.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder24002448).resize(width5/3,heigh5/3).into(circleborder4);
                    break;
                case "2560x1920":
                    circleborder4.setImageResource(R.drawable.circleborder25601921);
                    Log.i(TAG,"circleborder25601921");
                    int width6 = circleborder4.getDrawable().getIntrinsicWidth();
                    int heigh6 = circleborder4.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder25601921).resize(width6/3,heigh6/3).into(circleborder4);
                    break;
                case "3200x2550":
                    circleborder4.setImageResource(R.drawable.circleborder32002550);
                    Log.i(TAG,"circleborder32002550");
                    int width7 = circleborder4.getDrawable().getIntrinsicWidth();
                    int heigh7 = circleborder4.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder32002550).resize(width7/3,heigh7/3).into(circleborder4);
                    break;
                case "3696x2448":
                    circleborder4.setImageResource(R.drawable.circleborder36962448);
                    Log.i(TAG,"circleborder36962448");
                    int width8 = circleborder4.getDrawable().getIntrinsicWidth();
                    int heigh8 = circleborder4.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder36962448).resize(width8/3,heigh8/3).into(circleborder4);
                    break;
                case "3888x2592":
                    circleborder4.setImageResource(R.drawable.circleborder38882592);
                    Log.i(TAG,"circleborder38882592");
                    int width9 = circleborder4.getDrawable().getIntrinsicWidth();
                    int heigh9 = circleborder4.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder38882592).resize(width9/3,heigh9/3).into(circleborder4);
                    break;
                case "768x768":
                    circleborder4.setImageResource(R.drawable.circleborder768768);
                    Log.i(TAG,"circleborder768768");
                    int width10 = circleborder4.getDrawable().getIntrinsicWidth();
                    int heigh10 = circleborder4.getDrawable().getIntrinsicHeight();
                    Picasso.get().load(R.drawable.circleborder768768).resize(width10/3,heigh10/3).into(circleborder4);
                    break;
            }
        }
    }

    public void onBackPressed() {
        finish();
    }
}
