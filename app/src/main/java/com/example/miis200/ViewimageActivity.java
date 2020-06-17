package com.example.miis200;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.List;

public class ViewimageActivity extends AppCompatActivity implements ViewimageAdapter.OnNoteListener, FloatingActionButton.OnClickListener {

    private static final String TAG = "ViewimageActivity";
    DatabaseHelper databaseHelper;
    String patientid;
    private int imagesize;

    private Button printer,viewfinish;
    private PhotoView viewimg;
    private EditText memo;

    private RecyclerView mRecyclerView;
    private ViewimageAdapter mNoteRecyclerAdapter;
    private ArrayList<ViewimageItemRecycler> mNotes = new ArrayList<>();
    private ViewimageItemRecycler note;
    private BitmapFactory.Options options;
    List<String> list = new ArrayList<String>();
    private String last_imagepath,now_imagepath;
    SQLiteDatabase idDB;
    private Intent mChoiceActivityIntent,mOptioinActivityIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewimage);
        mRecyclerView = findViewById(R.id.recycleView);
        viewimg = findViewById(R.id.viewimg);
        memo = findViewById(R.id.memo);
        viewfinish = findViewById(R.id.Viewfinish);
        printer = findViewById(R.id.viewimageprinter);

        mChoiceActivityIntent = new Intent(this,ChoiceimageActivity.class);

        mOptioinActivityIntent = new Intent(this,Option.class);
        mOptioinActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);
        mNoteRecyclerAdapter = new ViewimageAdapter(mNotes, this);

        databaseHelper = new DatabaseHelper(this);
        idDB = databaseHelper.getReadableDatabase();

        options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        viewfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("MEMO", memo.getText().toString());
                idDB.update("TABLE2", values, "PATIENTID=? and IMAGEPATH=?", new String[]{patientid, list.get(list.size()-1)});
                databaseHelper.updatepatientstatus_2(patientid);
                startActivity(mOptioinActivityIntent);
            }
        });


        Intent intent = this.getIntent();
        patientid = intent.getStringExtra("patientid");
        imagesize = databaseHelper.getImagePath(patientid).size();
        for (int i=0; i<imagesize; i++){
            Bitmap thumbnail = BitmapFactory.decodeFile((String) databaseHelper.getImagePath(patientid).get(i), options);
            note = new ViewimageItemRecycler(thumbnail,(String) databaseHelper.getImagePath(patientid).get(i));
            mNoteRecyclerAdapter.addData(mNoteRecyclerAdapter.getItemCount(),note);
        }
        mRecyclerView.setAdapter(mNoteRecyclerAdapter);
        onNoteClick(0);

        printer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("MEMO", memo.getText().toString());
                idDB.update("TABLE2", values, "PATIENTID=? and IMAGEPATH=?", new String[]{patientid, list.get(list.size()-1)});
                mChoiceActivityIntent.putExtra("patientid",patientid);
                startActivity(mChoiceActivityIntent);
            }
        });


    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onNoteClick(int position) {
        ContentValues values = new ContentValues();
        if(list.size() != 0) {
            values.put("MEMO", memo.getText().toString());
            idDB.update("TABLE2", values, "PATIENTID=? and IMAGEPATH=?", new String[]{patientid, list.get(list.size()-1)});
            Cursor cursor = idDB.query("TABLE2", null, "PATIENTID=? and IMAGEPATH=?", new String[]{patientid, mNotes.get(position).toString()}, null, null, null);
            cursor.moveToNext();
            String Memo = cursor.getString(cursor.getColumnIndex("MEMO"));
            memo.setText(Memo);
        }
        list.add(mNotes.get(position).toString());
        now_imagepath = mNotes.get(position).toString();
        Bitmap bitmap = BitmapFactory.decodeFile(now_imagepath, options);
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

    public void onBackPressed() {
        ContentValues values = new ContentValues();
        for (int i=0; i<imagesize; i++){
            values.put("MEMO", "");
            idDB.update("TABLE2", values, "PATIENTID=? and IMAGEPATH=?", new String[]{patientid, (String) databaseHelper.getImagePath(patientid).get(i)});
        }
        finish();
    }
}
