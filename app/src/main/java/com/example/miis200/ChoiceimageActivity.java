package com.example.miis200;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ChoiceimageActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    private RecyclerView recyclerView;
    private ArrayList<ChoiceimageItemrecycler> choiceimageItemrecyclers = new ArrayList<>();
    private ChoiceimageAdapter adapter;
    private Button_Click btnGetSelected;

    String patientid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choiceimage);

        databaseHelper = new DatabaseHelper(this);

        recyclerView = findViewById(R.id.choiceimagerecyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapter = new ChoiceimageAdapter(this, choiceimageItemrecyclers);
        recyclerView.setAdapter(adapter);

        createList();
    }

    private void createList() {
        Intent intent = this.getIntent();
        patientid = intent.getStringExtra("patientid");
        int imagesize = databaseHelper.getImagePath(patientid).size();

        choiceimageItemrecyclers = new ArrayList<>();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        for (int i=0; i<imagesize; i++){
            Bitmap thumbnail = BitmapFactory.decodeFile((String) databaseHelper.getImagePath(patientid).get(i), options);
            ChoiceimageItemrecycler choiceimageItemrecycler = new ChoiceimageItemrecycler();
            choiceimageItemrecycler.setName((String) databaseHelper.getImagePath(patientid).get(i));
            choiceimageItemrecycler.setEyeimage(thumbnail);
            choiceimageItemrecyclers.add(choiceimageItemrecycler);
        }
        adapter.setChoiceimageItemrecyclers(choiceimageItemrecyclers);
    }

}
