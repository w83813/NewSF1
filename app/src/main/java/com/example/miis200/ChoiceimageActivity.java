package com.example.miis200;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class ChoiceimageActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    private RecyclerView recyclerView;
    private ArrayList<ChoiceimageItemRecycler> choiceimageItemrecyclers = new ArrayList<>();
    private ChoiceimageAdapter adapter;
    private Button btn_printer;
    private ArrayList choiceimagelist;
    private Intent mReportInetent;

    String patientid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choiceimage);

        Intent intent = this.getIntent();
        patientid = intent.getStringExtra("patientid");

        databaseHelper = new DatabaseHelper(this);

        recyclerView = findViewById(R.id.choiceimagerecyclerview);

        btn_printer = findViewById(R.id.chimg_printer);

        mReportInetent = new Intent(this,ReportActivity.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapter = new ChoiceimageAdapter(this, choiceimageItemrecyclers);
        recyclerView.setAdapter(adapter);



        createList();

        btn_printer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choiceimagelist = new ArrayList();
                if(adapter.getSelected().size() == 0){
                    showToast("No Selection.");
                } else if(adapter.getSelected().size() > 4){
                    showToast("Select up to four photos.");
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < adapter.getSelected().size(); i++) {
                        //stringBuilder.append(adapter.getSelected().get(i).getMemo());
                        //stringBuilder.append(adapter.getSelected().get(i).getImagepath());
                        //stringBuilder.append("\n");
                        choiceimagelist.add(adapter.getSelected().get(i).getImagepath());
                        choiceimagelist.add(adapter.getSelected().get(i).getMemo());
                    }
                    mReportInetent.putIntegerArrayListExtra("imagepathlist",choiceimagelist);
                    mReportInetent.putExtra("patientid",patientid);
                    startActivity(mReportInetent);
                    //showToast(stringBuilder.toString().trim());
                }
            }
        });

    }

    private void createList() {
        int imagesize = databaseHelper.getImagePath(patientid).size();

        choiceimageItemrecyclers = new ArrayList<>();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap thumbnail;

        for (int i=0; i<imagesize; i++){
            thumbnail = BitmapFactory.decodeFile((String) databaseHelper.getImagePath(patientid).get(i), options);
            ChoiceimageItemRecycler choiceimageItemrecycler = new ChoiceimageItemRecycler();
            choiceimageItemrecycler.setMemo((String) databaseHelper.getMemo(patientid).get(i));
            choiceimageItemrecycler.setEyeimage(thumbnail);
            choiceimageItemrecycler.setImagepath((String) databaseHelper.getImagePath(patientid).get(i));
            choiceimageItemrecyclers.add(choiceimageItemrecycler);
        }
        adapter.setChoiceimageItemrecyclers(choiceimageItemrecyclers);
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
