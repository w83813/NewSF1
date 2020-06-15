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

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {

    private ArrayList imagepathlist;
    private ImageView report_image1,report_image2,report_image3,report_image4;
    private TextView report_memo1,report_memo2,report_memo3,report_memo4;
    private FrameLayout report_framelayout1,report_framelayout2,report_framelayout3,report_framelayout4;
    private LinearLayout layout_memo1,layout_memo2,layout_memo3,layout_memo4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

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

        layout_memo1 = findViewById(R.id.layout_memo1);
        layout_memo2 = findViewById(R.id.layout_memo2);
        layout_memo3 = findViewById(R.id.layout_memo3);
        layout_memo4 = findViewById(R.id.layout_memo4);

        Intent intent  =  getIntent();
        imagepathlist = intent.getStringArrayListExtra("imagepathlist");

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        if (imagepathlist.size() >= 1) {
            report_framelayout1.setVisibility(View.VISIBLE);
            layout_memo1.setVisibility(View.VISIBLE);
            Bitmap thumbnail = BitmapFactory.decodeFile((String) imagepathlist.get(0), options);
            report_image1.setImageBitmap(thumbnail);
            report_memo1.setText((String) imagepathlist.get(1));
        }

        if (imagepathlist.size() >= 3) {
            report_framelayout2.setVisibility(View.VISIBLE);
            layout_memo2.setVisibility(View.VISIBLE);
            Bitmap thumbnail = BitmapFactory.decodeFile((String) imagepathlist.get(2), options);
            report_image2.setImageBitmap(thumbnail);
            report_memo2.setText((String) imagepathlist.get(3));
        }

        if (imagepathlist.size() >= 5) {
            report_framelayout3.setVisibility(View.VISIBLE);
            layout_memo3.setVisibility(View.VISIBLE);
            Bitmap thumbnail = BitmapFactory.decodeFile((String) imagepathlist.get(4), options);
            report_image3.setImageBitmap(thumbnail);
            report_memo3.setText((String) imagepathlist.get(5));
        }

        if (imagepathlist.size() >= 7) {
            report_framelayout4.setVisibility(View.VISIBLE);
            layout_memo4.setVisibility(View.VISIBLE);
            Bitmap thumbnail = BitmapFactory.decodeFile((String) imagepathlist.get(6), options);
            report_image4.setImageBitmap(thumbnail);
            report_memo4.setText((String) imagepathlist.get(7));
        }
    }

    public void onBackPressed() {
        finish();
    }
}