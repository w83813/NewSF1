package com.example.miis200;

import android.graphics.Bitmap;
import android.util.Log;

public class EyeimageItemRecycler {
    private Bitmap mImage;

    private String mName;

    public EyeimageItemRecycler(Bitmap mImg, String mName){
        this.mImage = mImg;
        Log.i("imgimgimgimg",String.valueOf(mImg));
        this.mName = mName;
        Log.i("imgimgimgimg11111",mName);

    }

    public void setmImg(Bitmap mImg){
        this.mImage = mImg;
    }

    public Bitmap getmImg(){
        return mImage;
    }

    public String getmName(){
        return mName;
    }

}
