package com.example.miis200;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ViewimageActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    private RecyclerView recyclerView;
    private Eyeimage_ItemRecycler itemRecyclerSetting;
    private Eyeimage_MyAdapter myAdapter;
    private List<Eyeimage_ItemRecycler> listRecycler = new ArrayList<Eyeimage_ItemRecycler>(  );
    String patientid;

    private Button printer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewimage);

        databaseHelper = new DatabaseHelper(this);

        printer = findViewById(R.id.Viewprinter);
        printer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewimageActivity.this,ChoiceimageActivity.class);
                startActivity(intent);
            }
        });

        //============================================
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        //创建LinearLayoutManager
        LinearLayoutManager manager = new LinearLayoutManager(this);
        //设置为横向滑动
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //设置
        recyclerView.setLayoutManager(manager);
        //实例化适配器
        myAdapter = new Eyeimage_MyAdapter(listRecycler);
        //设置适配器

        Intent intent = this.getIntent();
        patientid = intent.getStringExtra("patientid");
        int imagesize = databaseHelper.getImagePath(patientid).size();
        for (int i=0; i<imagesize; i++){
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            Bitmap thumbnail = BitmapFactory.decodeFile((String) databaseHelper.getImagePath(patientid).get(i), options);
            itemRecyclerSetting = new Eyeimage_ItemRecycler(thumbnail, (String) databaseHelper.getImagePath(patientid).get(i));
            myAdapter.addData(myAdapter.getItemCount(), itemRecyclerSetting);

        }
        recyclerView.setAdapter(myAdapter);



    }
}
