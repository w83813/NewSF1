package com.example.miis200;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.chrisbanes.photoview.PhotoView;

import org.opencv.photo.Photo;

import java.util.ArrayList;
import java.util.List;

public class ViewimageActivity extends AppCompatActivity implements NotesRecyclerAdapter.OnNoteListener, FloatingActionButton.OnClickListener {

    DatabaseHelper databaseHelper;
    String patientid;

    private Button printer;
    private PhotoView viewimg;

    private RecyclerView mRecyclerView;
    private NotesRecyclerAdapter mNoteRecyclerAdapter;
    private ArrayList<Note> mNotes = new ArrayList<>();
    private Note note;
    private BitmapFactory.Options options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewimage);
        mRecyclerView = findViewById(R.id.recycleView);
        viewimg = findViewById(R.id.viewimg);



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);
        mNoteRecyclerAdapter = new NotesRecyclerAdapter(mNotes, this);

        databaseHelper = new DatabaseHelper(this);

        options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        printer = findViewById(R.id.Viewprinter);
        printer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewimageActivity.this,ChoiceimageActivity.class);
                startActivity(intent);
            }
        });



        Intent intent = this.getIntent();
        patientid = intent.getStringExtra("patientid");
        int imagesize = databaseHelper.getImagePath(patientid).size();
        for (int i=0; i<imagesize; i++){
            Bitmap thumbnail = BitmapFactory.decodeFile((String) databaseHelper.getImagePath(patientid).get(i), options);
            note = new Note(thumbnail,(String) databaseHelper.getImagePath(patientid).get(i));
            mNoteRecyclerAdapter.addData(mNoteRecyclerAdapter.getItemCount(),note);
        }
        mRecyclerView.setAdapter(mNoteRecyclerAdapter);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onNoteClick(int position) {
        Log.v("mkmkmk",String.valueOf(mNotes.get(position)));
        String imagepath = mNotes.get(position).toString();
        Bitmap bitmap = BitmapFactory.decodeFile(imagepath,options);
        viewimg.setImageBitmap(bitmap);
    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };


}
